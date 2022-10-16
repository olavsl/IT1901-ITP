package personal_finance.core;

import java.util.List;

public class PersonalFinanceModel {
    
    private List<User> users;

    public PersonalFinanceModel (List<User> users) {
             this.users = List.copyOf(users);
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public void deleteUser(String username) {
        for (User user : this.users) {
            if (username.equals(user.getUsername())) {
                this.users.remove(user);
                break;
            }
        }
    }

    public List<User> getUsers() {
        List<User> usersCopy = List.copyOf(this.users);
        return usersCopy;
    }

}
