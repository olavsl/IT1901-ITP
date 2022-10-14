package personal_finance.core;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Budget {
    private List<Category> categories = new ArrayList<>();
    private LocalDate startDate;

    public Budget(LocalDate startDate) {
        this.startDate = startDate;
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
    * @return true when all categories are within limit, false otherewise
    */
    public boolean budgetCompliance() {
        LocalDate startDate= LocalDate.now();
        startDate.withDayOfMonth(this.startDate.getDayOfMonth());
        for (Category category : categories) {
            if (!category.getLimitCompliance(startDate)) {
                return false;
            }
        }
        return true;
    }

    public double getCategoryLimit(Category category) {
        return category.getLimit();
    }

    public double getCategoryLimitLeft(Category category) {
        return category.getLimitLeft(startDate);
    }
}

