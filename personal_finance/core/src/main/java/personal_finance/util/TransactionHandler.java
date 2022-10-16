package personal_finance.util;

import java.io.IOException;
import java.time.LocalDate;

import personal_finance.core.PersonalFinanceModel;
import personal_finance.core.Transaction;
import personal_finance.core.User;
import personal_finance.json.PersonalFinancePersistence;

public class TransactionHandler {
    
    private static PersonalFinancePersistence pfp = new PersonalFinancePersistence();

    /**
     * Creates a transaction instance, which is stored in the database under current user. 
     * Also, it updates the user object of the instance who called this method.
     * 
     * @param title
     * @param value
     * @param date
     * @param user
     * @param database
     * @throws IOException
     */
    public static void handleAddTransaction(String title, double value, LocalDate date, User user, String database) throws IOException {
        Transaction transaction;

        if (date == null) {
            transaction = new Transaction(title, value, LocalDate.now());
        } else {
            transaction = new Transaction(title, value, date);
        }

        pfp.setStorageFile(database);
        PersonalFinanceModel model = pfp.loadPersonalFinanceModel();
        
        for (User u : model.getUsers()) {
            if (user.getUsername().equals(u.getUsername())) {
                u.addTransaction(transaction);
            }
        }

        pfp.savePersonalFinanceModel(model);

        user.addTransaction(transaction);
    }

}
