package personal_finance.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Test
    public void testCalcTotalMonth() {
        User user = new User("abcd", "password");
        // Transaction with first day of month
        Transaction transaction1 = new Transaction("Test", 100.0, LocalDate.now().withDayOfMonth(1));
        // Transaction with last day of month
        Transaction transaction2 = new Transaction("Test", 200.0, LocalDate.now().withDayOfMonth(1).plusMonths(1).minusDays(1));
        user.addTransaction(transaction1);
        user.addTransaction(transaction2);
        assertEquals(300, user.calcTotalMonth());
    }
    
    @Test
    public void testCalcTotalLife() {
        User user = new User("abcd", "password");
        Transaction transaction1 = new Transaction("Test", 100.0);
        Transaction transaction2 = new Transaction("Test", 200.0);
        user.addTransaction(transaction1);
        user.addTransaction(transaction2);

        assertEquals(300, user.calcTotalLife());
    }

    @Test
    public void testGetCategories() {
        User user = new User("abcd", "password");
        Transaction transaction1 = new Transaction("Test", 100.0);
        Transaction transaction2 = new Transaction("Test", 200.0);
        transaction1.setCategory(null);
        transaction2.setCategory(new Category("title", 100));
        user.addTransaction(transaction1);
        assertTrue(user.getCategories().contains(null));

        user.addTransaction(transaction2);
        assertTrue(user.getCategories().contains(null) && user.getCategories().get(1).getTitle().equals("title"));
    }
    
}
