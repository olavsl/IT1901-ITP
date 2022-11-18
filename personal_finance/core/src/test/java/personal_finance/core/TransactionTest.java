package personal_finance.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class TransactionTest {

  @Test
  public void testConstructor() {
    String title = "test";
    double value = 100.0;
    LocalDate date = LocalDate.now();
    Category category = new Category("test", 100);

    Transaction transaction = new Transaction(title, value, date);

    assertEquals(title, transaction.getTitle());
    assertEquals(value, transaction.getValue());
    assertEquals(date, transaction.getDate());

    Transaction transaction2 = new Transaction();
    transaction2.setTitle(title);
    transaction2.setDate(date);
    transaction2.setValue(value);
    transaction2.setCategory(category);

    assertEquals(category, transaction2.getCategory());
    assertTrue(title.equals(transaction2.getTitle()));
    assertEquals(value, transaction2.getValue());
    assertEquals(date, transaction2.getDate());
  }

  @Test
  public void testSetDateStr() {
    String date = "1/11/2022";
    LocalDate date2 = LocalDate.of(2022, 11, 1);

    Transaction transaction = new Transaction();
    transaction.setDate(date);
    assertTrue(transaction.getDate().isEqual(date2));
  }

}
