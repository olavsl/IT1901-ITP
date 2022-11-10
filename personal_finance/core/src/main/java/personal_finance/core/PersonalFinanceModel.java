package personal_finance.core;

import java.util.List;

public class PersonalFinanceModel {
    
    private List<User> users;

    public PersonalFinanceModel (List<User> users) {
        this.users = users;
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
        return this.users;
    }

    public User getUser(String username) {
        for (User user : this.users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }

        return null;
    }

    public User putUser(User user) {
        User oldUser = getUser(user.getUsername());
        
        if (oldUser != null) {
            deleteUser(user.getUsername());
        }

        addUser(user);
        return oldUser;
    }

}
