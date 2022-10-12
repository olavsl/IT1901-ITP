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

    public List<User> getUsers() {
        return this.users;
    }

}
