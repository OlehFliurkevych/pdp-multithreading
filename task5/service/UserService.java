package task5.service;

import task5.dao.CurrencyType;
import task5.dao.User;
import task5.util.ValidationUtils;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author Oleh Fliurkevych
 */
public class UserService {

  private final AccountService accountService;

  public UserService(AccountService accountService) {
    this.accountService = accountService;
  }

  public User create(String name, String surname, Map<CurrencyType, BigDecimal> map) {
    ValidationUtils.validateUserNameAndSurname(name, surname);
    
    var user = new User();
    user.setName(name);
    user.setSurname(surname);
    user.setAccount(accountService.create(map));
    
    return user;
  }

}
