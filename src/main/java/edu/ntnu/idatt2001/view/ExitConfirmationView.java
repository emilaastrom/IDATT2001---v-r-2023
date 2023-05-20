package edu.ntnu.idatt2001.view;

import edu.ntnu.idatt2001.Main;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * The ExitConfirmationView class responsible for visualizing the exit confirmation.
 */
public class ExitConfirmationView {
  private BorderPane dimmer;
  private Stage exitConfirmationStage = new Stage();
  private BorderPane exitConfirmationRoot = new BorderPane();
  private Scene exitConfirmationScene = new Scene(exitConfirmationRoot, 500, 150);
  private Button exitConfirmationCancelButton = new Button("Cancel");
  private Button exitConfirmationButton = new Button("Confirm");

  /**
   * Instantiates a new Exit confirmation view.
   *
   * @param dimmer the dimmer
   */
  public ExitConfirmationView(BorderPane dimmer) {
    this.dimmer = dimmer;
    exitConfirmationStage.setTitle("Exit confirmation");
    exitConfirmationStage.initModality(Modality.APPLICATION_MODAL);
    exitConfirmationStage.setResizable(false);
    exitConfirmationStage.initStyle(javafx.stage.StageStyle.UNDECORATED);
    exitConfirmationRoot.setId("ExitConfirmationRoot");
    exitConfirmationStage.setScene(exitConfirmationScene);
    exitConfirmationButton.setId("ExitConfirmationButton");
    exitConfirmationButton.setMaxWidth(150);
    exitConfirmationCancelButton.setMaxWidth(150);
    HBox exitConfirmationButtonBox = new HBox();
    exitConfirmationButtonBox.getChildren().addAll(
            exitConfirmationButton,
            exitConfirmationCancelButton);
    exitConfirmationButtonBox.setAlignment(Pos.CENTER);
    exitConfirmationButtonBox.setSpacing(20);
    exitConfirmationRoot.setCenter(exitConfirmationButtonBox);
    exitConfirmationScene.getStylesheets().add("file:src/main/resources/maintheme.css");
  }

  /**
   * Display exit confirmation.
   */
  public void exitConfirmation() {
    exitConfirmationButton.setOnAction(event -> System.exit(0));
    exitConfirmationCancelButton.setOnAction(event -> {
      exitConfirmationStage.close();
      dimmer.setVisible(false);
    });
    exitConfirmationStage.addEventHandler(WindowEvent.WINDOW_HIDDEN, windowEvent ->
            dimmer.setVisible(false));
    exitConfirmationStage.show();
    dimmer.setVisible(true);
  }

  /**
   * Main menu confirmation.
   */
  public void mainMenuConfirmation() {
    exitConfirmationButton.setOnAction(event -> {
      Main.mainMenu();
      exitConfirmationStage.close();
      dimmer.setVisible(false);
      Main.updateStage();
    });
    exitConfirmationStage.close();
    exitConfirmationCancelButton.setOnAction(event -> {
      dimmer.setVisible(false);
      exitConfirmationStage.close();
    });
    exitConfirmationStage.addEventHandler(WindowEvent.WINDOW_HIDDEN, windowEvent ->
            dimmer.setVisible(false));
    System.out.println("showing");
    dimmer.setVisible(true);
    exitConfirmationStage.show();
  }
}