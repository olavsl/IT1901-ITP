package personal_finance.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import personal_finance.core.Category;
import personal_finance.core.Transaction;

public class TransactionsSorter {
    
    /**
     * @param transactions
     * @return A list of transactions sorted by amount
     */
    public static List<Transaction> sortByAmount(List<Transaction> transactions) {
        Collections.sort(transactions, new Comparator<Transaction>() {
            @Override
            public int compare(Transaction t1, Transaction t2) {
                return (int) (t1.getValue() - t2.getValue());
            }
        });

        return transactions;
    }

    /**
     * @param transactions
     * @return A list of transactions sorted by date
     */
    public static List<Transaction> sortByDate(List<Transaction> transactions) {
        Collections.sort(transactions, new Comparator<Transaction>() {
            @Override
            public int compare(Transaction t1, Transaction t2) {
                return t1.getDate().compareTo(t2.getDate());
            }
        });

        return transactions;
    }

    /**
     * @param transactions
     * @param category
     * @return A list of transactions in the specified category
     */
    public static List<Transaction> filter(List<Transaction> transactions, Category category) {
        List<Transaction> result = new ArrayList<>();
        
        for (Transaction t : transactions) {
            if (t.getCategory().getTitle().equals(category.getTitle())) {
                result.add(t);
            }
        }

        return transactions;
    }

}
