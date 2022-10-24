package personal_finance.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

public class AppTest extends ApplicationTest {

    private LogInController controller;

    @Override
    public void start(final Stage stage) throws Exception {
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("LogIn.fxml"));
        final Parent root = loader.load();
        this.controller = loader.getController();
        stage.setScene(new Scene(root));
        stage.show();
}

    @Test
    public void testController() {
        assertNotNull(this.controller);
    }

    @BeforeEach
    public void makeUserAndLogIn() {
// //creates user "test"
//       clickOn("#signUpBtn");
//       clickOn("#username").write("test");
//       clickOn("#password").write("test");
//       clickOn("#confirmedPassword").write("test");
//       clickOn("#createBtn");
//       clickOn("#logInBtn");
        logIn();
    }

    //presses all possible combination of buttons
    @Test
    public void testMenuBtns() {
          List<String> btns = new ArrayList<String>(Arrays.asList("#overviewBtn","#addBtn","#budgetBtn"));
          for (  String x : btns) {
              clickOn(x);
              for (  String y : btns) {
                  clickOn(y);
                  clickOn(x);
              }
              clickOn("#logOutBtn");
              logIn();
          }
      }

      private void logIn() {
        //logs in with the user "test"
          clickOn("#username").write("test");
          clickOn("#password").write("test");
          clickOn("#logInBtn");
      }
  }
