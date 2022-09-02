package task5.producer;

import task5.dao.CurrencyType;
import task5.service.CurrencyRateService;

import java.math.BigDecimal;
import java.util.Map.Entry;
import java.util.Random;
import java.util.logging.Logger;

/**
 * @author Oleh Fliurkevych
 */
public class RatesProducer implements Runnable {

  private static final Logger logger = Logger.getLogger(RatesProducer.class.getName());
  private static final Random random = new Random();
  private final CurrencyRateService currencyService;

  public RatesProducer(CurrencyRateService currencyRateService) {
    this.currencyService = currencyRateService;
  }

  @Override
  public void run() {
    while (true) {
      for (Entry<CurrencyType, BigDecimal> entry : currencyService.getRates().entrySet()) {
        var newRate = getRandomRate(entry.getValue());
        currencyService.updateRates(entry.getKey(), newRate);
        sleep();
      }
    }
  }

  private BigDecimal getRandomRate(BigDecimal currentRate) {
    var randomFloat = random.nextFloat();
    return currentRate.multiply(BigDecimal.valueOf(randomFloat));
  }

  private void sleep() {
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      logger.warning(e.getMessage());
    }
  }

}
