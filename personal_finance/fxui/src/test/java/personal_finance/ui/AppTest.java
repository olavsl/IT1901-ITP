package personal_finance.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
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
import org.testfx.service.query.NodeQuery;

import javafx.scene.Node;
import javafx.scene.control.Labeled;

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

    @Test
    public void testLogInScreen() {
        clickOn("#logOutBtn");
        clickOn("#username").write("wrong");
        clickOn("#password").write("wrong");
        clickOn("#logInBtn");

        Label wrongLogInFeedback = (Label)lookup("#wrongLogInFeedback").queryLabeled();

        assertTrue(wrongLogInFeedback.getText().equals("No user with these credentials"));
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
        clickOn("#addBtn");

        clickOn("#transactionTitle").write("test");
        clickOn("#transactionAmount").write("100");
        clickOn("#saveBtn");

        Label userFeedback = (Label)lookup("#userFeedback").queryLabeled();

        assertTrue(userFeedback.getText().equals("Transaction added successfully"));

        clickOn("#transactionTitle").eraseText(5).write("");
        clickOn("#saveBtn");

        assertFalse(userFeedback.getText().equals("Transaction added successfully"));

        clickOn("#transactionTitle").write("test");
        clickOn("#transactionAmount").eraseText(5).write("badInput");
        clickOn("#saveBtn");

        assertFalse(userFeedback.getText().equals("Transaction added successfully"));
    }

    @Test
    public void testBudget() {
        clickOn("#budgetBtn");

        Label userFeedback = (Label)lookup("#userFeedback").queryLabeled();

        clickOn("#setBtn");
        assertTrue(userFeedback.getText().equals("Budget start date successfully set"));
        
        clickOn("#budgetStartDate").write("11/1/2022");
        clickOn("#setBtn");

        assertTrue(userFeedback.getText().equals("Budget start date successfully changed"));

        clickOn("#categoryTitle").write("test");
        clickOn("#categoryLimit").write("1000");
        clickOn("#saveBtn");

        assertTrue(userFeedback.getText().equals("Successfully added category"));

        clickOn("#categoryTitle").eraseText(5).write("");
        clickOn("#saveBtn");

        assertFalse(userFeedback.getText().equals("Successfully added category"));

        clickOn("#categoryTitle").write("test");
        clickOn("#categoryLimit").eraseText(5).write("badInput");
        clickOn("#saveBtn");

        assertFalse(userFeedback.getText().equals("Successfully added category"));
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
