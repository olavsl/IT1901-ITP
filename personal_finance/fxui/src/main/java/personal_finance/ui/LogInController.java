package personal_finance.ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import personal_finance.core.User;

public class LogInController extends SceneSwitcher {

    private User user;
    
    @FXML private TextField username;
    @FXML private TextField password;

    @FXML
    public void switchToOverview(ActionEvent event) throws IOException {
        // switchToGeneral(event, this.user);
        switchToOverview(event, new User(username.getText(), password.getText(), password.getText())); // User object for testing functionality while persistence is not yet implemented
    }

    public void setUser(User user) {
        this.user = user;
    }
}
