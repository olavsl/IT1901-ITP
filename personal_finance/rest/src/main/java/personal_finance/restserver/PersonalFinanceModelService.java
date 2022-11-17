package personal_finance.restserver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import personal_finance.core.PersonalFinanceModel;
import personal_finance.core.User;
import personal_finance.json.PersonalFinancePersistence;

@Service
public class PersonalFinanceModelService {

    private PersonalFinanceModel model;
    private PersonalFinancePersistence pfp;

    public PersonalFinanceModelService(PersonalFinanceModel model) {
        this.model = model;
        this.pfp = new PersonalFinancePersistence();
        try {
            pfp.setStorageFile("springbootserver-users.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public PersonalFinanceModelService() {
        this(createPersonalFinanceModel());
    }

    private static PersonalFinanceModel createPersonalFinanceModel() {
        List<User> users = new ArrayList<>();
        return new PersonalFinanceModel(users);
    }

    public void savePersonalFinanceModel() {
        if (pfp != null) {
            try {
                pfp.savePersonalFinanceModel(this.model);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public PersonalFinanceModel getPersonalFinanceModel() {
        return this.model;
    }

}