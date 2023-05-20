package edu.ntnu.idatt2001.controller;

import edu.ntnu.idatt2001.Main;
import edu.ntnu.idatt2001.model.Game;
import javafx.scene.layout.Pane;

public class SettingsController {
  private Pane mainMenuRoot;

  public SettingsController(Pane mainMenuRoot){
    this.mainMenuRoot = mainMenuRoot;
  }


  public void showMainMenu() {
    Main.showExitConfirmation();
  }

  public void restartGame() {
    Game.getInstance().restartGame();
  }
}
