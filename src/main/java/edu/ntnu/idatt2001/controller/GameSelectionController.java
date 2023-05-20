package edu.ntnu.idatt2001.controller;

import edu.ntnu.idatt2001.Main;
import edu.ntnu.idatt2001.model.FileHandler;
import edu.ntnu.idatt2001.model.Game;
import edu.ntnu.idatt2001.model.Goal.Goal;
import edu.ntnu.idatt2001.view.PathsView;
import javafx.stage.Stage;

import java.util.List;

public class GameSelectionController {

    private PathsView pathsView;

    public GameSelectionController(PathsView pathsView){
        this.pathsView = pathsView;
    }

    public void chooseGameFile(Stage stage,String path, String playerName, List<Goal> playerGoals){
        try{
            if(FileHandler.openGame(stage, path, playerName, playerGoals)) {
                Main.changeScene(this.pathsView.getRoot());
                this.pathsView.setCurrentPassageVBox(this.pathsView.showPassages(Game.getInstance().getStory().getOpeningPassage()));
                Main.updateStage();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void loadExampleFile(Stage stage, String path, String playerName, List<Goal> playerGoals){
        FileHandler.openStaticGame(stage, path, playerName, playerGoals);
        Main.changeScene(this.pathsView.getRoot());
        this.pathsView.setCurrentPassageVBox(this.pathsView.showPassages(Game.getInstance().getStory().getOpeningPassage()));
        Main.updateStage();
    }

}
