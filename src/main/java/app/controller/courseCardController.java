package app.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class courseCardController {

    @FXML
    private Label titleLabel;

    @FXML
    private Label descLabel;

    @FXML
    private Button enrollButton;

    @FXML
    private Button removeButton;

    @FXML
    private Button detailsButton;


    public void setCourse(String title, String description, boolean isEnrolled,
                          Runnable onEnroll, Runnable onRemove, Runnable onDetails) {
        titleLabel.setText(title);
        descLabel.setText(description);

        if (isEnrolled) {
            enrollButton.setVisible(false);
            enrollButton.setManaged(false);

            removeButton.setVisible(true);
            removeButton.setManaged(true);

            detailsButton.setVisible(true);
            detailsButton.setManaged(true);

            removeButton.setOnAction(e -> {
                if (onRemove != null) onRemove.run();
            });

            detailsButton.setOnAction(e -> {
                if (onDetails != null) onDetails.run();
            });

        } else {
            enrollButton.setVisible(true);
            enrollButton.setManaged(true);

            removeButton.setVisible(false);
            removeButton.setManaged(false);

            detailsButton.setVisible(false);
            detailsButton.setManaged(false);

            enrollButton.setOnAction(e -> {
                if (onEnroll != null) onEnroll.run();
            });
        }
    }
}
