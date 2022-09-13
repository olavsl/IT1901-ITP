package app;

import java.util.List;

public class User {
    
    private String username;
    private String password;
    private List<Bilag> bilags;


    public User(String username, String password, String confirmedPassword) {
        setUsername(username);
        setPassword(password, confirmedPassword);; // For later: Hash passord for more security
    }

    public String getUsername() {
        return this.username;
    }

    public List<Bilag> getBilags() {
        return this.bilags;
    }

    private void setUsername(String username) {
        if (username.length() < 4 || username.length() > 20) {
            throw new IllegalArgumentException("Username must be between 4 and 20 characters!");
        }

        if (!username.matches("[a-zA-Z0-9]+")) {
            throw new IllegalArgumentException("Username must only contain letters and numbers!");
        }

        // if (username is already taken) {throw exception}

        this.username = username;
    }

    private void setPassword(String password, String confirmedPassword) {
        if (!password.equals(confirmedPassword)) {
            throw new IllegalArgumentException("Passwords does not match!");
        }

        this.password = password;
    }
}
