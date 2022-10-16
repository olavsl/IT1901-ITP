package personal_finance.ui;

import java.io.IOException;
import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import personal_finance.core.User;
import personal_finance.util.TransactionHandler;

public class AddTransactionController extends SceneSwitcher {

    @FXML private TextField transactionAmount;
    @FXML private DatePicker transactionDate;
    @FXML private TextField transactionTitle;
    @FXML private Label userFeedback;
    @FXML private Label usernameDisplay;

    private User user;
    
    @FXML
    void handleAddTransaction(ActionEvent event) throws IOException {
        LocalDate date = transactionDate.getValue();
        String title = transactionTitle.getText();
        double value = Double.valueOf(transactionAmount.getText());

        TransactionHandler.handleAddTransaction(title, value, date, this.user, "users.json");

        userFeedback.setText("Transaction added successfully");
    }


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
