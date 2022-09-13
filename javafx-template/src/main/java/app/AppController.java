package app;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AppController {

    private User user;

    @FXML private TextField username;
    @FXML private TextField password;
    @FXML private TextField confirmedPassword;
    @FXML private Label createUserFeedback;

    public AppController() {

    }

    @FXML
    public void createUser() {
        if (!password.getText().equals(confirmedPassword.getText())) {
            createUserFeedback.setText("Passwords don't match!");
        }
        
        this.user = new User(username.getText(), password.getText(), confirmedPassword.getText());
        createUserFeedback.setText("New user created! Login to access your profile :)");
    }

}
