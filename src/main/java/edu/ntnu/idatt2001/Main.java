package edu.ntnu.idatt2001;

import edu.ntnu.idatt2001.controller.ExitConfirmationController;
import edu.ntnu.idatt2001.controller.GameSelectionController;
import edu.ntnu.idatt2001.controller.InventoryController;
import edu.ntnu.idatt2001.controller.MainMenuController;
import edu.ntnu.idatt2001.controller.PathsController;
import edu.ntnu.idatt2001.controller.SettingsController;
import edu.ntnu.idatt2001.view.ExitConfirmationView;
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
  private static Scene mainMenuScene;
  private static Stage stage;
  private static InventoryView inventoryView;
  private static SettingsView mainMenuSettingsView;
  private static SettingsView gameSettingsView;
  private static MainMenuView mainMenuView;

  @Override
  public void start(Stage primaryStage) {
    stage = primaryStage;

    BorderPane dimmer = new BorderPane();
    dimmer.setVisible(false);
    dimmer.setMouseTransparent(true);
    dimmer.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5);");
    dimmer.setId("mainWindowDimmer");


    ExitConfirmationController exitConfirmationController = new ExitConfirmationController();
    ExitConfirmationView exitConfirmationView = new ExitConfirmationView(dimmer);

    InventoryController inventoryController = new InventoryController();
    inventoryView = new InventoryView(inventoryController);

    PathsController pathsController = new PathsController(inventoryView);
    PathsView pathsView = new PathsView(pathsController);

    GameSelectionController gameSelectionController = new GameSelectionController(pathsView);
    GameSelectionView gameSelectionView = new GameSelectionView(gameSelectionController);

    MainMenuController mainMenuController = new MainMenuController(
            gameSelectionView.getRoot(),
            exitConfirmationView);
    mainMenuView = new MainMenuView(mainMenuController, gameSelectionView.getRoot(), dimmer);

    SettingsController settingsController = new SettingsController(mainMenuView.getRoot());
    mainMenuSettingsView = new SettingsView(
            settingsController,
            pathsView,
            mainMenuView.getRoot(),
            dimmer,
            false);

    gameSettingsView = new SettingsView(
            settingsController,
            pathsView,
            pathsView.getRoot(),
            dimmer,
            true);



    scene = new Scene(mainMenuView.getRoot(), 1250, 700);
    mainMenuScene = scene;
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

  public static void mainMenu() {
    stage.setScene(mainMenuScene);
  }

  public static void updateStage() {
    stage.show();
  }

  public static void showInventory(double width, double height, BorderPane dimmer) {
    inventoryView.showInventory(width, height, dimmer);
  }

  public static void showMainMenuSettings() {
    mainMenuSettingsView.showStage();
  }

  public static void showGameSettings() {
    gameSettingsView.showStage();
  }

}
