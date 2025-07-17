package app.controller;

import app.util.Client;
import app.util.DBUtil;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;

public class addCourses {

    @FXML
    private VBox notEnrolledGrid;
    @FXML
    private ScrollPane scrollPane;


    @FXML
    public void initialize() {
        List<Course> availableCourses = CurrentSession.getNotEnrolledCourses();

        int column = 0;
        int row = 0;

        for (Course course : availableCourses) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/javafxproject/courseCard.fxml"));



                AnchorPane card = loader.load();

                courseCardController cardController = loader.getController();
                cardController.setCourse(
                        course.getTitle(),
                        course.getDescription(),
                        false, // false because this is from "not enrolled" list
                        new Runnable() {
                            public void run() {
                                Client.enrollCourse(CurrentSession.getStudentId(), course.getId()); // use socket
                                notEnrolledGrid.getChildren().remove(card); // remove card from UI
                            }
                        },
                        null,
                        null


                );


                notEnrolledGrid.getChildren().add(card);


                column++;
                if (column == 2) {
                    column = 0;
                    row++;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
