package task5.dao;

import java.util.UUID;

/**
 * @author Oleh Fliurkevych
 */
public class User {

  private UUID id;
  private String name;
  private String surname;
  private Account account;

  public User() {
    this.id = UUID.randomUUID();
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

  public void setAccount(Account account) {
    this.account = account;
  }
}
