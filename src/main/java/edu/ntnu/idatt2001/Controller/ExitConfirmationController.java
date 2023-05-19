package edu.ntnu.idatt2001.Controller;

import edu.ntnu.idatt2001.Main;
import edu.ntnu.idatt2001.View.MainMenuView;
import javafx.stage.Stage;

public class ExitConfirmationController {

  public ExitConfirmationController() {

  }

  public void showMainMenu(Stage stage) {
      MainMenuView mainMenuView = new MainMenuView(new MainMenuController(stage), stage);
      Main.changeScene(mainMenuView.getRoot(),stage);
  }
}
