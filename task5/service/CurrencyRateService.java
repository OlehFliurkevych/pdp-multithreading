package task5.service;

import task5.dao.CurrencyType;

import java.math.BigDecimal;
import java.util.EnumMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * @author Oleh Fliurkevych
 */
public class CurrencyRateService {

  private static final Logger logger = Logger.getLogger(CurrencyRateService.class.getName());
  private static volatile CurrencyRateService currencyService = new CurrencyRateService();
  private Map<CurrencyType, BigDecimal> rates = new EnumMap<>(CurrencyType.class);

  private CurrencyRateService() {
    rates.put(CurrencyType.USD, BigDecimal.ONE);
    rates.put(CurrencyType.EUR, BigDecimal.valueOf(0.8));
    rates.put(CurrencyType.UAH, BigDecimal.valueOf(20));
    rates.put(CurrencyType.GBP, BigDecimal.valueOf(0.6));

  }

  public static CurrencyRateService getInstance() {
    return currencyService;
  }

  public synchronized void updateRates(CurrencyType currencyType, BigDecimal newRate) {
    rates.put(currencyType, newRate);
    logger.info(String.format("Set new rate [%s] for currency [%s]", newRate, currencyType));
  }

  public synchronized Map<CurrencyType, BigDecimal> getRates() {
    return rates;
  }

}
