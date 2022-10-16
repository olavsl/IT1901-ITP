package personal_finance.ui;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import personal_finance.core.Transaction;
import personal_finance.core.User;

public class OverviewController extends SceneSwitcher {

    @FXML private Label totLife;
    @FXML private Label totMonth;
    @FXML private TableView<Transaction> transactionOverview;
    @FXML TableColumn<Transaction, String> transactionTitles;
    @FXML TableColumn<Transaction, Double> transactionAmounts;
    @FXML TableColumn<Transaction, String> transactionDates;
    @FXML private Label usernameDisplay;
    ObservableList<Transaction> transactions = FXCollections.observableArrayList();

    private User user;

    public void updateTransactionOverview() {
        if (transactions.isEmpty()) {
            transactions.addAll(user.getTransactions());
        }
        else {
            Transaction last_element = user.getTransactions().get(user.getTransactions().size()-1);
            transactions.add(last_element);
        }
        
        transactionTitles.setCellValueFactory(new PropertyValueFactory<Transaction, String>("title"));
        transactionAmounts.setCellValueFactory(new PropertyValueFactory<Transaction, Double>("value"));
        transactionDates.setCellValueFactory(new PropertyValueFactory<Transaction, String>("date"));
        
        transactionOverview.setItems(transactions);
    }

    public void updateDisplayname() {
        usernameDisplay.setText(this.user.getUsername());
    }

    public void updateTotals() {
        totLife.setText(String.valueOf(this.user.calcTotalLife()));
        totMonth.setText(String.valueOf(this.user.calcTotalMonth()));
    }

    @FXML
    public void switchToOverview(ActionEvent event) throws IOException {
        switchToOverview(event, this.user);
    }

    public void updateOverview() {
        usernameDisplay.setText(this.user.getUsername());
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
}
