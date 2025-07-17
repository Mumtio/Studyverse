module com.example.javafxproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;
    requires jdk.jdi;

    exports app.controller;
    opens app.controller to javafx.fxml;
    exports app.util;
    opens app.util to javafx.fxml;
}
