package personal_finance.ui;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import personal_finance.core.User;
import personal_finance.util.LogInAuthenticator;

public class LogInController extends SceneSwitcher {
    
    @FXML private TextField username;
    @FXML private TextField password;
    @FXML private Label wrongLogInFeedback;

    @FXML
    public void logIn(ActionEvent event) throws IOException, NoSuchAlgorithmException {
        String username = this.username.getText();
        String password = this.password.getText();
        
        User user = LogInAuthenticator.logIn(username, password);

        if (user.equals(null)) {
            wrongLogInFeedback.setText("Username and password are not matching!");
        }
        
        switchToOverview(event, user);
    }
}
