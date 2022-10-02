package personal_finance.core;

import java.time.LocalDate;

public class Transaction {
    private String title;
    private double value;
    private LocalDate date;
    // kan legge til kategori ved seinere implementasjon

    public Transaction(String title, double value) {
        this.title = title;
        this.value = value;
        this.date = LocalDate.now();
    }

    public Transaction(String title, double value, LocalDate date) {
        this.title = title;
        this.value = value;
        this.date = date;
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
}
