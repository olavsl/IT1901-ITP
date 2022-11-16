package personal_finance.ui;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import personal_finance.core.Category;
import personal_finance.core.User;
import personal_finance.util.TransactionHandler;

public class AddTransactionController extends SceneSwitcher {

    @FXML private TextField transactionAmount;
    @FXML private DatePicker transactionDate;
    @FXML private TextField transactionTitle;
    @FXML private ChoiceBox<String> btnChooseCategory;
    @FXML private Label userFeedback;
    @FXML private Label usernameDisplay;

    private User user;
    private Category chosenCategory = null;
    
    @FXML
    void handleAddTransaction(ActionEvent event) throws IOException {
        LocalDate date = transactionDate.getValue();
        String title = transactionTitle.getText();
        double value;
        // Checks for errors in input
        try {
            value = Double.valueOf(transactionAmount.getText());
        } catch (Exception e) {
            userFeedback.setText("Value field is only for numbers, try again");
            return;
        }
        if (title.equals("")) {
            userFeedback.setText("Title can not be empty, try again");
            return;
        }

        TransactionHandler.handleAddTransaction(title, value, date, this.chosenCategory, this.user);

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

    public void setCategoryChoices() {
        List<String> categories = new ArrayList<>();

        btnChooseCategory.getItems().add("Other");
        try {
            for (Category category : user.getBudget().getCategories()) {
                if (!categories.contains(category.getTitle())) {
                    categories.add(category.getTitle());
                    btnChooseCategory.getItems().add(category.getTitle());
                }
            }
        } catch (Exception e) {

        }

        btnChooseCategory.setOnAction((event) -> {
            String selectedCategory = btnChooseCategory.getSelectionModel().getSelectedItem();
            try {
                for (Category category : this.user.getBudget().getCategories()) {
                    if (category.getTitle().equals(selectedCategory)) {
                        this.chosenCategory = category;
                    }
                }
            } catch (Exception e) {
        
            }
        });
    }

    public void updateDisplayname() {
        usernameDisplay.setText(this.user.getUsername());
    }
}