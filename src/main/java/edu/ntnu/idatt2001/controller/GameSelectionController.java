package edu.ntnu.idatt2001.controller;

import edu.ntnu.idatt2001.Main;
import edu.ntnu.idatt2001.model.FileHandler;
import edu.ntnu.idatt2001.model.Game;
import edu.ntnu.idatt2001.model.goal.Goal;
import edu.ntnu.idatt2001.view.PathsView;
import java.util.List;
import javafx.stage.Stage;

/**
 * Controller for GameSelectionView.
 */
public class GameSelectionController {

  private final PathsView pathsView;

  public GameSelectionController(PathsView pathsView) {
    this.pathsView = pathsView;
  }

  /**
   * Choose users own game file and show main menu.
   */
  public void chooseGameFile(Stage stage, String path, String playerName, List<Goal> playerGoals) {
    try {
      if (FileHandler.openGame(stage, path, playerName, playerGoals)) {
        Main.changeScene(this.pathsView.getRoot());
        this.pathsView.setCurrentPassageVbox(this.pathsView.showPassages(
            Game.getInstance().getStory().getOpeningPassage()));
        Main.updateStage();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Load example game file and show main menu.
   */
  public void loadExampleFile(Stage stage, String path, String playerName, List<Goal> playerGoals) {
    FileHandler.openStaticGame(stage, path, playerName, playerGoals);
    Main.changeScene(this.pathsView.getRoot());
    this.pathsView.setCurrentPassageVbox(
            this.pathsView.showPassages(Game.getInstance().getStory().getOpeningPassage()));
    Main.updateStage();
  }

}
