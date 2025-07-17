package app.controller;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;


import java.io.IOException;

public class mainController {

    @FXML
    private StackPane centerContent;
    @FXML
    private Text nameText;

    public void loadView(String fxmlFileName) {
        try {
            Parent view = FXMLLoader.load(getClass().getResource("/com/example/javafxproject/" + fxmlFileName));
            centerContent.getChildren().setAll(view);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleDashboardClick()
    {
        loadView("dashboard.fxml");
    }

    public void handleCoursesClick()
    {
        loadView("courses.fxml");
    }

    public void handleAddCoursesClick()
    {
        loadView("addCourses.fxml");
    }

    @FXML
    public void initialize() {
        String studentName = CurrentSession.getName();
        nameText.setText(studentName);  // This sets your <Text> node
    }
}
