package personal_finance.core;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Category {
    private List<Transaction> transactions = new ArrayList<>();
    private String title;
    private double limit;

    public Category(String title, double limit) {
        this.title = title;
        this.limit = limit;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public String getTitle() {
        return title;
    }

    public double getLimit() {
        return limit;
    }
    
    public void setLimit(double limit) {
        this.limit = limit;
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    private double calcTotalMonth(LocalDate startDate) {
        double sum = 0;
        for (Transaction transaction : transactions) {
            if (transaction.getDate().isAfter(startDate)) {
                sum+=transaction.getValue();
            }
        }
        return sum;
    }
    /**
    * sums up all transactions and checks if sum is within limit
    * @param startDate the date to start the calculation
    * @return true if sum is less or equal to limit
    */
    public boolean getLimitCompliance(LocalDate startDate) {
        if (calcTotalMonth(startDate) <= this.limit) {
            return true;
        }
        return false;
    }

    /**
    * sums up all transactions and calculates the remaining limit
    * @param startDate the date to start the calculation
    * @return differance between limit and sum of transactions
    */
    public double getLimitLeft(LocalDate startDate) {
        return this.limit - calcTotalMonth(startDate);
    }
}
