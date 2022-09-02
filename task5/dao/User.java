package task5.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author Oleh Fliurkevych
 */
public class User implements Serializable {

  private UUID id;
  private String name;
  private String surname;
  private Account account;

  public User() {
    this.id = UUID.randomUUID();
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public Account getAccount() {
    return account;
  }

  public List<CurrencyAccount> getCurrencyAccounts() {
    return account.getCurrencyAccounts();
  }

  public Map<CurrencyType, BigDecimal> getCurrencyToAmountMap() {
    return getCurrencyAccounts().stream()
      .collect(Collectors.toMap(CurrencyAccount::getCurrencyType, CurrencyAccount::getAmount));
  }

  public BigDecimal getAmountByCurrencyType(CurrencyType currencyType) {
    return getCurrencyToAmountMap().get(currencyType);
  }

  public void setCurrencyAccounts(List<CurrencyAccount> currencyAccounts) {
    account.setCurrencyAccounts(currencyAccounts);
  }

  public void setAccount(Account account) {
    this.account = account;
  }
}
