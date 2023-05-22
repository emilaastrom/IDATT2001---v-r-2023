package edu.ntnu.idatt2001;

import edu.ntnu.idatt2001.controller.GameSelectionController;
import edu.ntnu.idatt2001.controller.InventoryController;
import edu.ntnu.idatt2001.controller.MainMenuController;
import edu.ntnu.idatt2001.controller.PathsController;
import edu.ntnu.idatt2001.controller.SettingsController;
import edu.ntnu.idatt2001.view.ExitConfirmationView;
import edu.ntnu.idatt2001.view.GameSelectionView;
import edu.ntnu.idatt2001.view.HelpView;
import edu.ntnu.idatt2001.view.InventoryView;
import edu.ntnu.idatt2001.view.MainMenuView;
import edu.ntnu.idatt2001.view.PathsView;
import edu.ntnu.idatt2001.view.SettingsView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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
    primaryStage.getIcons().add(new Image("file:src/main/resources/chest.png"));

    //Create a dimmer to dim the background when a new stage is shown
    BorderPane dimmer = new BorderPane();
    dimmer.setVisible(false);
    dimmer.setMouseTransparent(true);
    dimmer.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5);");
    dimmer.setId("mainWindowDimmer");

    //initializes the controllers and views
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

    helpView = new HelpView(mainMenuView.getRoot());

    SettingsController settingsController = new SettingsController(
        mainMenuView.getRoot(), pathsView.getRoot());
    settingsView = new SettingsView(
        settingsController,
        pathsView,
        dimmer);

    //Set the scene and stylesheet
    scene = new Scene(mainMenuView.getRoot(), 1250, 700);
    currentStylesheet = "file:src/main/resources/maintheme.css";
    scene.getStylesheets().add(currentStylesheet);

    //Set the stage
    primaryStage.setTitle("Paths");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  /**
   * The main method.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
    launch(args);
  }

  /**
   * Changes the scene.
   *
   * @param root the root
   */
  public static void changeScene(Pane root) {
    scene.setRoot(root);
    stage.setScene(scene);
  }

  /**
   * Updates the stage.
   */
  public static void updateStage() {
    stage.show();
  }

  /**
   * Shows the settings stage for the main menu.
   *
   */
  public static void showMainMenuSettings() {
    settingsView.showStage(false);
  }

  /**
   * Shows the settings stage for the game.
   *
   */
  public static void showGameSettings() {
    settingsView.showStage(true);
  }

  /**
   * Shows the exit confirmation stage.
   *
   */
  public static void showExitConfirmation(PathsView pathsView) {
    pathsView.getPathsDimmer().setVisible(true);
    exitConfirmationView.mainMenuConfirmation(mainMenuView.getRoot(), pathsView.getPathsDimmer());
  }

  /**
   * Shows the help stage.
   *
   */
  public static void showHelp() {
    helpView.showHelp();
  }
}
