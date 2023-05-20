package edu.ntnu.idatt2001.controller;

import edu.ntnu.idatt2001.Main;
import edu.ntnu.idatt2001.view.ExitConfirmationView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 * Controller for the main menu.
 */
public class MainMenuController {
  Pane gameSelectionRoot;
  ExitConfirmationView exitConfirmationView;

  /**
   * Instantiates a new MainMenuController.
   *
   * @param gameSelectionRoot game selection root
   * @param exitConfirmationView exit confirmation view
   */
  public MainMenuController(Pane gameSelectionRoot, ExitConfirmationView exitConfirmationView) {
    this.gameSelectionRoot = gameSelectionRoot;
    this.exitConfirmationView = exitConfirmationView;
  }

  /**
   * Shows the game selection.
   */
  public void showSettings(BorderPane dimmer) {
    Main.showMainMenuSettings();
    dimmer.setVisible(true);
  }

  /**
   * Shows exit confirmation.
   */
  public void showExitConfirmation(BorderPane dimmer) {
    dimmer.setVisible(true);
    exitConfirmationView.exitConfirmation();
  }

  public void showHelp() {
    Main.showHelp();
  }
}