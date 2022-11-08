package personal_finance.ui;

import java.io.IOException;
import java.util.List;

import personal_finance.core.Budget;
import personal_finance.core.Category;
import personal_finance.core.User;
import personal_finance.util.BudgetHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


public class BudgetController extends SceneSwitcher {

    @FXML private DatePicker budgetStartDate;
    @FXML private TextField categoryLimit;
    @FXML private TableColumn<Category, String> categoryTitles;
    @FXML private TableColumn<Category, Double> categoryLimits;
    @FXML private TableColumn<Button, Double> deleteBtn;
    @FXML private TableView<Category> categoryOverview;
    @FXML private TextField categoryTitle;
    @FXML private Label userFeedback;
    @FXML private Label usernameDisplay;
    ObservableList<Category> categories = FXCollections.observableArrayList();
    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    public void updateCategoryOverview() {
        if (user.getBudget() == null) {
            return;
        }
        if (categories.isEmpty()) {
            categories.addAll(user.getBudget().getCategories());
        }
        else {
            Category last_element = user.getBudget().getCategories().get(user.getBudget().getCategories().size()-1);
            categories.add(last_element);
        }
        
        categoryTitles.setCellValueFactory(new PropertyValueFactory<Category, String>("title"));
        categoryLimits.setCellValueFactory(new PropertyValueFactory<Category, Double>("limit"));
        
        categoryOverview.setItems(categories);
    }

    @FXML
    public void handleSetBudget(ActionEvent event) throws IOException {
        if (user.getBudget() != null) {
            user.getBudget().setStartDate(budgetStartDate.getValue());
            userFeedback.setText("Budget start date successfully changed");
        }
        else {
            user.setBudget(new Budget(budgetStartDate.getValue()));
            userFeedback.setText("Budget start date successfully set");
        }

        BudgetHandler.handleCreateNewBudget(budgetStartDate.getValue(), this.user, "users.json");
    }

    @FXML
    public void handleAddCategory(ActionEvent event) {
        double limit;
        String title = categoryTitle.getText();
        
        // Error handling
        try {
            limit = Double.valueOf(categoryLimit.getText());
            if (limit < 0) {
                throw new IllegalArgumentException("Limit value out of bounds, can only be positive");
            }
        } catch (Exception e) {
            userFeedback.setText("Limit field is only for positive numbers, try again");
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
        updateCategoryOverview();
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
