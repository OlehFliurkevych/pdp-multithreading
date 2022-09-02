package task5.service;

import task5.dao.CurrencyType;
import task5.dao.User;
import task5.util.ValidationUtils;

import java.math.BigDecimal;
import java.util.Map;
import java.util.logging.Logger;

/**
 * @author Oleh Fliurkevych
 */
public class UserService {

  private static final Logger logger = Logger.getLogger(UserService.class.getName());
  private final AccountService accountService;
  private final IoUserService ioUserService;

  public UserService(AccountService accountService, IoUserService ioUserService) {
    this.accountService = accountService;
    this.ioUserService = ioUserService;
  }

  public User create(String name, String surname, Map<CurrencyType, BigDecimal> map) {
    ValidationUtils.validateUserNameAndSurname(name, surname);

    logger.info(String.format("Creating user with name [%s] and surname [%s]", name, surname));
    var user = new User();
    user.setName(name);
    user.setSurname(surname);
    user.setAccount(accountService.create(map));

    ioUserService.writeNewUser(user);
    logger.info(String.format("Created and wrote user into file. User id = [%s]", user.getId()));

    return user;
  }
  
}
