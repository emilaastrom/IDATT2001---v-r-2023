package edu.ntnu.idatt2001.Controller;

import edu.ntnu.idatt2001.Main;
import edu.ntnu.idatt2001.Model.FileHandler;
import edu.ntnu.idatt2001.View.PathsView;
import javafx.stage.Stage;

public class MainMenuController {
  public MainMenuController() {
  }

  public void chooseAdventure(Stage stage) {
    if(FileHandler.openGame(stage)) {
      PathsView pathsView = new PathsView(new PathsController(), stage);
      Main.changeScene(pathsView.getRoot(), stage);
    }
  }
}