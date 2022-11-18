package personal_finance.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import personal_finance.core.Transaction;
import personal_finance.core.User;

public class UserSerializer extends JsonSerializer<User> {

  /*
   * Format: { "username": "...", "password": "...", "transactions":
   * [transaction1, transaction2, ... ] }
   */

  @Override
  public void serialize(User user, JsonGenerator generator, SerializerProvider provider) throws IOException {
    generator.writeStartObject();
    generator.writeStringField("username", user.getUsername());
    generator.writeStringField("password", user.getPassword());
    if (user.getTransactions().size() >= 0) {
      generator.writeArrayFieldStart("transactions");
      for (Transaction transaction : user.getTransactions()) {
        generator.writeObject(transaction);
      }
      generator.writeEndArray();
    }
    if (user.getBudget() != null) {
      generator.writeObjectField("budget", user.getBudget());
    }
    generator.writeEndObject();
  }

}
