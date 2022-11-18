package personal_finance.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import personal_finance.core.Budget;
import personal_finance.core.Category;
import personal_finance.core.User;

public class BudgetHandlerTest {

  @Test
  public void testHandleAddCategory() throws NoSuchAlgorithmException, IOException {
    String newUsername = "newUsername";
    String newPassword = "newPassword";

    Budget budget = new Budget(LocalDate.now());
    Category category = new Category("test", 100);

    UserCreater.createUser(newUsername, newPassword);

    User loadedUser = LogInAuthenticator.logIn(newUsername, newPassword);

    UserCreater.deleteUser(newUsername);

    BudgetHandler.handleCreateNewBudget(budget.getStartDate(), loadedUser);
    assertEquals(0, loadedUser.getBudget().getCategories().size());

    BudgetHandler.handleAddCategory(category, loadedUser);
    assertEquals(category, loadedUser.getBudget().getCategories().get(0));

  }

  @Test
  public void testHandleCreateNewBudget() throws NoSuchAlgorithmException, IOException {
    String newUsername = "newUsername";
    String newPassword = "newPassword";

    Budget budget = new Budget(LocalDate.now());

    UserCreater.createUser(newUsername, newPassword);

    User loadedUser = LogInAuthenticator.logIn(newUsername, newPassword);

    UserCreater.deleteUser(newUsername);

    assertEquals(null, loadedUser.getBudget());
    BudgetHandler.handleCreateNewBudget(budget.getStartDate(), loadedUser);

    assertTrue(budget.getStartDate().isEqual(loadedUser.getBudget().getStartDate())
        && budget.getCategories().size() == loadedUser.getBudget().getCategories().size());
  }

  // @AfterAll
  // public static void clearTestFile() throws IOException {
  // PersonalFinancePersistence pfp = new PersonalFinancePersistence();
  // List<User> emptyList = new ArrayList<>();
  // PersonalFinanceModel model = new PersonalFinanceModel(emptyList);
  // pfp.setStorageFile("test.json");
  // pfp.savePersonalFinanceModel(model);
  // }
}
