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

import personal_finance.core.Category;

public class CategoryDeserializer extends JsonDeserializer<Category> {

    @Override
    public Category deserialize(JsonParser parser, DeserializationContext dsctx) throws IOException {
        TreeNode treeNode = parser.getCodec().readTree(parser);
        return deserialize((JsonNode) treeNode);
    }

    Category deserialize(JsonNode jsonNode) {
        if (jsonNode instanceof ObjectNode objectNode) {
            Category category = new Category();
            JsonNode titleNode = objectNode.get("title");
            if (titleNode instanceof TextNode) {
                category.setTitle(titleNode.asText());
            }
            JsonNode limitNode = objectNode.get("limit");
            if (limitNode instanceof NumericNode) {
                category.setLimit(limitNode.asDouble());
            }

            return category;
        }

        return null;
    }
}
