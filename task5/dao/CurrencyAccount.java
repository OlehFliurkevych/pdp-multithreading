package task5.dao;

import java.math.BigDecimal;

/**
 * @author Oleh Fliurkevych
 */
public class CurrencyAccount {
  
  private CurrencyType currencyType;
  private BigDecimal amount;

  public CurrencyType getCurrencyType() {
    return currencyType;
  }

  public void setCurrencyType(CurrencyType currencyType) {
    this.currencyType = currencyType;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }
}
