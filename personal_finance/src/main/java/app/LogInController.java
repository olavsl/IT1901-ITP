package app;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class LogInController extends SceneSwitcher {

    private User user;
    
    @FXML private TextField username;
    @FXML private TextField password;

    @FXML
    public void switchToGeneral(ActionEvent event) throws IOException {
        switchToGeneral(event, this.user);
    }

    public void setUser(User user) {
        this.user = user;
    }
}
