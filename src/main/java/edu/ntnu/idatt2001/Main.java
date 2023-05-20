package edu.ntnu.idatt2001;

import edu.ntnu.idatt2001.controller.*;
import edu.ntnu.idatt2001.view.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {
  public static String currentStylesheet;
  private static Scene scene;
  private static Scene mainMenuScene;
  private static Stage stage;
  private static InventoryView inventoryView;
  private static SettingsView mainMenuSettingsView;
  private static SettingsView gameSettingsView;
  private static MainMenuView mainMenuView;
  private static ExitConfirmationController exitConfirmationController;
  private static ExitConfirmationView exitConfirmationView;
  @Override
  public void start(Stage primaryStage) {
    stage = primaryStage;

    BorderPane dimmer = new BorderPane();
    dimmer.setVisible(false);
    dimmer.setMouseTransparent(true);
    dimmer.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5);");
    dimmer.setId("mainWindowDimmer");


    exitConfirmationController = new ExitConfirmationController();
    exitConfirmationView = new ExitConfirmationView(dimmer);

    InventoryController inventoryController = new InventoryController();
    inventoryView = new InventoryView(inventoryController);

    PathsController pathsController = new PathsController(inventoryView);
    PathsView pathsView = new PathsView(pathsController);

    GameSelectionController gameSelectionController = new GameSelectionController(pathsView);
    GameSelectionView gameSelectionView = new GameSelectionView(gameSelectionController);

    MainMenuController mainMenuController = new MainMenuController(gameSelectionView.getRoot(),exitConfirmationView);
    mainMenuView = new MainMenuView(mainMenuController,gameSelectionView.getRoot(),dimmer);

    SettingsController settingsController = new SettingsController(mainMenuView.getRoot());
    mainMenuSettingsView = new SettingsView(settingsController, pathsView ,mainMenuView.getRoot(),dimmer, false);

    gameSettingsView = new SettingsView(settingsController, pathsView, pathsView.getRoot(), dimmer, true);



    scene = new Scene(mainMenuView.getRoot(), 1250, 700);
    mainMenuScene = scene;
    currentStylesheet = "file:src/main/resources/maintheme.css";
    scene.getStylesheets().add(currentStylesheet);
    primaryStage.setScene(scene);
    primaryStage.show();
  }
  public static void main(String[] args) {
    launch(args);
  }

  public static void changeScene(Pane root) {
    scene.setRoot(root);
    stage.setScene(scene);
  }
  public static void mainMenu(){
    stage.setScene(mainMenuScene);
  }

  public static void updateStage(){
    stage.show();
  }

  public static void showInventory(double width, double height, BorderPane dimmer){
    inventoryView.showInventory(width, height, dimmer);
  }

  public static void showMainMenuSettings(){
    mainMenuSettingsView.showStage();
  }

  public static void showGameSettings(){
    gameSettingsView.showStage();
  }

  public static void showExitConfirmation(){
    exitConfirmationView.mainMenuConfirmation(mainMenuView.getRoot());
  }

}
