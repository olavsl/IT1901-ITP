package personal_finance.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import personal_finance.core.PersonalFinanceModel;
import personal_finance.core.Transaction;
import personal_finance.core.User;
import personal_finance.json.PersonalFinancePersistence;

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

        UserCreater.createUser(newUsername, newPassword);

        PersonalFinancePersistence pfp = new PersonalFinancePersistence();
        pfp.setStorageFile("test.json");
        PersonalFinanceModel model = pfp.loadPersonalFinanceModel();
        User loadedUser = new User();

        for (User u : model.getUsers()) {
            if (u.getUsername().equals(newUsername) && u.getPassword().equals(PasswordHasher.hash(newPassword))) {
                loadedUser = u;
            }
        }

        UserCreater.deleteUser(newUsername);

        assertTrue(compareUsers(user, loadedUser));
    }

    @Test
    public void testDeleteUser() throws NoSuchAlgorithmException, IOException {
        String username = "username";
        String password = "password";

        List<User> usersWithUserToBeDeleted = new ArrayList<>();
        User userToBeDeleted = new User(username, PasswordHasher.hash(password));
        usersWithUserToBeDeleted.add(userToBeDeleted);
        usersWithUserToBeDeleted.add(new User("test", PasswordHasher.hash("test")));
        PersonalFinanceModel modelWithUserToBeDeleted = new PersonalFinanceModel(usersWithUserToBeDeleted);

        PersonalFinancePersistence pfp = new PersonalFinancePersistence();
        pfp.setStorageFile("test.json");
        UserCreater.createUser(username, password);
        PersonalFinanceModel loadedModel = pfp.loadPersonalFinanceModel();

        assertTrue(comparePersonalFinanceModels(modelWithUserToBeDeleted, loadedModel));

        UserCreater.deleteUser(username);

        List<User> listWithoutUserToBeDeleted = new ArrayList<>();
        listWithoutUserToBeDeleted.add(new User("test", PasswordHasher.hash("test")));
        PersonalFinanceModel modelWithoutUserToBeDeleted = new PersonalFinanceModel(listWithoutUserToBeDeleted);

        PersonalFinanceModel loadedModel2 = pfp.loadPersonalFinanceModel();
        UserCreater.deleteUser(username);

        assertTrue(comparePersonalFinanceModels(modelWithoutUserToBeDeleted, loadedModel2));
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

    @AfterAll
    public static void clearTestFile() throws IOException {
        PersonalFinancePersistence pfp = new PersonalFinancePersistence();
        List<User> emptyList = new ArrayList<>();
        PersonalFinanceModel model = new PersonalFinanceModel(emptyList);
        pfp.setStorageFile("test.json");
        pfp.savePersonalFinanceModel(model);
    }

}
