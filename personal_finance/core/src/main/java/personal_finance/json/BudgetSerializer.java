package personal_finance.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import personal_finance.core.Budget;
import personal_finance.core.Category;

public class BudgetSerializer extends JsonSerializer<Budget> {
    
    @Override
    public void serialize(Budget budget, JsonGenerator generator, SerializerProvider serializers) throws IOException {
        generator.writeStartObject();
        generator.writePOJOField("startDate", budget.getStartDate());
        if (budget.getCategories().size() > 0) {
            generator.writeArrayFieldStart("categories");
            for (Category category : budget.getCategories()) {
                generator.writeObject(category);
            }
            generator.writeEndArray();
        }
        generator.writeEndObject();
    }

}
