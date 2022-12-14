package task5.dao;

import java.io.Serializable;
import java.util.List;

/**
 * @author Oleh Fliurkevych
 */
public class Account implements Serializable {

  private String accountNumber;
  private List<CurrencyAccount> currencyAccounts;

  public String getAccountNumber() {
    return accountNumber;
  }

  public void setAccountNumber(String accountNumber) {
    this.accountNumber = accountNumber;
  }

  public List<CurrencyAccount> getCurrencyAccounts() {
    return currencyAccounts;
  }

  public void setCurrencyAccounts(List<CurrencyAccount> currencyAccounts) {
    this.currencyAccounts = currencyAccounts;
  }
}
