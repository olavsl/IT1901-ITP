package personal_finance.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.junit.jupiter.api.Test;

import personal_finance.core.PersonalFinanceModel;
import personal_finance.core.Transaction;
import personal_finance.core.User;

public class UserCreaterTest {

    @Test
    public void testValidateNewUserCredentials() throws IOException, NoSuchAlgorithmException {
        String allowedUsername = "username";
        String allowedPassword = "password";

        assertEquals("valid", UserCreater.validateNewUserCredentials(allowedUsername, allowedPassword, allowedPassword));

        UserCreater.createUser(allowedUsername, allowedPassword);
        String takenUsername = allowedUsername;

        assertEquals("usernameTaken", UserCreater.validateNewUserCredentials(takenUsername, allowedPassword, allowedPassword));

        String newUsername = "newUsername";
        String differentPassword = "password1";

        assertEquals("differentPasswords", UserCreater.validateNewUserCredentials(newUsername, allowedPassword, differentPassword));

        UserCreater.deleteUser(allowedUsername);
    }

    @Test
    public void testCreateUser() throws NoSuchAlgorithmException, IOException {
        String newUsername = "newUsername";
        String newPassword = "newPassword";
        User user = new User(newUsername, PasswordHasher.hash(newPassword));

        RemotePersonalFinanceModelAccess remoteModelAccess = new RemotePersonalFinanceModelAccess();

        UserCreater.createUser(newUsername, newPassword);

        remoteModelAccess = new RemotePersonalFinanceModelAccess();

        User loadedUser = remoteModelAccess.getUser(newUsername);

        UserCreater.deleteUser(newUsername);

        assertTrue(compareUsers(user, loadedUser));
    }

    @Test
    public void testDeleteUser() throws NoSuchAlgorithmException, IOException {
        RemotePersonalFinanceModelAccess remoteModelAccess = new RemotePersonalFinanceModelAccess();
        User user = new User("username", "password");
        
        remoteModelAccess.postUser(user);
        remoteModelAccess = new RemotePersonalFinanceModelAccess();

        assertTrue(compareUsers(user, remoteModelAccess.getUser("username")));

        remoteModelAccess.deleteUser(user);
        remoteModelAccess = new RemotePersonalFinanceModelAccess();

        assertNull(remoteModelAccess.getUser("username"));
    }

    public boolean compareUsers(User a, User b) {
        if (!a.getUsername().equals(b.getUsername())) {
            return false;
        }

        if (!a.getPassword().equals(b.getPassword())) {
            return false;
        }

        int loopSize;

        if (a.getTransactions().size() < b.getTransactions().size()) {
            loopSize = a.getTransactions().size();
        } else {
            loopSize = b.getTransactions().size();
        }

        for (int i = 0; i < loopSize; i++) {
            Transaction t1 = a.getTransactions().get(i);
            Transaction t2 = b.getTransactions().get(i);
            
            if (!t1.getTitle().equals(t2.getTitle())) {
                return false;
            }

            if (t1.getValue() != t2.getValue()) {
                return false;
            }

            if (!t1.getDate().equals(t2.getDate())) {
                return false;
            }
        }

        return true;
    }

    public boolean comparePersonalFinanceModels(PersonalFinanceModel a, PersonalFinanceModel b) {
        int loopSize;

        if (a.getUsers().size() < b.getUsers().size()) {
            loopSize = a.getUsers().size();
        } else {
            loopSize = b.getUsers().size();
        }
        
        for (int i = 0; i < loopSize; i++) {
            if (!compareUsers(a.getUsers().get(i), b.getUsers().get(i))) {
                System.out.println(String.valueOf(i) + " " + a.getUsers().get(i).getUsername() + " " + b.getUsers().get(i).getUsername());
                return false;
            }
        }

        return true;
    }

}
