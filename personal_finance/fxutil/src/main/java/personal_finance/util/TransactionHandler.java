package personal_finance.util;

import java.io.IOException;
import java.time.LocalDate;

import personal_finance.core.Category;
import personal_finance.core.Transaction;
import personal_finance.core.User;

public class TransactionHandler {

  private static RemotePersonalFinanceModelAccess remoteModelAccess = new RemotePersonalFinanceModelAccess();

  /**
   * Creates a transaction instance, which is stored in the database under current
   * user.
   * Also, it updates the user object of the instance who called this method.
   * 
   * @param title
   * @param value
   * @param date
   * @param category
   * @param user
   * @throws IOException
   */
  public static void handleAddTransaction(String title, double value, LocalDate date, Category category, User user)
      throws IOException {
    Transaction transaction = new Transaction(title, value);

    if (date == null) {
      transaction.setDate(LocalDate.now());
    } else {
      transaction.setDate(date);
    }

    if (category == null) {
      Category other = new Category("Other", 0);
      transaction.setCategory(other);
    } else {
      transaction.setCategory(category);
    }

    remoteModelAccess.getPersonalFinanceModel();
    user.addTransaction(transaction);
    remoteModelAccess.putUser(user);
  }

}
