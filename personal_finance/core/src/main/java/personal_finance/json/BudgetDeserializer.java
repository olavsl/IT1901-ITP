package personal_finance.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import java.io.IOException;
import personal_finance.core.Budget;
import personal_finance.core.Category;

public class BudgetDeserializer extends JsonDeserializer<Budget> {

  @Override
  public Budget deserialize(JsonParser parser, DeserializationContext dsctx) throws IOException {
    TreeNode treeNode = parser.getCodec().readTree(parser);
    return deserialize((JsonNode) treeNode);
  }

  Budget deserialize(JsonNode jsonNode) {
    if (jsonNode instanceof ObjectNode objectNode) {
      Budget budget = new Budget();

      JsonNode startDateNode = objectNode.get("startDate");
      if (startDateNode instanceof TextNode) {
        budget.setStartDate(startDateNode.asText());
      }

      JsonNode categoriesNode = objectNode.get("categories");
      boolean hasCategories = categoriesNode instanceof ArrayNode;
      if (hasCategories) {
        for (JsonNode categoryNode : ((ArrayNode) categoriesNode)) {
          Category category = new CategoryDeserializer().deserialize(categoryNode);
          if (category != null) {
            budget.addCategory(category);
          }
        }
      }

      return budget;
    }

    return null;
  }

}
