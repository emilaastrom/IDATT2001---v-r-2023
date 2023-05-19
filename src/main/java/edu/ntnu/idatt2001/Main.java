package edu.ntnu.idatt2001;

import edu.ntnu.idatt2001.Controller.MainMenuController;
import edu.ntnu.idatt2001.View.MainMenuView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {
  public static String currentStylesheet;
  private static Scene scene;
  @Override
  public void start(Stage primaryStage) {
    MainMenuController mainMenuController = new MainMenuController(primaryStage);
    MainMenuView view = new MainMenuView(mainMenuController,primaryStage);

    scene = new Scene(view.getRoot(), 1250, 700);
    currentStylesheet = "file:src/main/resources/maintheme.css";
    scene.getStylesheets().add(currentStylesheet);
    primaryStage.setScene(scene);
    primaryStage.show();
  }
  public static void main(String[] args) {
    launch(args);
  }

  public static void changeScene(Pane root, Stage stage) {
    scene.setRoot(root);
    stage.setScene(scene);
  }

}
