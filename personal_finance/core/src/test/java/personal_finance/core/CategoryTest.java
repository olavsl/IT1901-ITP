package personal_finance.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class CategoryTest {
   

    @Test
    public void testSetLimit() {
        assertEquals(0, new Category("title",0).getLimit());
        assertEquals(100, new Category("title",100).getLimit());
        assertEquals(-100, new Category("title",-100).getLimit());
    }

    @Test
    public void testGetTitle() {
        Category category = new Category("title",0);
        assertEquals("title", category.getTitle());
    }

    @Test 
    public void testGetLimit() {
        Category category = new Category("title", 0);
        assertEquals(0, category.getLimit());
    }

    @Test
    public void testGetLimitCompliance() {
        List<Transaction> transactions = new ArrayList<>();
        Category category = new Category("title", 1000);
        Transaction transaction = new Transaction("Test", 100);
        transaction.setCategory(category);
        transactions.add(transaction);
        assertTrue(category.getLimitCompliance(LocalDate.now(), transactions));

        Transaction transaction2 = new Transaction("Test2", 1000);
        transaction2.setCategory(category);
        transactions.add(transaction2);
        assertTrue(!category.getLimitCompliance(LocalDate.now(), transactions));
    }

    @Test
    public void testGetLimitLeft() {
        List<Transaction> transactions = new ArrayList<>();
        Category category = new Category("title", 1000);
        Transaction transaction = new Transaction("Test", 100);
        transaction.setCategory(category);
        transactions.add(transaction);
        assertEquals(900, category.getLimitLeft(LocalDate.now(), transactions));
        
        Transaction transaction2 = new Transaction("Test2", 900);
        transaction2.setCategory(category);
        transactions.add(transaction2);
        assertEquals(0, category.getLimitLeft(LocalDate.now(), transactions));

        Transaction transaction3 = new Transaction("Test2", 100);
        transaction3.setCategory(category);
        transactions.add(transaction3);
        assertEquals(-100, category.getLimitLeft(LocalDate.now(), transactions));
    }   
}
