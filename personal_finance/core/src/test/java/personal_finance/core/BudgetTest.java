package personal_finance.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;




public class BudgetTest {
    
    @Test 
    public void testAddCategory(){
        Budget budget = new Budget(null);
        assertEquals(0, budget.getCategories().size());
        budget.addCategory("title", 100);
        assertTrue(budget.getCategories().size()==1);
        assertThrows(IllegalArgumentException.class, () -> budget.addCategory("title", 100));
    }

    @Test 
    public void testAddCategory2(){
        Budget budget = new Budget(null);
        Category category = new Category("title",1000);
        assertEquals(0, budget.getCategories().size());
        budget.addCategory(category);
        assertTrue(budget.getCategories().contains(category));
    }

    @Test
    public void testCalcTotalLimit(){
        Budget budget = new Budget(null);
        budget.addCategory("title", 100);
        assertEquals(100, budget.calcTotalLimit());
        budget.addCategory("title2", 1000);
        assertEquals(1100, budget.calcTotalLimit());
    }

    @Test
    public void testBudgetCompliance(){
        List<Transaction> transactions = new ArrayList<>();
        Category category = new Category("title",1000);
        Budget budget = new Budget(LocalDate.now());
        budget.addCategory("title", 1000);
        Transaction transaction = new Transaction("Test", 100);
        transaction.setCategory(category);
        transactions.add(transaction);
        assertTrue(budget.budgetCompliance(transactions));

        Transaction transaction2 = new Transaction("Test2", 1000);
        transaction2.setCategory(category);
        transactions.add(transaction2);
        assertTrue(!budget.budgetCompliance(transactions));
    }

    @Test
    public void testGetCategoryLimit(){
        Budget budget = new Budget(null);
        Category category = new Category("Food", 100);
        budget.addCategory("title", 100);
        assertEquals(100, budget.getCategoryLimit(category));
    }

    @Test
    public void testGetCategoryLimitLeft(){
        List<Transaction> transactions = new ArrayList<>();
        Category category = new Category("title",1000);
        Budget budget = new Budget(LocalDate.now());
        budget.addCategory("title", 1000);
        Transaction transaction = new Transaction("Test", 100);
        transaction.setCategory(category);
        transactions.add(transaction);
        assertEquals(900, budget.getCategoryLimitLeft(category, transactions));

        Transaction transaction2 = new Transaction("Test2", 900);
        transaction2.setCategory(category);
        transactions.add(transaction2);
        assertEquals(0, budget.getCategoryLimitLeft(category, transactions));
    }
    @Test
    public void testGetSetStartDate(){
        Budget budget = new Budget(null);
        assertEquals(null,budget.getStartDate());
        budget.setStartDate(LocalDate.now());
        assertEquals(LocalDate.now(),budget.getStartDate());
    }
    @Test
    public void testGetCategoryFromString() {
        Budget budget = new Budget(null);
        Category category = new Category("title",1000);
        Category category2 = new Category("title2",1000);
        Category category3 = new Category("title3",1000);
        budget.addCategory(category);
        budget.addCategory(category2);
        budget.addCategory(category3);

        assertEquals(category,budget.getCategoryFromString("title"));
        assertEquals(category2,budget.getCategoryFromString("title2"));
        assertEquals(category3,budget.getCategoryFromString("title3"));
        assertEquals(null,budget.getCategoryFromString("lol"));
    }
}
