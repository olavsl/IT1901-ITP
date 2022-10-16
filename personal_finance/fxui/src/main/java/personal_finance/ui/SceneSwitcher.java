package personal_finance.ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import personal_finance.core.User;

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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LogIn.fxml"));
        Parent root = loader.load();
        switchScene(event, stage, scene, root);
    }

    @FXML
    public void switchToOverview(ActionEvent event, User user) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Overview.fxml"));
        Parent root = loader.load();
        OverviewController oc = loader.getController();
        oc.setUser(user);
        oc.updateDisplayname();
        oc.updateTransactionOverview();
        oc.updateTotals();
        switchScene(event, stage, scene, root);
    }

    @FXML
    public void switchToAddTransaction(ActionEvent event, User user) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddTransaction.fxml"));
        Parent root = loader.load();
        AddTransactionController ac = loader.getController();
        ac.setUser(user);
        ac.updateDisplayname();
        switchScene(event, stage, scene, root);
    }

    @FXML
    public void switchToBudget(ActionEvent event, User user) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Budget.fxml"));
        Parent root = loader.load();
        BudgetController bc = loader.getController();
        bc.setUser(user);
        bc.updateDisplayname();
        switchScene(event, stage, scene, root);
    }
}
