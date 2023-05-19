package edu.ntnu.idatt2001.Controller;

import edu.ntnu.idatt2001.Main;
import edu.ntnu.idatt2001.Model.Game;
import edu.ntnu.idatt2001.View.MainMenuView;
import edu.ntnu.idatt2001.View.SettingsView;
import javafx.stage.Stage;

public class SettingsController {

  public SettingsController(){

  }


  public void showMainMenu(Stage stage) {
    MainMenuView mainMenuView = new MainMenuView(new MainMenuController(stage), stage);
    Main.changeScene(mainMenuView.getRoot(),stage);
  }

  public void restartGame() {
    Game.getInstance().restartGame();
  }
}
