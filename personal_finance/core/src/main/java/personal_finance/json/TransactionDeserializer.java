package personal_finance.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.NumericNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;

import personal_finance.core.Transaction;

public class TransactionDeserializer extends JsonDeserializer<Transaction> {
    
    @Override
    public Transaction deserialize(JsonParser parser, DeserializationContext dsctx) throws IOException {
        TreeNode treeNode = parser.getCodec().readTree(parser);
        return deserialize((JsonNode) treeNode);
    }

    Transaction deserialize(JsonNode jsonNode) {
        if (jsonNode instanceof ObjectNode objectNode) {
            Transaction transaction = new Transaction();
            JsonNode titleNode = objectNode.get("title");
            if (titleNode instanceof TextNode) {
                transaction.setTitle(titleNode.asText());
            }
            JsonNode valueNode = objectNode.get("value");
            if (valueNode instanceof NumericNode) {
                transaction.setValue(valueNode.asInt());
            }
            JsonNode dateNode = objectNode.get("date");
            if (dateNode instanceof TextNode) { // TODO: serialization of dates is tricky: https://www.baeldung.com/jackson-serialize-dates
                transaction.setDate(dateNode.asText());
            }

            return transaction;
        }

        return null;
    }

}
