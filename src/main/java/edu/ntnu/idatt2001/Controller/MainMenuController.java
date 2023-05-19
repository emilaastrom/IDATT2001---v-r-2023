package edu.ntnu.idatt2001.Controller;

import edu.ntnu.idatt2001.Main;
import edu.ntnu.idatt2001.Model.FileHandler;
import edu.ntnu.idatt2001.View.ExitConfirmationView;
import edu.ntnu.idatt2001.View.GameSelectionView;
import edu.ntnu.idatt2001.View.PathsView;
import edu.ntnu.idatt2001.View.SettingsView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainMenuController {
  Stage stage;
  public MainMenuController(Stage stage) {
    this.stage = stage;
  }

  public void chooseAdventure(Stage stage) {
    if(FileHandler.openGame(stage, "Player", null)) {
      PathsView pathsView = new PathsView(new PathsController(stage), stage);
      Main.changeScene(pathsView.getRoot(), stage);
    }
  }

  public void showGameSelection(Stage stage) {
    GameSelectionView gameSelectionView = new GameSelectionView(new GameSelectionController(), stage);
    Main.changeScene(gameSelectionView.getRoot(), stage);
  }

  public void showSettings(Pane root, BorderPane dimmer) {
    SettingsView settingsView = new SettingsView(new SettingsController(), root, stage, dimmer, false);
    dimmer.setVisible(true);
  }

  public void showExitConfirmation(BorderPane dimmer) {
    ExitConfirmationView exitConfirmationView = new ExitConfirmationView(dimmer);
    exitConfirmationView.ExitConfirmation();
  }
}