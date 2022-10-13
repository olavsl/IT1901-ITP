package personal_finance.util;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import personal_finance.core.PersonalFinanceModel;
import personal_finance.core.User;
import personal_finance.json.PersonalFinancePersistence;

public class LogInAuthenticator {
    
    private static PersonalFinancePersistence pfp = new PersonalFinancePersistence();

    public static User logIn(String username, String password) throws IOException, NoSuchAlgorithmException {
        pfp.setStorageFile("users.json");
        PersonalFinanceModel model = pfp.loadPersonalFinanceModel();

        for (User user : model.getUsers()) {
            if (username.equals(user.getUsername()) && PasswordHasher.hash(password).equals(user.getPassword())) {
                return user;
            }
        }

        return null;
    }

}
