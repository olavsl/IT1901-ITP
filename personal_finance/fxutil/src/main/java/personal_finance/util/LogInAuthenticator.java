package personal_finance.util;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import personal_finance.core.User;

public class LogInAuthenticator {
    
    private static RemotePersonalFinanceModelAccess remoteModelAccess = new RemotePersonalFinanceModelAccess();

    /**
     * @param username
     * @param password
     * @return User object with matching username, if there exits one. Otherwise, null.
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    public static User logIn(String username, String password) throws IOException, NoSuchAlgorithmException {
        remoteModelAccess = new RemotePersonalFinanceModelAccess();
        
        User user = remoteModelAccess.getUser(username);

        if (user == null) {
            return null;
        } else if (user.getPassword().equals(PasswordHasher.hash(password))) {
            return user;
        }

        return null;
    }

}
