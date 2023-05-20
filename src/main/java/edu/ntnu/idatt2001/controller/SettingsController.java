package edu.ntnu.idatt2001.controller;

import edu.ntnu.idatt2001.Main;
import edu.ntnu.idatt2001.model.Game;
import javafx.scene.layout.Pane;

/**
 * Controller for SettingsView.
 */
public class SettingsController {
  private Pane mainMenuRoot;

  public SettingsController(Pane mainMenuRoot) {
    this.mainMenuRoot = mainMenuRoot;
  }


  public void showMainMenu() {
    Main.changeScene(mainMenuRoot);
  }

  public void restartGame() {
    Game.getInstance().restartGame();
  }
}
