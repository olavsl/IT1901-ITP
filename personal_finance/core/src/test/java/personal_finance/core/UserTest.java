package personal_finance.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

//import java.sql.Date;
import java.time.LocalDate;

public class UserTest {

    @Test
    public void testSetUsername() {
        assertThrows(IllegalArgumentException.class, () -> new User("abc", "password", "password"));
        assertThrows(IllegalArgumentException.class, () -> new User("abcdabcdabcdabcdabcda", "password", "password"));
        assertThrows(IllegalArgumentException.class, () -> new User("abcd!", "password", "password"));
    
        // assertEquals("allowedUsername", new User("allowedUsername", "password", "password").getUsername());
        // assertNotEquals("differentUsername", new User("NewAllowedUsername", "password", "password").getUsername());
    }

    @Test
    public void testGetUsername() {
        User user = new User("abcd", "password", "password");
        assertEquals("abcd", user.getUsername());
    }

    @Test
    public void testSetPassword() {
        assertThrows(IllegalArgumentException.class, () -> new User("abcd", "passwordA", "passwordB"));
    }


    @Test
    public void testGetTransactions() {
        User user = new User("abcd", "password", "password");
        Transaction transaction = new Transaction("Test", 100.0, LocalDate.now());
        user.addTransaction(transaction);
        assertEquals(transaction, user.getTransactions().get(0));
    }


    @Test
    public void testAddTransaction() {
        User user = new User("abcd", "password", "password");
        Transaction transaction = new Transaction("Test", 200.0, LocalDate.now());
        user.addTransaction(transaction);
        assertEquals(transaction, user.getTransactions().get(0));
    }
    

    @Test
    public void testCheckIfUserExists() {
        User user = new User("abcd", "password", "password");
        assertEquals(false, user.checkIfUserExists("abcd"));

    } 

 
    
}
