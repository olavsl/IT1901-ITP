package personal_finance.util;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import personal_finance.core.User;

public class UserCreater {
    
    private static RemotePersonalFinanceModelAccess remoteModelAccess = new RemotePersonalFinanceModelAccess();

    /**
     * @param username
     * @param password
     * @param confirmedPassword
     * @param database
     * @return A string which represent one of the three cases: 
     * the username is already taken, 
     * the passwords are different,
     * or the credentials are valid.
     * @throws IOException
     */
    public static String validateNewUserCredentials(String username, String password, String confirmedPassword, String database) throws IOException {
        remoteModelAccess.getPersonalFinanceModel();
        User user = remoteModelAccess.getUser(username);

        if (user != null) {
            return "usernameTaken";
        }

        if (!password.equals(confirmedPassword)) {
            return "differentPasswords";
        }
        
        return "valid";
    }

    /**
     * Creates a new user object with the parsed credentials, and saves it to the JSON database.
     * 
     * @param username
     * @param password
     * @param database
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    public static void createUser(String username, String password, String database) throws IOException, NoSuchAlgorithmException {
        remoteModelAccess.getPersonalFinanceModel();
        User user = new User(username, PasswordHasher.hash(password));
        remoteModelAccess.putUser(user);
    }

    /**
     * Deletes the user who's username matches the parsed username from the database.
     * 
     * @param username
     * @param database
     * @throws IOException
     */
    public static void deleteUser(String username, String database) throws IOException {
        remoteModelAccess.deleteUser(remoteModelAccess.getPersonalFinanceModel().getUser(username));
    }

}
