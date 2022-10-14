package personal_finance.ui;

import java.io.IOException;
import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import personal_finance.core.Transaction;
import personal_finance.core.User;

public class AddTransactionController extends SceneSwitcher {

    @FXML private TextField transactionAmount;
    @FXML private DatePicker transactionDate;
    @FXML private TextField transactionTitle;
    @FXML private Label userFeedback;
    @FXML private Label usernameDisplay;


    private User user;
    
    @FXML
    void handleAddTransaction(ActionEvent event) {
        LocalDate date = transactionDate.getValue();
        String title = transactionTitle.getText();
        double value = Double.valueOf(transactionAmount.getText());
        Transaction transaction;

        if (date==null) {
            transaction = new Transaction(title, value);
        }
        else {
            transaction = new Transaction(title, value, date);
        }
        user.addTransaction(transaction);
        userFeedback.setText("Transaction added succesfully");

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
