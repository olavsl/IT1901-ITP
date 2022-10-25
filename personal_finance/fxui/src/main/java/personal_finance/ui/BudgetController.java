package personal_finance.ui;

import java.io.IOException;
import java.util.List;

import personal_finance.core.Budget;
import personal_finance.core.Category;
import personal_finance.core.User;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;


public class BudgetController extends SceneSwitcher {

    @FXML private DatePicker budgetStartDate;
    @FXML private TextField categoryLimit;
    @FXML private TableColumn<?, ?> categoryLimits;
    @FXML private TableView<?> categoryOverview;
    @FXML private TextField categoryTitle;
    @FXML private TableColumn<?, ?> categoryTitles;
    @FXML private Label userFeedback;
    @FXML private Label usernameDisplay;

    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    @FXML
    public void handleSetBudget(ActionEvent event) {
        if (user.getBudget()!=null) {
            user.getBudget().setStartDate(budgetStartDate.getValue());
            userFeedback.setText("Budget start date successfully changed");
        }
        else {
            user.setBudget(new Budget(budgetStartDate.getValue()));
            userFeedback.setText("Budget start date successfully changed");
        }

    }

    @FXML
    public void handleAddCategory(ActionEvent event) {
        double limit;
        String title = categoryTitle.getText();

        try {
            limit = Double.valueOf(categoryLimit.getText());
        } catch (Exception e) {
            userFeedback.setText("Limit field is only for numbers, try again");
            return;
        }

        if (title.equals("")) {
            userFeedback.setText("Title can not be empty, try again");
            return;
        }
        List<Category> categories = user.getBudget().getCategories();
        
        for (Category category : categories) {
            if (category.getTitle().equals(title)) {
                userFeedback.setText("Already a category with this title, try again");
                return;
            }
        }
        user.getBudget().addCategory(title, limit);
        userFeedback.setText("Successfully added category");
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
