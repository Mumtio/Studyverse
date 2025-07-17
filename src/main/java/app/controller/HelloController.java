package app.controller;

import app.util.DBUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class HelloController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;
    @FXML
    private Label messageLabel;

    @FXML
    public void handleLogin(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();


        if (username.isEmpty() || password.isEmpty()) {
            messageLabel.setText("All fields required!");
            return;
        }

        try (Connection conn = DBUtil.getConnection()) {
            String sql = "SELECT * FROM students WHERE name = ? AND password = ?";
            PreparedStatement stmt = ((java.sql.Connection) conn).prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                messageLabel.setText("Login successful!");

                int studentId = rs.getInt("id");
                String fullName = rs.getString("name");


                List<Course> enrolled = DBUtil.getEnrolledCourses(String.valueOf(studentId));
                List<Course> notEnrolled = DBUtil.getNotEnrolledCourses(String.valueOf(studentId));


                CurrentSession.initSession(studentId, fullName, enrolled, notEnrolled);

                switchToMainScene(event);

            } else {
                messageLabel.setText("Invalid credentials");
            }

        } catch (SQLException e) {
            messageLabel.setText("Error connecting to DB");
            e.printStackTrace();
        }
    }


    /*private void switchToSignUp(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("signup-view.fxml"));
            Scene signUpScene = new Scene(loader.load());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(signUpScene);
            stage.setTitle("PrepVerse - Sign Up");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    public void switchToSignUp(javafx.scene.input.MouseEvent event) {
        try {
            //System.out.println("yoooooooooooooooooooooo" + getClass().getResource("signup-view.fxml"));

            FXMLLoader loader = new FXMLLoader(getClass().getResource("signup-view.fxml"));
            Scene signUpScene = new Scene(loader.load());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(signUpScene);
            stage.setTitle("PrepVerse - Sign Up");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void switchToMainScene(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/javafxproject/main.fxml")); // path to your main scene
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Welcome to Prepverse!");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
