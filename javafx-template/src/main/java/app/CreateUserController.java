package app;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;

public class CreateUserController {

    private User user;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML private TextField username;
    @FXML private TextField password;
    @FXML private TextField confirmedPassword;
    @FXML private Label createUserFeedback;

    public CreateUserController() {

    }

//metode som bytter mellom scener
    @FXML
    public void switchToLogIn(ActionEvent event) throws IOException{
        //for testing purpuses this will swithc to General instead until some sort of persistent storage is arranged
        FXMLLoader loader = new FXMLLoader(getClass().getResource("General.fxml"));
        root = loader.load();

        GeneralController controller = loader.getController();
        controller.setUser(this.user);

        //Parent root = FXMLLoader.load(getClass().getResource("LogIn.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void createUser() {
        if (!password.getText().equals(confirmedPassword.getText())) {
            createUserFeedback.setText("Passwords don't match!");
        }
        
        this.user = new User(username.getText(), password.getText(), confirmedPassword.getText());
        createUserFeedback.setText("New user created! Login to access your profile :)");
    }

}
