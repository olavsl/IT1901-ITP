package personal_finance.ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import personal_finance.core.User;
import personal_finance.core.PersonalFinanceModel;
import personal_finance.json.PersonalFinancePersistence;

public class CreateUserController extends SceneSwitcher {

    private User user;
    private PersonalFinancePersistence pfp = new PersonalFinancePersistence();

    @FXML private TextField username;
    @FXML private TextField password;
    @FXML private TextField confirmedPassword;
    @FXML private Label createUserFeedback;

    @FXML
    public void createUser() throws IOException {
        if (!password.getText().equals(confirmedPassword.getText())) {
            createUserFeedback.setText("Passwords don't match!");
        }
        
        this.user = new User(username.getText(), password.getText(), confirmedPassword.getText());

        pfp.setStorageFilePath("");
        pfp.savePersonalFinanceModel(new PersonalFinanceModel(this.user));

        createUserFeedback.setText("New user created! Login to access your profile :)");
    }

    public void switchToLogIn(ActionEvent event) throws IOException {
        switchToLogIn(event, this.user);
    }
}
