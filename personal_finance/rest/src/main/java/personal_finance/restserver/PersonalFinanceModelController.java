package personal_finance.restserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import personal_finance.core.PersonalFinanceModel;
import personal_finance.core.User;

@RestController
@RequestMapping(PersonalFinanceModelController.PERSONALFINANCE_MODEL_SERVICE_PATH)
public class PersonalFinanceModelController {

    public static final String PERSONALFINANCE_MODEL_SERVICE_PATH = "personalFinance";

    @Autowired
    private PersonalFinanceModelService modelService;
   
    @GetMapping
    public PersonalFinanceModel getPersonalFinanceModel() {
        return modelService.getPersonalFinanceModel();
    }

    private void savePersonalFinanceModel() {
        modelService.savePersonalFinanceModel();
    }

    /**
     * Checks if a user with the parsed username exists.
     * 
     * @param user
     * @param username
     * @throws IllegalArgumentException
     */
    private void checkIfUserExists(User user, String username) {
        if (user == null) {
            throw new IllegalArgumentException("There exists no user with the name \"" + username + "\"!");
        }
    }

    /**
     * Gets the user corresponding to the parsed username.
     * 
     * @param username
     * @return the corresponding user
     */
    @GetMapping
    public User getUser(@PathVariable("user") String username) {
        User user = getPersonalFinanceModel().getUser(username);
        checkIfUserExists(user, username);
        return user;
    }

    /**
     * Checks if a user with parsed username already exists. If so,
     * then the user is swapped out (updated) with the "new" user object.
     * 
     * @param username
     * @param user
     * @return true if a new user was added
     */
    @PutMapping
    public boolean putUser(@PathVariable("user") String username, @RequestBody User user) {
        boolean addedNewUser = getPersonalFinanceModel().putUser(user) == null;
        savePersonalFinanceModel();
        return addedNewUser;
    }

}
