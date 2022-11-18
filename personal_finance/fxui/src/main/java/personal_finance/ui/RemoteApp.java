package personal_finance.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Launch the project on RESTful server.
 * 
 * How to launch the app:
 * 1. cd into project-folder "personal_finance"
 * 2. Run the command "mvn install". If you can't install because of test
 * failures, try running "mvn install -DskipTests"
 * 3. Run the command "mvn compile"
 * 4. Open another terminal. (Go to "Terminal" and choose "Split Terminal")
 * 5. In the first terminal:
 * a) cd into "rest"-folder
 * b) Run the command "mvn spring-boot:run"
 * 6. In the seconds terminal:
 * a) Run the command "mvn -Premoteapp -pl fxui javafx:run"
 * 7. Now the app should appear on your screen. Database (JSON) can be viewed in
 * the web browser by going to "http://localhost:8080/personal_finance/"
 */
public class RemoteApp extends Application {

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
    launch(RemoteApp.class, args);
  }
}