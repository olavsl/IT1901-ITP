package personal_finance.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;

import personal_finance.core.Transaction;
import personal_finance.core.User;

public class UserDeserializer extends JsonDeserializer<User> {

    @Override
    public User deserialize(JsonParser parser, DeserializationContext dsctx) throws IOException {
        TreeNode treeNode = parser.getCodec().readTree(parser);
        return deserialize((JsonNode) treeNode);
    }

    User deserialize(JsonNode jsonNode) {
        if (jsonNode instanceof ObjectNode objectNode) {
            User user = new User();
            JsonNode usernameNode = objectNode.get("username");
            if (usernameNode instanceof TextNode) {
                user.setUsername(usernameNode.asText());
            }
            JsonNode passwordNode = objectNode.get("password");
            if (passwordNode instanceof TextNode) {
                user.setPassword(passwordNode.asText());
            }
            JsonNode transactionsNode = objectNode.get("transactions");
            boolean hasTransactions = transactionsNode instanceof ArrayNode;
            if (hasTransactions) {
                for (JsonNode transactionNode : ((ArrayNode) transactionsNode)) {
                    Transaction transaction = new TransactionDeserializer().deserialize(transactionNode);
                    if (transaction != null) {
                        user.addTransaction(transaction);
                    }
;                }
            }

            return user;
        }

        return null;
    }

}
