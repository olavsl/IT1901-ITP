package personal_finance.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import personal_finance.core.Budget;
import personal_finance.core.Category;
import personal_finance.core.PersonalFinanceModel;
import personal_finance.core.User;
import personal_finance.json.PersonalFinancePersistence;

public class BudgetHandlerTest {
    @Test
    public void testHandleAddCategory() throws NoSuchAlgorithmException, IOException {
        String newUsername = "newUsername";
        String newPassword = "newPassword";

        Budget budget = new Budget(LocalDate.now());
        Category category = new Category("test", 100);

        UserCreater.createUser(newUsername, newPassword, "test.json");

        PersonalFinancePersistence pfp = new PersonalFinancePersistence();
        pfp.setStorageFile("test.json");
        PersonalFinanceModel model = pfp.loadPersonalFinanceModel();
        User loadedUser = new User();

        for (User u : model.getUsers()) {
            if (u.getUsername().equals(newUsername) && u.getPassword().equals(PasswordHasher.hash(newPassword))) {
                loadedUser = u;
            }
        }

        UserCreater.deleteUser(newUsername, "test.json");

        BudgetHandler.handleCreateNewBudget(budget.getStartDate(), loadedUser, "test.json");
        assertEquals(0, loadedUser.getBudget().getCategories().size());

        BudgetHandler.handleAddCategory(category, loadedUser, "test.json");
        assertEquals(category, loadedUser.getBudget().getCategories().get(0));

    }

    
    @Test
    public void testHandleCreateNewBudget() throws NoSuchAlgorithmException, IOException {
        String newUsername = "newUsername";
        String newPassword = "newPassword";

        Budget budget = new Budget(LocalDate.now());

        UserCreater.createUser(newUsername, newPassword, "test.json");

        PersonalFinancePersistence pfp = new PersonalFinancePersistence();
        pfp.setStorageFile("test.json");
        PersonalFinanceModel model = pfp.loadPersonalFinanceModel();
        User loadedUser = new User();

        for (User u : model.getUsers()) {
            if (u.getUsername().equals(newUsername) && u.getPassword().equals(PasswordHasher.hash(newPassword))) {
                loadedUser = u;
            }
        }

        UserCreater.deleteUser(newUsername, "test.json");

        assertEquals(null, loadedUser.getBudget());
        BudgetHandler.handleCreateNewBudget(budget.getStartDate(), loadedUser, "test.json");

        assertTrue(budget.getStartDate().isEqual(loadedUser.getBudget().getStartDate()) && budget.getCategories().size()==loadedUser.getBudget().getCategories().size());
    }
    
    @AfterAll
    public static void clearTestFile() throws IOException {
        PersonalFinancePersistence pfp = new PersonalFinancePersistence();
        List<User> emptyList = new ArrayList<>();
        PersonalFinanceModel model = new PersonalFinanceModel(emptyList);
        pfp.setStorageFile("test.json");
        pfp.savePersonalFinanceModel(model);
    }
}
