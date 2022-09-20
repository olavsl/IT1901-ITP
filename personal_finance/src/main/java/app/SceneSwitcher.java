package app;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneSwitcher {
    
    private Stage stage;
    private Scene scene;
    private Parent parent;

    @FXML
    public void switchScene(ActionEvent event, Stage stage, Scene scene, Parent parent) throws IOException{
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void switchToCreateUser(ActionEvent event) throws IOException{
        parent = FXMLLoader.load(getClass().getResource("CreateUser.fxml"));
        switchScene(event, stage, scene, parent);
    }

    @FXML
    public void switchToLogIn(ActionEvent event) throws IOException{
        parent = FXMLLoader.load(getClass().getResource("LogIn.fxml"));
        switchScene(event, stage, scene, parent);
    }

    @FXML
    public void switchToGeneral(ActionEvent event, User user) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("General.fxml"));
        Parent root = loader.load();
        GeneralController gc = loader.getController();
        gc.setUser(user);
        switchScene(event, stage, scene, root);
    }

}
