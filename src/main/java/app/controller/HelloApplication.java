package app.controller;

import app.util.DBUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
//"/com/example/javafxproject/main.fxml"

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/javafxproject/signup-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);
        stage.setTitle("PrepVerse");
        stage.setScene(scene);
        stage.show();
        try (Connection conn = DBUtil.getConnection()) {
            System.out.println("✅ Connected to Neon DB!");
        } catch (SQLException e) {
            System.out.println("❌ Connection failed: " + e.getMessage());
        }
    }



    public static void main(String[] args) {
        launch();


    }
}