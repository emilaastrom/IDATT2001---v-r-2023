package edu.ntnu.idatt2001.Controller;

import edu.ntnu.idatt2001.Main;
import edu.ntnu.idatt2001.Model.FileHandler;
import edu.ntnu.idatt2001.Model.Goal.Goal;
import edu.ntnu.idatt2001.View.PathsView;
import javafx.stage.Stage;

import java.util.List;

public class GameSelectionController {

    public void chooseGameFile(Stage stage, String playerName, List<Goal> playerGoals){
        try{
            if(FileHandler.openGame(stage, playerName, playerGoals)) {
                PathsView pathsView = new PathsView(new PathsController(stage), stage);
                Main.changeScene(pathsView.getRoot(), stage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void loadExampleFile(Stage stage, String path, String playerName, List<Goal> playerGoals){
        FileHandler.openStaticGame(stage, path, playerName, playerGoals);
        PathsView pathsView = new PathsView(new PathsController(stage), stage);
        Main.changeScene(pathsView.getRoot(), stage);
    }

}
