package edu.ntnu.idatt2001.view;

import edu.ntnu.idatt2001.Main;
import edu.ntnu.idatt2001.handlers.BackgroundHandler;
import edu.ntnu.idatt2001.controller.MainMenuController;
import edu.ntnu.idatt2001.handlers.MusicHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * The MainMenuView class responsible for visualizing the main menu.
 */
public class MainMenuView {
  private final StackPane mainMenuRoot = new StackPane();
  private final BorderPane mainWindowDimmer;
  private final MainMenuController controller;
  private final Pane gameSelectionRoot;
  private final StackPane entryWindowLogoStackPane = new StackPane();
  private final Image entryWindowLogo = new Image("file:src/main/resources/icons/logo.png");
  private final VBox entryWindowBox = new VBox();
  private final Button entryWindowChooseAdventureButton = new Button("CHOOSE YOUR ADVENTURE");
  private final Button settingsButton = new Button("SETTINGS");
  private final Button helpButton = new Button("HOW TO PLAY");
  private final Button exitGameButton = new Button("EXIT GAME");
  private final BorderPane entryWindow = new BorderPane();


  /**
   * Instantiates a new MainMenuView.
   *
   * @param controller        the controller
   * @param gameSelectionRoot the game selection root
   * @param mainWindowDimmer  the main window dimmer
   */
  public MainMenuView(
          MainMenuController controller,
          Pane gameSelectionRoot,
          BorderPane mainWindowDimmer) {
    this.gameSelectionRoot = gameSelectionRoot;
    this.controller = controller;
    this.mainWindowDimmer = mainWindowDimmer;

    //Playing soundtrack
    MusicHandler.playMusic();

    createAndConfigurePane();

    configureAndIdControls();
  }

  /**
   * Returns the root of the main menu.
   *
   * @return the root StackPane
   */
  public StackPane getRoot() {
    return mainMenuRoot;
  }

  /**
   * Create and configure the root of MainMenuView.
   */
  public void createAndConfigurePane() {
    //Creating and configuring the main menu StackPane
    entryWindowBox.setMinHeight(0);
    entryWindowBox.setMinWidth(0);
    entryWindowBox.setAlignment(javafx.geometry.Pos.CENTER);
    entryWindowBox.setSpacing(30);

    entryWindowLogoStackPane.getChildren().add(new javafx.scene.image.ImageView(entryWindowLogo));

    entryWindowBox.getChildren().addAll(
            entryWindowLogoStackPane,
            entryWindowChooseAdventureButton,
            settingsButton,
            helpButton,
            exitGameButton);

    entryWindow.setVisible(true);
    entryWindow.setCenter(entryWindowBox);
    entryWindow.setStyle("-fx-background-color: rgb(0,0,0,0)");

    mainMenuRoot.getChildren().addAll(entryWindow, mainWindowDimmer);
    mainMenuRoot.setBackground(BackgroundHandler.setBackgroundForest());
  }

  /**
   * Setting ID for different buttons and creating event handlers  .
   */
  public void configureAndIdControls() {
    entryWindowChooseAdventureButton.setId("mainMenuButton");
    entryWindowChooseAdventureButton.addEventHandler(MouseEvent.MOUSE_CLICKED,
            mouseEvent -> Main.changeScene(gameSelectionRoot));
    settingsButton.setId("mainMenuButton");
    settingsButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent ->
            controller.showSettings(mainWindowDimmer));
    helpButton.setId("mainMenuButton");
    helpButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent ->
            controller.showHelp());
    exitGameButton.setId("mainMenuButton");
    exitGameButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent ->
            controller.showExitConfirmation(mainWindowDimmer));
  }

  /**
   * Returns the main window dimmer.
   *
   * @return the dimmer
   */
  public BorderPane getDimmer() {
    return mainWindowDimmer;
  }
}
