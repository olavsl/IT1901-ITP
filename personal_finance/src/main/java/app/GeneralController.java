package app;

import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class GeneralController extends SceneSwitcher {

    @FXML private TextField transactionAmount;
    @FXML private DatePicker transactionDate;
    @FXML private TextField transactionTitle;

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
        System.out.println(transaction.getTitle());
    }

    public void setUser(User user) {
        this.user = user;
    }
}
