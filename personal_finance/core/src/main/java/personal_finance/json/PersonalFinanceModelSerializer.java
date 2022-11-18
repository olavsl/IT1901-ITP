package personal_finance.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import personal_finance.core.PersonalFinanceModel;
import personal_finance.core.User;

public class PersonalFinanceModelSerializer extends JsonSerializer<PersonalFinanceModel> {

  @Override
  public void serialize(PersonalFinanceModel model, JsonGenerator generator, SerializerProvider provider)
      throws IOException {
    generator.writeStartObject();
    if (model.getUsers().size() >= 0) {
      generator.writeArrayFieldStart("users");
      for (User user : model.getUsers()) {
        generator.writeObject(user);
      }
      generator.writeEndArray();
    }
    generator.writeEndObject();
  }

}
