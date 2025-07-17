package app.controller;

import app.util.DBUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.FileWriter;


import java.io.BufferedWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class signupcontroller {

    @FXML
    private TextField usernameField;
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;

    @FXML
    private Label messageLabel;



    public void handleSignup(ActionEvent actionEvent) {
        String username = usernameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();

        if(username.isEmpty()||email.isEmpty()||password.isEmpty()) {
            System.out.println("Please fill in all fields");
            return;
        }
        /*try (BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt", true))) {
            writer.write(username + "," + password);
            writer.newLine();
            showAlert("Success", "User registered successfully!");
            usernameField.clear();
            passwordField.clear();
        } catch (IOException e) {
            showAlert("Error", "Failed to save user");
            e.printStackTrace();
        }*/

        try (Connection conn = DBUtil.getConnection()) {
            String sql = "INSERT INTO students (name, email, password) VALUES (?, ?, ?)";
            PreparedStatement stmt = ((java.sql.Connection) conn).prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, email);
            stmt.setString(3,password);
            stmt.executeUpdate();
            messageLabel.setText("Signup successful!");
        } catch (SQLException e) {
            if (e.getMessage().contains("duplicate key")) {
                messageLabel.setText("Username already exists.");
            } else {
                messageLabel.setText("Signup failed.");
                e.printStackTrace();
            }
        }


        messageLabel.setText("Account created successfully!");
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void switchToLogin(MouseEvent mouseEvent) {
        try {
            //System.out.println("yoooooooooooooooooooooo" + getClass().getResource("signup-view.fxml"));

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/javafxproject/Login.fxml"));
            Scene LoginScene = new Scene(loader.load());
            Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            stage.setScene(LoginScene);
            stage.setTitle("PrepVerse - Login");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



