package personal_finance.core;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Budget {
    private List<Category> categories = new ArrayList<>();
    private LocalDate startDate;

    public Budget(LocalDate startDate) {
        setStartDate(startDate);
    }

    public void addCategory(String title, double limit) {
        if (!inList(title)) {
            Category category = new Category(title, limit);
            categories.add(category);
        }
    }

    private boolean inList(String title) {
        for (Category category : categories) {
            if (category.getTitle().equalsIgnoreCase(title)) {
                return true;
            }
        }
        return false;
    }
    /**
    * sums up limit for all categories in budget
    * @return the sum of all limits
    */
    public double calcTotalLimit() {
        double sum = 0;
        for (Category category : categories) {
            sum+=category.getLimit();
        }
        return sum;
    }

    /**
    * checks if all the categories are within their limits
    * @param transactions The list of all transactions from user
    * @return true when all categories are within limit, false otherewise
    */
    public boolean budgetCompliance(List<Transaction> transactions) {
        for (Category category : categories) {
            if (!category.getLimitCompliance(this.startDate, transactions)) {
                return false;
            }
        }
        return true;
    }

    public double getCategoryLimit(Category category) {
        return category.getLimit();
    }

    public double getCategoryLimitLeft(Category category, List<Transaction> transactions) {
        return category.getLimitLeft(startDate, transactions);
    }

    public List<Category> getCategories() {
        return categories;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        if (startDate==null) {
            startDate = LocalDate.now();
        }
        this.startDate = startDate;
    }
}

