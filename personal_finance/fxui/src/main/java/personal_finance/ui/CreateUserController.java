package personal_finance.ui;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import personal_finance.core.User;
import personal_finance.core.PersonalFinanceModel;
import personal_finance.json.PersonalFinancePersistence;
import personal_finance.util.PasswordHasher;

public class CreateUserController extends SceneSwitcher {

    private PersonalFinancePersistence pfp = new PersonalFinancePersistence();

    @FXML private TextField username;
    @FXML private TextField password;
    @FXML private TextField confirmedPassword;
    @FXML private Label createUserFeedback;

    @FXML
    public void createUser() throws IOException, NoSuchAlgorithmException {
        User user;
        PersonalFinanceModel model;
        pfp.setStorageFile("users.json");

        try {
            model = pfp.loadPersonalFinanceModel();
        } catch (Exception e) {
            List<User> emptyList = new ArrayList<>();
            model = new PersonalFinanceModel(emptyList);
        }

        for (User u : model.getUsers()) {
            if (u.getUsername().equals(username.getText())) {
                createUserFeedback.setText("Username is already taken!");
                throw new IllegalArgumentException("Username is already taken!");
            }
        }

        if (!password.getText().equals(confirmedPassword.getText())) {
            createUserFeedback.setText("Passwords don't match!");
            throw new IllegalArgumentException("Passwords don't match!");
        }
        
        user = new User(username.getText(), PasswordHasher.hash(password.getText()));

        model.addUser(user);

        pfp.savePersonalFinanceModel(model);

        createUserFeedback.setText("New user created! Login to access your profile :)");
    }

}
