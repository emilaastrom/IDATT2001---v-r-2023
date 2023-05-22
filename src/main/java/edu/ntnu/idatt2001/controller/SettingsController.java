package edu.ntnu.idatt2001.controller;

import edu.ntnu.idatt2001.Main;
import edu.ntnu.idatt2001.model.Game;
import edu.ntnu.idatt2001.view.PathsView;
import javafx.scene.layout.Pane;

/**
 * Controller for SettingsView.
 */
public class SettingsController {
  private final Pane mainMenuRoot;
  private final Pane pathsRoot;

  /**
   * Instantiates a new SettingsController.
   *
   * @param mainMenuRoot main menu root
   */
  public SettingsController(Pane mainMenuRoot, Pane pathsRoot) {
    this.mainMenuRoot = mainMenuRoot;
    this.pathsRoot = pathsRoot;
  }

  /**
   * Shows the main menu.
   */
  public void showMainMenu(PathsView pathsView) {
    Main.showExitConfirmation(pathsView);
  }

  /**
   * Restarts the game.
   */
  public void restartGame() {
    Game.getInstance().restartGame();
  }

  /**
   * Gets main menu root.
   */
  public Pane getMainMenuRoot() {
    return mainMenuRoot;
  }

  /**
   * Gets pathsView (game screen) root.
   */
  public Pane getPathsRoot() {
    return pathsRoot;
  }
}
