package app;

import java.time.LocalDate;

public class Bilag {
    private String name;
    private double value;
    private LocalDate date;
    // kan legge til kategori ved seinere implementasjon

    public Bilag(String name, double value) {
        this.name = name;
        this.value = value;
        this.date = LocalDate.now();
    }

    public Bilag(String name, double value, LocalDate date) {
        this.name = name;
        this.value = value;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public double getValue() {
        return value;
    }

    public LocalDate getDate() {
        return date;
    }    
}
