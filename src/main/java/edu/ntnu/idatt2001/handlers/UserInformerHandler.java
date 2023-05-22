package edu.ntnu.idatt2001.handlers;

import static edu.ntnu.idatt2001.Main.currentStylesheet;

import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.stage.Modality;


/**
 * Is responsible for informing the user of various important things.
 */
public class UserInformerHandler {

  /**
   * Informs the user that an error has occurred.
   *
   * @param title The title of the error
   * @param body The body of the error
   */
  public static void errorWarning(String title, String body) {
    // Check if the title or body is empty
    if (title.strip().length() == 0) {
      throw new IllegalArgumentException("The title must be defined");
    }
    if (body.strip().length() == 0) {
      throw new IllegalArgumentException("The body must be defined");
    }

    // Create alert
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("An error has occurred");
    // Set header text and content text
    alert.setHeaderText(title);
    alert.setContentText(body);
    alert.initModality(Modality.APPLICATION_MODAL);

    //set the width of the dialog pane
    DialogPane dialogPane = alert.getDialogPane();
    dialogPane.setMinWidth(500);
    dialogPane.setMaxWidth(500);
    // Set the stylesheet
    dialogPane.getStylesheets().add(currentStylesheet);
    dialogPane.setId("DialogPane");
    dialogPane.getStyleClass().add("DialogPane");

    // Show the alert
    alert.showAndWait();
  }
}
