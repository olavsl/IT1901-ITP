package personal_finance.util;

import java.io.IOException;
import java.time.LocalDate;

import personal_finance.core.Budget;
import personal_finance.core.PersonalFinanceModel;
import personal_finance.core.User;
import personal_finance.json.PersonalFinancePersistence;

public class BudgetHandler {
    
    private static PersonalFinancePersistence pfp = new PersonalFinancePersistence();

    
    /**
     * Creates a new Budget with a start date and adds it to the User. 
     * Then, it writes the budget to the database.
     * 
     * @param startDate
     * @param user
     * @param database
     * @throws IOException
     */
    public static void handleCreateNewBudget(LocalDate startDate, User user, String database) throws IOException {
        pfp.setStorageFile(database);
        PersonalFinanceModel model;
        
        model = pfp.loadPersonalFinanceModel();

        Budget budget = new Budget(startDate);

        for (User u : model.getUsers()) {
            if (user.getUsername().equals(u.getUsername())) {
                u.setBudget(budget);
            }
        }

        user.setBudget(budget);
        
        pfp.savePersonalFinanceModel(model);
    }

}
