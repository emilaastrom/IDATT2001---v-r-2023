package edu.ntnu.idatt2001.controller;

import edu.ntnu.idatt2001.Main;
import edu.ntnu.idatt2001.handlers.FileHandler;
import edu.ntnu.idatt2001.handlers.UserInformerHandler;
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
  public boolean chooseGameFile(String path, String playerName, List<Goal> playerGoals) {
    try {
      // If opening passage is empty, the file is not valid
      if (FileHandler.openGame(path, playerName, playerGoals)) {
        if(Game.getInstance().getStory().getOpeningPassage().getLinks().isEmpty() ||
            Game.getInstance().getStory().getOpeningPassage().getContent().equals("")||
            Game.getInstance().getStory().getOpeningPassage().getTitle().equals("")) {
          UserInformerHandler.errorWarning("Error", "The game file is not valid");
          return false;
        }
        // If the file is valid, show game screen
        Main.changeScene(this.pathsView.getRoot());
        this.pathsView.setCurrentPassageVbox(this.pathsView.showPassages(
            Game.getInstance().getStory().getOpeningPassage()));
        Main.updateStage();
      }
    } catch (Exception e) {
      // If the file is not valid, show error message
      UserInformerHandler.errorWarning("Error", "Could not open file");
      return false;
    }
    // If the file is valid, return true
    return true;
  }

  /**
   * Load example game file and show main menu.
   */
  public void loadExampleFile(String path, String playerName, List<Goal> playerGoals) {
    //opens the example game file
    FileHandler.openStaticGame(path, playerName, playerGoals);
    //shows the game screen
    Main.changeScene(this.pathsView.getRoot());
    this.pathsView.setCurrentPassageVbox(
            this.pathsView.showPassages(Game.getInstance().getStory().getOpeningPassage()));
    Main.updateStage();
  }
}
