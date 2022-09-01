package task5.util;

import task5.dao.CurrencyType;
import task5.exception.ValidationException;

import java.math.BigDecimal;

/**
 * @author Oleh Fliurkevych
 */
public class ValidationUtils {

  public static void validateCurrencyAmount(CurrencyType currencyType, BigDecimal amount) {
    if (amount.doubleValue() < 0) {
      throw new ValidationException(
        String.format("Amount for currency [%s] can't be less than zero.", currencyType));
    }
  }

  public static void validateUserNameAndSurname(String name, String surname) {
    if (name.length() < 3 || surname.length() < 3) {
      throw new ValidationException("Name and surname can't be less than 3 symbols.");
    }
  }

}
