package personal_finance.util;

import java.io.IOException;
import java.time.LocalDate;

import personal_finance.core.Budget;
import personal_finance.core.Category;
import personal_finance.core.User;

public class BudgetHandler {
    
    private static RemotePersonalFinanceModelAccess remoteModelAccess = new RemotePersonalFinanceModelAccess();
    
    /**
     * Creates a new Budget with a start date and adds it to the User. 
     * Then, it writes the budget to the database.
     * 
     * @param startDate
     * @param user
     * @throws IOException
     */
    public static void handleCreateNewBudget(LocalDate startDate, User user) throws IOException {
        Budget budget = user.getBudget();

        if (budget == null) {
            budget = new Budget(startDate);
        } else {
            budget.setStartDate(startDate);
        }

        remoteModelAccess.getPersonalFinanceModel();
        user.setBudget(budget);
        remoteModelAccess.putUser(user);
    }

    /**
     * Adds the new Category to the users Budget. 
     * Then, it writes the category to the database.
     * 
     * @param category
     * @param user
     * @throws IOException
     */
    public static void handleAddCategory(Category category, User user) throws IOException {
        remoteModelAccess.getPersonalFinanceModel();
        user.getBudget().addCategory(category);
        remoteModelAccess.putUser(user);
    }

}
