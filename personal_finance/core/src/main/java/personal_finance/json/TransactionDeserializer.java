package personal_finance.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.NumericNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import java.io.IOException;
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
      if (dateNode instanceof TextNode) {
        transaction.setDate(dateNode.asText());
      }
      JsonNode categoryNode = objectNode.get("category");
      if (categoryNode instanceof ObjectNode) {
        transaction.setCategory(new CategoryDeserializer().deserialize(categoryNode));
      }

      return transaction;
    }

    return null;
  }

}
