package task5.dao;

import java.util.List;

/**
 * @author Oleh Fliurkevych
 */
public class Account {

  private User user;
  private List<Currency> currencies;

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public List<Currency> getCurrencies() {
    return currencies;
  }

  public void setCurrencies(List<Currency> currencies) {
    this.currencies = currencies;
  }
}
