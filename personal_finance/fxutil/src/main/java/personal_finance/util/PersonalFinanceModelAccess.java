package personal_finance.util;

import personal_finance.core.PersonalFinanceModel;
import personal_finance.core.User;

public interface PersonalFinanceModelAccess {
    
    public PersonalFinanceModel getPersonalFinanceModel();
    
    public User getUser(String username);

    public void putUser(User user);

    public void deleteUser(User user);

}
