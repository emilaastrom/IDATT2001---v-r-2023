package edu.ntnu.idatt2001.controller;

import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.stage.Modality;

import static edu.ntnu.idatt2001.Main.currentStylesheet;

/**
 * Is responsible for informing the user of various important things.
 */
public class UserInformer {

  /**
   * Informs the user that an error has occurred.
   *
   * @param title The title of the error
   * @param body The body of the error
   */
  public static void errorWarning(String title, String body) {
    if (title.strip().length() == 0) {
      throw new IllegalArgumentException("The title must be defined");
    }
    if (body.strip().length() == 0) {
      throw new IllegalArgumentException("The body must be defined");
    }

    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("An error has occurred");
    alert.setHeaderText(title);
    alert.setContentText(body);
    alert.initModality(Modality.APPLICATION_MODAL);

    DialogPane dialogPane = alert.getDialogPane();
    dialogPane.setMinWidth(500);
    dialogPane.setMaxWidth(500);
    dialogPane.getStylesheets().add(currentStylesheet);
    dialogPane.setId("DialogPane");
    dialogPane.getStyleClass().add("DialogPane");

    alert.showAndWait();
  }

  /**
   * Informs the user of a normal warning.
   *
   * @param title The title of the warning
   * @param body The body of the warning
   */
  public static void normalWarning(String title, String body) {
    if (title.strip().length() == 0) {
      throw new IllegalArgumentException("The title must be defined");
    }
    if (body.strip().length() == 0) {
      throw new IllegalArgumentException("The body must be defined");
    }

    Alert alert = new Alert(Alert.AlertType.WARNING);
    alert.setTitle("Advarsel");
    alert.setHeaderText(title);
    alert.setContentText(body);

    alert.showAndWait();
  }
}
