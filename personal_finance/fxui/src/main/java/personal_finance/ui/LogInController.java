package personal_finance.ui;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import personal_finance.core.PersonalFinanceModel;
import personal_finance.core.User;
import personal_finance.json.PersonalFinancePersistence;
import personal_finance.util.PasswordHasher;

public class LogInController extends SceneSwitcher {

    private PersonalFinancePersistence pfp = new PersonalFinancePersistence();
    
    @FXML private TextField username;
    @FXML private TextField password;
    @FXML private Label wrongLogInFeedback;

    @FXML
    public void switchToOverview(ActionEvent event) throws IOException, NoSuchAlgorithmException {
        pfp.setStorageFile("users.json");
        PersonalFinanceModel model = pfp.loadPersonalFinanceModel();

        for (User user : model.getUsers()) {
            if (username.getText().equals(user.getUsername()) && PasswordHasher.hash(password.getText()).equals(user.getPassword())) {
                switchToOverview(event, user);
                System.out.println("Successfully logged in!");
                break;
            }
        }

        wrongLogInFeedback.setText("Username and password are not matching!");
    }
}
