package personal_finance.core;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Category {
    private String title;
    private double limit;

    public Category() {}

    public Category(String title, double limit) {
        this.title = title;
        this.limit = limit;
    }
    /**
    * Generates a lits of transactions with itself as category
    * @param transactions The list of all transactions from user
    * @return The list with all transations with this category
    */
    public List<Transaction> getTransactions(List<Transaction> transactions) {
        List<Transaction> catTransactions = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (transaction.getCategory()==this) {
                catTransactions.add(transaction);
            }
        }
        return catTransactions;
    }

    public String getTitle() {
        return this.title;
    }

    public double getLimit() {
        return limit;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }

    public void setLimit(double limit) {
        this.limit = limit;
    }

    private double calcTotalMonth(LocalDate startDate, List<Transaction> transactions) {
        double sum = 0;
        startDate= LocalDate.now().withDayOfMonth(startDate.getDayOfMonth());
        LocalDate endDate = startDate.plusMonths(1);
        for (Transaction transaction : transactions) {
            if (transaction.getDate().isAfter(startDate.minusDays(1)) && transaction.getDate().isBefore(endDate) && transaction.getCategory().getTitle()==this.getTitle()) {
                sum+=transaction.getValue();
            }
        }
        return sum;
    }
    /**
    * Sums up all transactions with date between and including 1st day of month to last of month and checks if sum is <= limit
    * @param startDate The date to start the calculation
    * @param transactions The list of all transactions from user
    * @return True if sum is less or equal to limit
    */
    public boolean getLimitCompliance(LocalDate startDate, List<Transaction> transactions) {
        if (calcTotalMonth(startDate, transactions) <= this.limit) {
            return true;
        }
        return false;
    }

    /**
    * Sums up all transactions with date between and including 1st day of month to last of month and calculates the remaining limit
    * @param startDate The date to start the calculation
    * @param transactions The list of all transactions from user
    * @return Differance between limit and sum of transactions
    */
    public double getLimitLeft(LocalDate startDate, List<Transaction> transactions) {
        return this.limit - calcTotalMonth(startDate, transactions);
    }
}
