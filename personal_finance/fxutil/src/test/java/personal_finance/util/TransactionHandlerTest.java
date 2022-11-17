package personal_finance.util;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import personal_finance.core.Category;
import personal_finance.core.Transaction;
import personal_finance.core.User;

public class TransactionHandlerTest {
    
    private RemotePersonalFinanceModelAccess remoteModelAccess = new RemotePersonalFinanceModelAccess();

    @Test
    public void testHandleAddTransaction() throws IOException {
        User user = new User("username", "password");
        remoteModelAccess.postUser(user);

        String title = "title";
        double value = 0.0;
        LocalDate date = LocalDate.now();
        Category category = new Category("Other", 0);

        Transaction transaction = new Transaction(title, value, date);
        transaction.setCategory(category);

        TransactionHandler.handleAddTransaction(title, value, null, null, user);
    
        remoteModelAccess = new RemotePersonalFinanceModelAccess();

        Transaction addedTransaction = remoteModelAccess.getUser("username").getTransactions().get(0);

        assertTrue(compareTransactions(transaction, addedTransaction));
    }

    private boolean compareTransactions(Transaction t1, Transaction t2) {
        if (!t1.getTitle().equals(t2.getTitle())) {
            return false;
        }

        if (t1.getValue() != t2.getValue()) {
            return false;
        }

        if (!t1.getDate().equals(t2.getDate())) {
            return false;
        }

        return true;
    }

}
