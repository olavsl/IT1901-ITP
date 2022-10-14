package personal_finance.util;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import personal_finance.core.PersonalFinanceModel;
import personal_finance.core.User;
import personal_finance.json.PersonalFinancePersistence;

public class UserCreater {
    
    private static PersonalFinancePersistence pfp = new PersonalFinancePersistence();

    public static String validateNewUserCredentials(String username, String password, String confirmedPassword, String database) throws IOException {
        PersonalFinanceModel model;
        pfp.setStorageFile(database);

        try {
            model = pfp.loadPersonalFinanceModel();
        } catch (Exception e) {
            List<User> emptyList = new ArrayList<>();
            model = new PersonalFinanceModel(emptyList);
        }

        for (User u : model.getUsers()) {
            if (u.getUsername().equals(username)) {
                return "usernameTaken";
            }
        }

        if (!password.equals(confirmedPassword)) {
            return "differentPasswords";
        }
        
        return "valid";
    }

    public static void createUser(String username, String password, String database) throws IOException, NoSuchAlgorithmException {
        User user;
        PersonalFinanceModel model;
        pfp.setStorageFile(database);

        try {
            model = pfp.loadPersonalFinanceModel();
        } catch (Exception e) {
            List<User> emptyList = new ArrayList<>();
            model = new PersonalFinanceModel(emptyList);
        }

        user = new User(username, PasswordHasher.hash(password));

        model.addUser(user);

        pfp.savePersonalFinanceModel(model);
    }

    public static void deleteUser(String username, String database) throws IOException {
        PersonalFinanceModel model;
        pfp.setStorageFile(database);

        try {
            model = pfp.loadPersonalFinanceModel();
        } catch (Exception e) {
            List<User> emptyList = new ArrayList<>();
            model = new PersonalFinanceModel(emptyList);
        }

        model.deleteUser(username);

        pfp.savePersonalFinanceModel(model);
    }

}
