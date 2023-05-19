package edu.ntnu.idatt2001.Controller;

import edu.ntnu.idatt2001.Main;
import edu.ntnu.idatt2001.Model.FileHandler;
import edu.ntnu.idatt2001.View.PathsView;
import javafx.stage.Stage;

public class GameSelectionController {

    public void chooseGameFile(Stage stage){
        try{
            if(FileHandler.openGame(stage)) {
                PathsView pathsView = new PathsView(new PathsController(), stage);
                Main.changeScene(pathsView.getRoot(), stage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void loadExampleFile(Stage stage, String path){
        FileHandler.openStaticGame(stage, path);
        PathsView pathsView = new PathsView(new PathsController(), stage);
        Main.changeScene(pathsView.getRoot(), stage);
    }

}
