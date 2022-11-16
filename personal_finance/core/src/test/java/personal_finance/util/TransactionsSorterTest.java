package personal_finance.util;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personal_finance.core.Transaction;
import personal_finance.core.Category;

public class TransactionsSorterTest {
    List<Transaction> transactions;
    Category category;
    Category category2;

    @BeforeEach
    public void setup() {
        transactions = new ArrayList<>();
        category = new Category("food", 1000);
        category2 = new Category("bills", 1000);
        Transaction transaction1 = new Transaction("testA", 2, LocalDate.now().plusDays(2));
        Transaction transaction2 = new Transaction("testB", 3, LocalDate.now().plusDays(0));
        Transaction transaction3 = new Transaction("testC", 1, LocalDate.now().plusDays(1));
        transaction1.setCategory(category);
        transaction2.setCategory(category2);
        transaction3.setCategory(category2);
        transactions.add(transaction1);
        transactions.add(transaction2);
        transactions.add(transaction3);
    }

    @Test
    public void testFilter() {
        assertTrue(TransactionsSorter.filter(transactions, category).get(0).getCategory().getTitle().equals("food") && TransactionsSorter.filter(transactions, category).size()==1);
        assertTrue(TransactionsSorter.filter(transactions, category2).get(0).getCategory().getTitle().equals("bills") && TransactionsSorter.filter(transactions, category2).size()==2);
    }

    @Test
    public void testSortByAmount() {
        assertTrue(TransactionsSorter.sortByAmount(transactions).get(0).getValue() <= TransactionsSorter.sortByAmount(transactions).get(2).getValue());
    }

    @Test
    public void testSortByDate() {
        assertTrue(TransactionsSorter.sortByAmount(transactions).get(0).getDate().isAfter(TransactionsSorter.sortByAmount(transactions).get(2).getDate()));
    }
}
