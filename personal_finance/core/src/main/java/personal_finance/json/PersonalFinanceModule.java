package personal_finance.json;

import java.util.EnumSet;
import java.util.Set;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;

import personal_finance.core.PersonalFinanceModel;
import personal_finance.core.Transaction;
import personal_finance.core.User;
import personal_finance.json.PersonalFinancePersistence.PersonalFinanceModelParts;

// @SuppressWarnings("serial")
public class PersonalFinanceModule extends SimpleModule {
    
    private static final String NAME = "PersonalFinanceModule";

    public PersonalFinanceModule(Set<PersonalFinanceModelParts> parts) {
        super(NAME, Version.unknownVersion());

        addSerializer(Transaction.class, new TransactionSerializer());
        addDeserializer(Transaction.class, new TransactionDeserializer());

        addSerializer(User.class, new UserSerializer());
        addDeserializer(User.class, new UserDeserializer());

        addSerializer(PersonalFinanceModel.class, new PersonalFinanceModelSerializer());
        addDeserializer(PersonalFinanceModel.class, new PersonalFinanceModelDeserializer());
    }

    public PersonalFinanceModule() {
        this(EnumSet.allOf(PersonalFinanceModelParts.class));
    }
}
