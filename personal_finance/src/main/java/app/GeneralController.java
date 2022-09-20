package app;

import java.io.IOException;
import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;

public class GeneralController {

    @FXML
    private TextField transactionAmount;

    @FXML
    private DatePicker transactionDate;

    @FXML
    private TextField transactionTitle;

    private User user;

    private Stage stage;
    private Scene scene;
    private Parent root;
    
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

    
    //metode som bytter mellom scener
    @FXML
    public void switchToLogIn(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("LogIn.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void setUser(User user) {
        this.user = user;
    }
}
