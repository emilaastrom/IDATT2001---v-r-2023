package edu.ntnu.idatt2001.Controller;

import edu.ntnu.idatt2001.Main;
import edu.ntnu.idatt2001.Model.FileHandler;
import edu.ntnu.idatt2001.Model.Game;
import edu.ntnu.idatt2001.Model.Goal.Goal;
import edu.ntnu.idatt2001.View.PathsView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.List;

public class GameSelectionController {

    private PathsView pathsView;

    public GameSelectionController(PathsView pathsView){
        this.pathsView = pathsView;
    }

    public void chooseGameFile(Stage stage, String playerName, List<Goal> playerGoals){
        try{
            if(FileHandler.openGame(stage, playerName, playerGoals)) {
                Main.changeScene(this.pathsView.getRoot());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void loadExampleFile(Stage stage, String path, String playerName, List<Goal> playerGoals){
        FileHandler.openStaticGame(stage, path, playerName, playerGoals);
        System.out.println(path+ playerName+ playerGoals);
        Main.changeScene(this.pathsView.getRoot());
        this.pathsView.setCurrentPassageVBox(this.pathsView.showPassages(Game.getInstance().getStory().getOpeningPassage()));
        Main.updateStage();
    }

}
