package personal_finance.core;

import java.util.List;

public class PersonalFinanceModel {

  private List<User> users;

  public PersonalFinanceModel(List<User> users) {
    this.users = users;
  }

  /**
   * @param username
   * @return True if user with username is in list. Else, False.
   */
  public boolean containsUser(String username) {
    for (User u : this.users) {
      if (username.equals(u.getUsername())) {
        return true;
      }
    }

    return false;
  }

  /**
   * @param user
   * @return True if the user is added. False if a user with that name already
   *         exists.
   */
  public boolean addUser(User user) {
    if (containsUser(user.getUsername())) {
      return false;
    }

    this.users.add(user);
    return true;
  }

  public boolean deleteUser(String username) {
    for (User user : this.users) {
      if (username.equals(user.getUsername())) {
        this.users.remove(user);
        return true;
      }
    }

    return false;
  }

  public List<User> getUsers() {
    return this.users;
  }

  public User getUser(String username) {
    for (User user : this.users) {
      if (username.equals(user.getUsername())) {
        return user;
      }
    }

    return null;
  }

  public boolean putUser(User user) {
    if (!containsUser(user.getUsername())) {
      return false;
    }

    deleteUser(user.getUsername());
    addUser(user);

    return true;
  }

}
