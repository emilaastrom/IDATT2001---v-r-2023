package edu.ntnu.idatt2001.View;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class ExitConfirmationView {
  BorderPane dimmer;
  Stage exitConfirmationStage = new Stage();
  BorderPane exitConfirmationRoot = new BorderPane();
  Scene exitConfirmationScene = new Scene(exitConfirmationRoot, 500, 150);

  Button exitConfirmationCancelButton = new Button("Cancel");

  Button exitConfirmationButton = new Button("Exit");
  public ExitConfirmationView(BorderPane dimmer) {
    this.dimmer = dimmer;
    dimmer.setVisible(true);
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
    exitConfirmationButtonBox.getChildren().addAll(exitConfirmationButton, exitConfirmationCancelButton);
    exitConfirmationButtonBox.setAlignment(Pos.CENTER);
    exitConfirmationButtonBox.setSpacing(20);
    exitConfirmationRoot.setCenter(exitConfirmationButtonBox);
    exitConfirmationScene.getStylesheets().add("file:src/main/resources/maintheme.css");
    exitConfirmationStage.show();
  }

  public void ExitConfirmation() {
    exitConfirmationButton.setOnAction(event -> System.exit(0));
    exitConfirmationCancelButton.setOnAction(event -> {
      dimmer.setVisible(false);
      exitConfirmationStage.close();
    });
    exitConfirmationStage.addEventHandler(WindowEvent.WINDOW_HIDDEN, windowEvent -> dimmer.setVisible(false));
  }

  public void MainMenuConfirmation() {
    exitConfirmationButton.setOnAction(event -> System.exit(0));
    exitConfirmationStage.close();
    exitConfirmationCancelButton.setOnAction(event -> {
      dimmer.setVisible(false);
      exitConfirmationStage.close();
    });
    exitConfirmationStage.addEventHandler(WindowEvent.WINDOW_HIDDEN, windowEvent -> dimmer.setVisible(false));
  }
}
