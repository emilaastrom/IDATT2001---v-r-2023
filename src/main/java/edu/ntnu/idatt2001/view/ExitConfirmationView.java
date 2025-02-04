package edu.ntnu.idatt2001.view;

import edu.ntnu.idatt2001.Main;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * The ExitConfirmationView class responsible for visualizing the exit confirmation.
 */
public class ExitConfirmationView {
  private final BorderPane dimmer;
  private final Stage exitConfirmationStage = new Stage();
  private final Button exitConfirmationCancelButton = new Button("Cancel");
  private final Button exitConfirmationButton = new Button("Confirm");

  /**
   * Instantiates a new Exit confirmation view.
   *
   * @param dimmer the dimmer
   */
  public ExitConfirmationView(BorderPane dimmer) {
    this.dimmer = dimmer;

    //Creating and configuring exit confirmation stage and scene
    exitConfirmationStage.getIcons().add(new Image(Main.class.getResource("/icons/settings.png").toString()));
    BorderPane exitConfirmationRoot = new BorderPane();
    exitConfirmationStage.setTitle("Exit confirmation");
    exitConfirmationStage.initModality(Modality.APPLICATION_MODAL);
    exitConfirmationStage.setResizable(false);
    exitConfirmationStage.initStyle(javafx.stage.StageStyle.UNDECORATED);
    Scene exitConfirmationScene = new Scene(exitConfirmationRoot, 500, 150);
    exitConfirmationRoot.setId("ExitConfirmationRoot");
    exitConfirmationStage.setScene(exitConfirmationScene);
    exitConfirmationButton.setId("ExitConfirmationButton");
    exitConfirmationButton.setMaxWidth(150);
    exitConfirmationCancelButton.setMaxWidth(150);

    //Creating and configuring a HBox for containing the two buttons
    HBox exitConfirmationButtonBox = new HBox();
    exitConfirmationButtonBox.getChildren().addAll(
            exitConfirmationButton,
            exitConfirmationCancelButton);
    exitConfirmationButtonBox.setAlignment(Pos.CENTER);
    exitConfirmationButtonBox.setSpacing(20);
    exitConfirmationRoot.setCenter(exitConfirmationButtonBox);
    exitConfirmationScene.getStylesheets().add(Main.class.getResource("/maintheme.css").toString());

    //Ensuring the dimmer for the background stage enabled only when confirmation stage is showing
    exitConfirmationStage.addEventHandler(WindowEvent.WINDOW_HIDDEN, windowEvent ->
            dimmer.setVisible(false));
    exitConfirmationStage.addEventHandler(WindowEvent.WINDOW_SHOWING, windowEvent ->
            dimmer.setVisible(true));
  }

  /**
   * Display exit confirmation.
   */
  public void exitConfirmation() {
    //Setting buttons to either exit or cancel - and showing stage
    exitConfirmationButton.setOnAction(event -> System.exit(0));
    exitConfirmationCancelButton.setOnAction(event -> {
      exitConfirmationStage.close();
      dimmer.setVisible(false);
    });
    exitConfirmationStage.show();
  }

  /**
   * Main menu confirmation.
   * @param root the root of the main menu
   * @param dimmer the dimmer
   */
  public void mainMenuConfirmation(Pane root, BorderPane dimmer) {
    //Setting buttons to either go to main menu or cancel - and showing stage
    exitConfirmationButton.setOnAction(event -> {
      Main.changeScene(root);
      exitConfirmationStage.close();
      dimmer.setVisible(false);
      Main.updateStage();
    });
    exitConfirmationCancelButton.setOnAction(event -> {
      dimmer.setVisible(false);
      exitConfirmationStage.close();
    });
    exitConfirmationStage.show();
  }
}
