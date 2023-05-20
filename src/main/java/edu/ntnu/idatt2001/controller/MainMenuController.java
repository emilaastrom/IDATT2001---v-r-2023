package edu.ntnu.idatt2001.controller;

import edu.ntnu.idatt2001.Main;
import edu.ntnu.idatt2001.view.ExitConfirmationView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class MainMenuController {
  Pane gameSelectionRoot;
  ExitConfirmationView exitConfirmationView;
  public MainMenuController(Pane gameSelectionRoot, ExitConfirmationView exitConfirmationView){
    this.gameSelectionRoot = gameSelectionRoot;
    this.exitConfirmationView = exitConfirmationView;
  }

  public void showGameSelection() {
    Main.changeScene(gameSelectionRoot);
  }

  public void showSettings(Pane root, BorderPane dimmer) {
    Main.showMainMenuSettings();
    dimmer.setVisible(true);
  }

  public void showExitConfirmation(BorderPane dimmer) {
    exitConfirmationView.ExitConfirmation();
  }
}