package app.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;



public class courses {

    @FXML
    private GridPane enrolledGrid;


    public void initialize() {
        List<Course> enrolledCourses = CurrentSession.getEnrolledCourses();

        int column = 0;
        int row = 0;

        for (Course course : enrolledCourses) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/javafxproject/courseCard.fxml"));
                AnchorPane card = loader.load();

                courseCardController cardController = loader.getController();

                cardController.setCourse(
                        course.getTitle(),
                        course.getDescription(),
                        true, // isEnrolled

                        null, // No enroll button

                        new Runnable() { // Remove button logic
                            @Override
                            public void run() {
                                System.out.println("Removing course: " + course.getTitle());
                                // TODO: Add logic to remove from DB and refresh UI
                            }
                        },

                        new Runnable() { // Details button logic
                            @Override
                            public void run() {
                                System.out.println("Showing details for course: " + course.getTitle());
                                // TODO: Add logic to show course details popup or new scene
                            }
                        }
                );

                enrolledGrid.add(card, column, row);
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

