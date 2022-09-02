package task5;

import task5.dao.CurrencyType;
import task5.producer.ExchangeProducer;
import task5.producer.RatesProducer;
import task5.service.AccountService;
import task5.service.CurrencyAccountService;
import task5.service.CurrencyRateService;
import task5.service.ExchangeService;
import task5.service.IoUserService;
import task5.service.UserService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Oleh Fliurkevych
 */
public class Task5 {

  public static void main(String[] args) {
    CurrencyAccountService currencyAccountService = new CurrencyAccountService();
    AccountService accountService = new AccountService(currencyAccountService);
    IoUserService ioUserService = new IoUserService();
    UserService userService = new UserService(accountService, ioUserService);
    var currencyRateService = CurrencyRateService.getInstance();
    var ratesProducer = new RatesProducer(currencyRateService);
    var exchangeService = new ExchangeService(currencyRateService);

    var user1 = userService.create(
      "Oleh", "Fliurkevych",
      Map.of(CurrencyType.USD, BigDecimal.valueOf(1_000),
        CurrencyType.EUR, BigDecimal.valueOf(1_500),
        CurrencyType.UAH, BigDecimal.valueOf(20_000)));
    var user2 = userService.create(
      "Bob", "Fliurkevych",
      Map.of(CurrencyType.USD, BigDecimal.valueOf(10_000),
        CurrencyType.EUR, BigDecimal.valueOf(500)));
    var user3 = userService.create(
      "Tom", "Fliurkevych",
      Map.of(CurrencyType.USD, BigDecimal.valueOf(500),
        CurrencyType.EUR, BigDecimal.valueOf(100),
        CurrencyType.GBP, BigDecimal.valueOf(200_000)));
    var user4 = userService.create(
      "John", "Fliurkevych",
      Map.of(CurrencyType.USD, BigDecimal.valueOf(4_000),
        CurrencyType.EUR, BigDecimal.valueOf(500),
        CurrencyType.UAH, BigDecimal.valueOf(10_000)));
    var user5 = userService.create(
      "Bill", "Fliurkevych",
      Map.of(CurrencyType.USD, BigDecimal.valueOf(800),
        CurrencyType.GBP, BigDecimal.valueOf(1_500),
        CurrencyType.EUR, BigDecimal.valueOf(5_000),
        CurrencyType.UAH, BigDecimal.valueOf(10_000)));

    var users = List.of(user1, user2, user3, user4, user5);

    var exchangeProducer1 = new ExchangeProducer(users, exchangeService, ioUserService);
    var exchangeProducer2 = new ExchangeProducer(users, exchangeService, ioUserService);
    var exchangeProducer3 = new ExchangeProducer(users, exchangeService, ioUserService);
    var exchangeProducer4 = new ExchangeProducer(users, exchangeService, ioUserService);
    var exchangeProducer5 = new ExchangeProducer(users, exchangeService, ioUserService);

    ExecutorService executorService = Executors.newFixedThreadPool(6);
    executorService.execute(ratesProducer);
    executorService.execute(exchangeProducer1);
    executorService.execute(exchangeProducer2);
    executorService.execute(exchangeProducer3);
    executorService.execute(exchangeProducer4);
    executorService.execute(exchangeProducer5);


  }

}
