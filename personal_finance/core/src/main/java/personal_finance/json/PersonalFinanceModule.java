package personal_finance.json;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.util.EnumSet;
import java.util.Set;
import personal_finance.core.Budget;
import personal_finance.core.Category;
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

    addSerializer(Category.class, new CategorySerializer());
    addDeserializer(Category.class, new CategoryDeserializer());

    addSerializer(Budget.class, new BudgetSerializer());
    addDeserializer(Budget.class, new BudgetDeserializer());
  }

  public PersonalFinanceModule() {
    this(EnumSet.allOf(PersonalFinanceModelParts.class));
  }
}
