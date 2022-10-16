package personal_finance.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserTest {

    @Test
    public void testSetUsername() {
        assertThrows(IllegalArgumentException.class, () -> new User("abc", "password"));
        assertThrows(IllegalArgumentException.class, () -> new User("abcdabcdabcdabcdabcda", "password"));
        assertThrows(IllegalArgumentException.class, () -> new User("abcd!", "password"));
    
        assertEquals("allowedUsername", new User("allowedUsername", "password").getUsername());
        assertNotEquals("differentUsername", new User("NewAllowedUsername", "password").getUsername());
    }

    @Test
    public void testGetUsername() {
        User user = new User("abcd", "password");
        assertEquals("abcd", user.getUsername());
    }

    @Test
    public void testSetPassword() {
        assertEquals("password", new User("username", "password").getPassword());
    }

    @Test
    public void testAddTransaction() {
        User user = new User("abcd", "password");
        Transaction transaction = new Transaction("Test", 200.0, LocalDate.now());
        user.addTransaction(transaction);
        assertEquals(transaction, user.getTransactions().get(0));
    }

    @Test
    public void testGetTransactions() {
        User user = new User("abcd", "password");
        List<Transaction> transactions = new ArrayList<>();
        Transaction transaction1 = new Transaction("Test", 100.0, LocalDate.now());
        Transaction transaction2 = new Transaction("Test", 200.0, LocalDate.now());
        transactions.add(transaction1);
        transactions.add(transaction2);
        user.addTransaction(transaction1);
        user.addTransaction(transaction2);
        assertEquals(transactions, user.getTransactions());
    }
    
}
