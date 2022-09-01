package task5.service;

import task5.dao.Account;
import task5.dao.CurrencyType;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

/**
 * @author Oleh Fliurkevych
 */
public class AccountService {
  
  private final CurrencyAccountService currencyAccountService;

  public AccountService(CurrencyAccountService currencyAccountService) {
    this.currencyAccountService = currencyAccountService;
  }

  public Account create(Map<CurrencyType, BigDecimal> map) {
    var account = new Account();
    account.setAccountNumber(UUID.randomUUID().toString());
    account.setCurrencyAccounts(currencyAccountService.create(map));
    return account;
  }

}
