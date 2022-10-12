package personal_finance.json;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import personal_finance.core.PersonalFinanceModel;
import personal_finance.core.User;

public class PersonalFinanceModelDeserializer extends JsonDeserializer<PersonalFinanceModel> {

    @Override
    public PersonalFinanceModel deserialize(JsonParser parser, DeserializationContext dsctx) throws IOException {
        TreeNode treeNode = parser.getCodec().readTree(parser);
        return deserialize((JsonNode) treeNode);
    }

    PersonalFinanceModel deserialize(JsonNode jsonNode) {
        if (jsonNode instanceof ObjectNode objectNode) {
            List<User> emptyList = new ArrayList<>();
            PersonalFinanceModel model = new PersonalFinanceModel(emptyList);

            JsonNode usersNode = objectNode.get("users");
            boolean hasUsers = usersNode instanceof ArrayNode;
            if (hasUsers) {
                for (JsonNode userNode : (ArrayNode) usersNode) {
                    User user = new UserDeserializer().deserialize(userNode);
                    model.addUser(user);
                }
            }

            return model;
        }

        return null;
    }
}
