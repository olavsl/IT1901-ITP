package personal_finance.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import personal_finance.core.Category;

public class CategorySerializer extends JsonSerializer<Category> {

    @Override
    public void serialize(Category category, JsonGenerator generator, SerializerProvider serializers) throws IOException {
        generator.writeStartObject();
        generator.writeStringField("title", category.getTitle());
        generator.writePOJOField("limit", category.getLimit());
        generator.writeEndObject();
    }
    
}
