package personal_finance.core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class PersonalFinanceModelTest {
    
    private PersonalFinanceModel pfm;

    @Test
    public void testGetUsers() {
        pfm = new PersonalFinanceModel(null);

        assertEquals(null, pfm.getUsers());

        List<User> users = new ArrayList<>();
        User user = new User("user", "user");
        users.add(user);
        pfm = new PersonalFinanceModel(users);

        assertEquals(users, pfm.getUsers());
    }

    @Test
    public void testAddUser() {
        pfm = new PersonalFinanceModel(new ArrayList<>());
        User user1 = new User("test1", "test1");
        pfm.addUser(user1);

        assertEquals(user1, pfm.getUsers().get(0));

        User user2 = new User("test2", "test2");
        pfm.addUser(user2);

        assertEquals(user2, pfm.getUsers().get(1));
    }

}
