package edu.ntnu.idatt2001.controller;

import edu.ntnu.idatt2001.Main;
import edu.ntnu.idatt2001.model.Game;
import javafx.scene.layout.Pane;

/**
 * Controller for SettingsView.
 */
public class SettingsController {
  private final Pane mainMenuRoot;

  /**
   * Instantiates a new SettingsController.
   *
   * @param mainMenuRoot main menu root
   */
  public SettingsController(Pane mainMenuRoot) {
    this.mainMenuRoot = mainMenuRoot;
  }

  /**
   * Shows the main menu.
   */
  public void showMainMenu() {
    Main.showExitConfirmation();
  }

  /**
   * Restarts the game.
   */
  public void restartGame() {
    Game.getInstance().restartGame();
  }
}
