module personal_finance.ui {
    requires transitive com.fasterxml.jackson.databind;

    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;

    requires personal_finance.core;

    opens personal_finance.ui to javafx.graphics, javafx.fxml, javafx.base;
}
