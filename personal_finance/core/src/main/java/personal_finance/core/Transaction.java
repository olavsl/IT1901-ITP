package personal_finance.core;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private String title;
    private double value;
    private LocalDate date;
    private Category category;

    public Transaction() {}

    public Transaction(String title, double value, Category category) {
        this.title = title;
        this.value = value;
        this.date = LocalDate.now();
        setCategory(category);
    }

    public Transaction(String title, double value, LocalDate date, Category category) {
        this.title = title;
        this.value = value;
        this.date = date;
        setCategory(category);
    }

    public Transaction(String title, double value, LocalDate date, Category category) {
        this.title = title;
        this.value = value;
        this.date = date;
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public double getValue() {
        return value;
    }

    public LocalDate getDate() {
        return date;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        this.date = LocalDate.parse(date, formatter);
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        if (category==null) {
            this.category = new Category("$Other$", 0);
        }
        else {
            this.category = category;
        }
    }
}
