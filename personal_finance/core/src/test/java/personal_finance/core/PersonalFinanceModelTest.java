package personal_finance.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class PersonalFinanceModelTest {
    
    private PersonalFinanceModel pfm;

    @Test
    public void testContainsUser() {
        pfm = new PersonalFinanceModel(new ArrayList<>());
        User user = new User("username", "password");
        pfm.addUser(user);

        assertTrue(pfm.containsUser("username"));
        assertFalse(pfm.containsUser("nonExistingUser"));
    }

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
        
        boolean added = pfm.addUser(user1);

        assertTrue(added);
        assertEquals(user1, pfm.getUser("test1"));

        User user2 = new User("test2", "test2");
        pfm.addUser(user2);

        assertEquals(user2, pfm.getUser("test2"));
        assertNotEquals(user1, pfm.getUser("test2"));
        
        added = pfm.addUser(user1);
        
        assertFalse(added);
    }

    @Test
    public void testGetUser() {
        pfm = new PersonalFinanceModel(new ArrayList<>());
        User user = new User("username", "password");
        pfm.addUser(user);

        User gottenUser = pfm.getUser("username");

        assertEquals(user, gottenUser);

        User nullUser = pfm.getUser("nonExistingUser");

        assertNull(nullUser);
    }

    @Test
    public void testPutUser() {
        pfm = new PersonalFinanceModel(new ArrayList<>());
        User user = new User("username", "password");
        pfm.addUser(user);

        User addedUser = pfm.getUser("username");

        assertEquals(user, addedUser);

        user.addTransaction(new Transaction("test", 0));
        boolean put = pfm.putUser(user);

        assertTrue(put);

        User putUser = pfm.getUser("username");

        assertEquals(user, putUser);

        User userNotInDatabase = new User("username2", "password");
        boolean notPut = pfm.putUser(userNotInDatabase);

        assertFalse(notPut);
    }

    @Test
    public void testDeleteUser() {
        pfm = new PersonalFinanceModel(new ArrayList<>());
        User user = new User("username", "password");
        pfm.addUser(user);

        assertTrue(pfm.containsUser("username"));

        boolean deleted = pfm.deleteUser("username");

        assertTrue(deleted);
        assertFalse(pfm.containsUser("username"));

        boolean notDeleted = pfm.deleteUser("nonExistingUser");

        assertFalse(notDeleted);
    }

}
