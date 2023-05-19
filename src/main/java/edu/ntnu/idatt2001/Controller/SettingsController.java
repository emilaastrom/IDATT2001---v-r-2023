package edu.ntnu.idatt2001.Controller;

import edu.ntnu.idatt2001.Main;
import edu.ntnu.idatt2001.Model.Game;
import edu.ntnu.idatt2001.View.MainMenuView;
import edu.ntnu.idatt2001.View.SettingsView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SettingsController {
  private Pane mainMenuRoot;

  public SettingsController(Pane mainMenuRoot){
    this.mainMenuRoot = mainMenuRoot;
  }


  public void showMainMenu() {
    Main.changeScene(mainMenuRoot);
  }

  public void restartGame() {
    Game.getInstance().restartGame();
  }
}
