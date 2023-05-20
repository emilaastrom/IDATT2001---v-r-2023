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
  public void chooseGameFile(Stage stage, String playerName, List<Goal> playerGoals) {
    try {
      if (FileHandler.openGame(stage, playerName, playerGoals)) {
        Main.changeScene(this.pathsView.getRoot());
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
    System.out.println(path + playerName + playerGoals);
    Main.changeScene(this.pathsView.getRoot());
    this.pathsView.setCurrentPassageVBox(
            this.pathsView.showPassages(Game.getInstance().getStory().getOpeningPassage()));
    Main.updateStage();
  }

}
