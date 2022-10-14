package personal_finance.core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class TransactionTest {
    
    @Test
    public void testConstructor() {
        String title = "test";
        double value = 100.0;
        LocalDate date = LocalDate.now();

        Transaction transaction = new Transaction(title, value, date);

        assertEquals(title, transaction.getTitle());
        assertEquals(value, transaction.getValue());
        assertEquals(date, transaction.getDate());
    }

}
