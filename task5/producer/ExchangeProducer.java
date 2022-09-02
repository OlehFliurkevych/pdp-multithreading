package task5.producer;

import task5.dao.CurrencyType;
import task5.dao.User;
import task5.service.ExchangeService;
import task5.service.IoUserService;
import task5.util.ValidationUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * @author Oleh Fliurkevych
 */
public class ExchangeProducer implements Runnable {

  private static final Logger logger = Logger.getLogger(ExchangeProducer.class.getName());
  private static final Random random = new Random();
  private final List<User> users;
  private final ExchangeService exchangeService;
  private final IoUserService ioUserService;

  public ExchangeProducer(List<User> users, ExchangeService exchangeService,
    IoUserService ioUserService) {
    this.users = users;
    this.exchangeService = exchangeService;
    this.ioUserService = ioUserService;
  }

  @Override
  public void run() {
    while (true) {
      //simulating user entering 
      var amount = BigDecimal.valueOf(random.nextInt(100));
      var user = users.get(random.nextInt(users.size()));
      var from = getRandomCurrencyType();
      var to = getRandomCurrencyType();
      logger.info(
        String.format("Try to simulate exchange [%s] [%s] to [%s] for user with id = [%s]",
          amount, from, to, user.getId()));

      if (ValidationUtils.validateUserBeforeExchange(from, to, user)) {
        var exchangeAmount = exchangeService.exchangeCurrency(from, to, amount);
        var amountFrom = user.getAmountByCurrencyType(from).subtract(amount);
        ValidationUtils.validateUserExchange(amountFrom, from, user);
        var amountTo = user.getAmountByCurrencyType(to).add(exchangeAmount);

        var currencyAccounts = user.getCurrencyAccounts().stream()
          .peek(currencyAccount -> {
            if (currencyAccount.getCurrencyType().equals(from)) {
              currencyAccount.setAmount(amountFrom);
            } else if (currencyAccount.getCurrencyType().equals(to)) {
              currencyAccount.setAmount(amountTo);
            }
          })
          .collect(Collectors.toList());
        user.setCurrencyAccounts(currencyAccounts);
        logger.info(String.format("Exchanged [%s] [%s] to [%s] for user with id [%s]",
          amount, from, to, user.getId()));

        ioUserService.updateUser(user);
      }
    }
  }

  private CurrencyType getRandomCurrencyType() {
    return CurrencyType.values()[random.nextInt(CurrencyType.values().length)];
  }

}
