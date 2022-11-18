package personal_finance.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

  @Override
  public void start(Stage stage) {
    try {

      Parent root = FXMLLoader.load(getClass().getResource("LogIn.fxml"));
      Scene scene = new Scene(root);
      stage.setTitle("Personal finance");
      stage.setScene(scene);
      stage.show();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    launch(args);
  }
}