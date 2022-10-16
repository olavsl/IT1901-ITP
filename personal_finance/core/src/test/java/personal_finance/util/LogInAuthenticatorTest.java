package personal_finance.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import personal_finance.core.User;

public class LogInAuthenticatorTest {

    @BeforeAll
    public static void setUp() throws NoSuchAlgorithmException, IOException {
        UserCreater.createUser("test", "test", "test.json");
    }

    @Test
    public void testLogIn() throws NoSuchAlgorithmException, IOException {
        User correctUser = new User("test", PasswordHasher.hash("test"));
        User loggedInUser = LogInAuthenticator.logIn("test", "test", "test.json");
        
        assertEquals(correctUser.getUsername(), loggedInUser.getUsername());
        assertEquals(correctUser.getPassword(), loggedInUser.getPassword());
        assertEquals(correctUser.getTransactions(), loggedInUser.getTransactions());

        assertEquals(null, LogInAuthenticator.logIn("test", "wrongPassword", "test.json"));
        assertNotEquals(correctUser, LogInAuthenticator.logIn("test", "wrongPassword", "test.json"));
        
        assertThrows(IOException.class, () -> {
            LogInAuthenticator.logIn("test", "test", "nonExistingDatabase.json");
        });
    }
    
    @AfterAll
    public static void cleanUp() throws NoSuchAlgorithmException, IOException {
        UserCreater.deleteUser("test", "test.json");
    }
}
