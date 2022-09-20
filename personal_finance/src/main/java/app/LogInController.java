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

//Siden ingen proper log in system finnes akkurat nå lar jeg den være tom
public class LogInController {

    private User user;

    private Stage stage;
    private Scene scene;
    private Parent root;
    
    @FXML private TextField username;
    @FXML private TextField password;

    public LogInController() {

    }

    //metode som bytter mellom scener
    @FXML
    public void switchToCreateUser(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("CreateUser.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    public void switchToGeneral(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("General.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
