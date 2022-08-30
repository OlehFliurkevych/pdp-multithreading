package task5.dao;

import java.math.BigDecimal;

/**
 * @author Oleh Fliurkevych
 */
public class Currency {
  
  private CurrencyType currency;
  private BigDecimal rate;

  public CurrencyType getCurrency() {
    return currency;
  }

  public void setCurrency(CurrencyType currency) {
    this.currency = currency;
  }

  public BigDecimal getRate() {
    return rate;
  }

  public void setRate(BigDecimal rate) {
    this.rate = rate;
  }
}
