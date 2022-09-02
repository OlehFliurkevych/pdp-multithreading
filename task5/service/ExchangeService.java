package task5.service;

import task5.dao.CurrencyType;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author Oleh Fliurkevych
 */
public class ExchangeService {
  
  private final CurrencyRateService currencyService;

  public ExchangeService(CurrencyRateService currencyService) {
    this.currencyService = currencyService;
  }

  public BigDecimal exchangeCurrency(CurrencyType from, CurrencyType to , BigDecimal amount){
    var rates = currencyService.getRates();
    return convertCurrencies(rates.get(from), rates.get(to), amount);
  }

  private BigDecimal convertCurrencies(BigDecimal fromRate, BigDecimal toRate, BigDecimal amount) {
    var exchangeRate = fromRate.divide(toRate, 10, RoundingMode.CEILING);
    return amount.multiply(exchangeRate);
  }
  
}
