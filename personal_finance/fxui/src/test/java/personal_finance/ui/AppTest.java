package personal_finance.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import personal_finance.util.UserCreater;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
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
//creates user "test"
      clickOn("#signUpBtn");
      clickOn("#username").write("test");
      clickOn("#password").write("test");
      clickOn("#confirmedPassword").write("test");
      clickOn("#createBtn");
      clickOn("#logInBtn");
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

    @Test
    public void testAddTransaction() {
        logIn();

        clickOn("#addBtn");

        clickOn("#transactionTitle").write("test");
        clickOn("#transactionAmount").write("100");
        clickOn("#saveBtn");

        Label userFeedback = (Label)lookup("#userFeedback");

        assertTrue(userFeedback.getText().equals("Transaction added successfully"));

        clickOn("#transactionTitle").write("");
        clickOn("#transactionAmount").write("100");
        clickOn("#saveBtn");

        assertFalse(userFeedback.getText().equals("Transaction added successfully"));

        clickOn("#transactionTitle").write("test");
        clickOn("#transactionAmount").write("badInput");
        clickOn("#saveBtn");

        assertFalse(userFeedback.getText().equals("Transaction added successfully"));
    }

    @AfterEach
    public void cleanUp() throws NoSuchAlgorithmException, IOException {
        UserCreater.deleteUser("test", "users.json");
    }
    private void logIn() {
        //logs in with the user "test"
          clickOn("#username").write("test");
          clickOn("#password").write("test");
          clickOn("#logInBtn");
      }
  }
