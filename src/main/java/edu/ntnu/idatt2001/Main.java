package edu.ntnu.idatt2001;

import edu.ntnu.idatt2001.controller.*;
import edu.ntnu.idatt2001.view.ExitConfirmationView;
import edu.ntnu.idatt2001.view.HelpView;
import edu.ntnu.idatt2001.view.GameSelectionView;
import edu.ntnu.idatt2001.view.InventoryView;
import edu.ntnu.idatt2001.view.MainMenuView;
import edu.ntnu.idatt2001.view.PathsView;
import edu.ntnu.idatt2001.view.SettingsView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


/**
 * Main class initializing the application.
 */
public class Main extends Application {
  public static String currentStylesheet;
  private static Scene scene;
  private static Stage stage;
  private static SettingsView settingsView;
  private static HelpView helpView;
  private static ExitConfirmationView exitConfirmationView;
  private static MainMenuView mainMenuView;

  @Override
  public void start(Stage primaryStage) {
    stage = primaryStage;

    BorderPane dimmer = new BorderPane();
    dimmer.setVisible(false);
    dimmer.setMouseTransparent(true);
    dimmer.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5);");
    dimmer.setId("mainWindowDimmer");


    exitConfirmationView = new ExitConfirmationView(dimmer);

    InventoryController inventoryController = new InventoryController();
    InventoryView inventoryView = new InventoryView(inventoryController);

    PathsController pathsController = new PathsController(inventoryView);
    PathsView pathsView = new PathsView(pathsController);

    GameSelectionController gameSelectionController = new GameSelectionController(pathsView);
    GameSelectionView gameSelectionView = new GameSelectionView(gameSelectionController);

    MainMenuController mainMenuController = new MainMenuController(
            gameSelectionView.getRoot(),
            exitConfirmationView);
    mainMenuView = new MainMenuView(mainMenuController, gameSelectionView.getRoot(), dimmer);

    HelpController helpController = new HelpController();
    helpView = new HelpView(helpController, mainMenuView.getRoot());

    SettingsController settingsController = new SettingsController(mainMenuView.getRoot(),pathsView.getRoot());
    settingsView = new SettingsView(
        settingsController,
        pathsView,
        dimmer);
    
    scene = new Scene(mainMenuView.getRoot(), 1250, 700);
    currentStylesheet = "file:src/main/resources/maintheme.css";

    scene.getStylesheets().add(currentStylesheet);
    primaryStage.setTitle("Paths");
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

  public static void updateStage() {
    stage.show();
  }

  public static void showMainMenuSettings() {
    settingsView.showStage(false);
  }

  public static void showGameSettings() {
    settingsView.showStage(true);
  }

  public static void showExitConfirmation(PathsView pathsView) {
    pathsView.getPathsDimmer().setVisible(true);
    exitConfirmationView.mainMenuConfirmation(mainMenuView.getRoot(), pathsView.getPathsDimmer());
  }

  public static void showHelp() {helpView.showHelp();}

}
