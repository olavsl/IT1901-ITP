package personal_finance.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import personal_finance.core.Transaction;

public class TransactionSerializer extends JsonSerializer<Transaction> {
    
    /*
     * Format: { "title": "...", "value": "...", "date", "..."}
     */

    @Override
    public void serialize(Transaction transaction, JsonGenerator generator, SerializerProvider provider) throws IOException {
        generator.writeStartObject();
        generator.writeStringField("title", transaction.getTitle());
        generator.writePOJOField("value", transaction.getValue());
        generator.writePOJOField("date", transaction.getDate());
        generator.writeEndObject();
    }

}
