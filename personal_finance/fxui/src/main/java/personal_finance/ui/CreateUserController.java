package personal_finance.ui;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import personal_finance.util.UserCreater;

public class CreateUserController extends SceneSwitcher {

    @FXML private TextField username;
    @FXML private PasswordField password;
    @FXML private PasswordField confirmedPassword;
    @FXML private Label createUserFeedback;

    @FXML
    public void createUser() throws IOException, NoSuchAlgorithmException {
        String username = this.username.getText();
        String password = this.password.getText();
        String confirmedPassword = this.confirmedPassword.getText();

        String validity = UserCreater.validateNewUserCredentials(username, password, confirmedPassword, "users.json");

        if (validity.equals("usernameTaken")) {
            createUserFeedback.setText("Username is already taken!");
            throw new IllegalArgumentException("Username is already taken!");
        }

        if (validity.equals("differentPasswords")) {
            createUserFeedback.setText("Passwords don't match!");
            throw new IllegalArgumentException("Passwords don't match!");
        }

        UserCreater.createUser(username, password, "users.json");

        createUserFeedback.setText("New user created! Login to access your profile :)");
    }

}
