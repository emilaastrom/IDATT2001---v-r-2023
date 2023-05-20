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
  Pane gameSelectionRoot;
  ExitConfirmationView exitConfirmationView;
  public MainMenuController(Pane gameSelectionRoot, ExitConfirmationView exitConfirmationView){
    this.gameSelectionRoot = gameSelectionRoot;
    this.exitConfirmationView = exitConfirmationView;
  }

  public void showGameSelection() {
    Main.changeScene(gameSelectionRoot);
  }

  public void showSettings(Pane root, BorderPane dimmer) {
    Main.showMainMenuSettings();
    dimmer.setVisible(true);
  }

  public void showExitConfirmation(BorderPane dimmer) {
    exitConfirmationView.ExitConfirmation();
  }
}