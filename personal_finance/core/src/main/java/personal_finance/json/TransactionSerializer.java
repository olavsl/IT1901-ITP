package personal_finance.json;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        generator.writeStringField("date", formatter.format(transaction.getDate()));
        generator.writeObjectField("category", transaction.getCategory());
        generator.writeEndObject();
    }

}
