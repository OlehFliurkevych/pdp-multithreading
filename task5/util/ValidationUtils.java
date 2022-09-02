package task5.util;

import task5.dao.Account;
import task5.dao.CurrencyAccount;
import task5.dao.CurrencyType;
import task5.dao.User;
import task5.exception.NotFoundException;
import task5.exception.ValidationException;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * @author Oleh Fliurkevych
 */
public class ValidationUtils {

  private static final Logger logger = Logger.getLogger(ValidationUtils.class.getName());

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

  public static void validateUser(boolean fileExist, boolean shouldExists, UUID id) {
    if (!fileExist && shouldExists) {
      throw new NotFoundException(String.format("User with id [%s] does not exist", id));
    } else if (fileExist && !shouldExists) {
      throw new ValidationException(String.format("User with id [%s] already exists", id));
    }
  }

  public static boolean validateUserBeforeExchange(CurrencyType from, CurrencyType to, User user) {
    var currenciesForUser = Optional.ofNullable(user.getAccount())
      .map(Account::getCurrencyAccounts)
      .map(ca -> ca.stream()
        .map(CurrencyAccount::getCurrencyType)
        .collect(Collectors.toSet()))
      .orElse(Collections.emptySet());

    if (!currenciesForUser.contains(from) || !currenciesForUser.contains(to)) {
      logger.info(String.format(
        "User with id [%s] don't have all accounts for exchange from [%s] to [%s]",
        user.getId(), from, to));
      return false;
    }
    return true;
  }

  public static void validateUserExchange(BigDecimal amount, CurrencyType from, User user) {
    if (amount.doubleValue() < 0) {
      throw new ValidationException(
        String.format("User with id [%s] don't have enough amount in [%s] account",
          user.getId(), from));
    }
  }

}
