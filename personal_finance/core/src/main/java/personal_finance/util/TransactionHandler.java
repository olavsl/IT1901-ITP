package personal_finance.util;

import java.io.IOException;
import java.time.LocalDate;

import personal_finance.core.PersonalFinanceModel;
import personal_finance.core.Transaction;
import personal_finance.core.User;
import personal_finance.json.PersonalFinancePersistence;

public class TransactionHandler {
    
    private static PersonalFinancePersistence pfp = new PersonalFinancePersistence();

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
