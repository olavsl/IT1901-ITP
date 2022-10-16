package personal_finance.ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import personal_finance.core.User;

public class BudgetController extends SceneSwitcher {

    @FXML private Label usernameDisplay;

    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    @FXML
    public void switchToAddTransaction(ActionEvent event) throws IOException {
        switchToAddTransaction(event, this.user);
    }

    @FXML
    public void switchToOverview(ActionEvent event) throws IOException {
        switchToOverview(event, this.user);
    }
    
    @FXML
    public void switchToBudget(ActionEvent event) throws IOException {
        switchToBudget(event, this.user);
    }

    public void updateDisplayname() {
        usernameDisplay.setText(this.user.getUsername());
    }
}
