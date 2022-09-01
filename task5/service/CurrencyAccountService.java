package task5.service;

import task5.dao.CurrencyAccount;
import task5.dao.CurrencyType;
import task5.util.ValidationUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author Oleh Fliurkevych
 */
public class CurrencyAccountService {

  public CurrencyAccount create(CurrencyType type, BigDecimal amount) {
    var currencyAccount = new CurrencyAccount();
    currencyAccount.setAmount(amount);
    currencyAccount.setCurrencyType(type);
    return currencyAccount;
  }

  public List<CurrencyAccount> create(Map<CurrencyType, BigDecimal> map) {
    List<CurrencyAccount> accounts = new ArrayList<>();
    for (Entry<CurrencyType, BigDecimal> entry : map.entrySet()) {
      var type = entry.getKey();
      var amount = entry.getValue();
      ValidationUtils.validateCurrencyAmount(type, amount);
      accounts.add(create(type, amount));
    }
    return accounts;
  }

}
