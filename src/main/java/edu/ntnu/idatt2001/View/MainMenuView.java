package edu.ntnu.idatt2001.View;

import edu.ntnu.idatt2001.Controller.BackgroundController;
import edu.ntnu.idatt2001.Controller.GameSelectionController;
import edu.ntnu.idatt2001.Controller.MainMenuController;
import edu.ntnu.idatt2001.Controller.MusicController;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class MainMenuView {
  StackPane mainMenuRoot = new StackPane();
  private BorderPane mainWindowDimmer = new BorderPane();
  private MainMenuController controller;
  static Stage settingsStage;
  static BorderPane settingsRoot;
  static Stage gameSelectionStage;
  private BorderPane gameSelectionRoot;
  private BorderPane pathsWindowRoot;
  private String currentStylesheet;
  private StackPane entryWindowLogoStackPane = new StackPane();
  private Image entryWindowLogo = new Image("file:src/main/resources/logo.png");
  private VBox entryWindowVBox = new VBox();
  private Button entryWindowChooseAdventureButton = new Button("CHOOSE YOUR ADVENTURE");
  private Button settingsButton = new Button("SETTINGS");
  private Button exitGameButton = new Button("EXIT GAME");
  private BorderPane entryWindow = new BorderPane();
  private Stage stage;


  public MainMenuView(MainMenuController controller, Stage stage) {
    this.controller = controller;
    this.stage = stage;

    MusicController.playMusic();

    createAndConfigurePane();

    createAndLayoutControls();

    updateControllerFromListeners();

    observeModelAndUpdateControls();
  }

  public Parent asParent() {
    return mainMenuRoot;
  }

  public void createAndConfigurePane(){
    mainWindowDimmer.setVisible(false);
    mainWindowDimmer.setMouseTransparent(true);
    mainWindowDimmer.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5);");
    mainWindowDimmer.setId("mainWindowDimmer");

    entryWindowVBox.setMinHeight(0);
    entryWindowVBox.setMinWidth(0);
    entryWindowVBox.setAlignment(javafx.geometry.Pos.CENTER);
    entryWindowVBox.setSpacing(30);

    entryWindowLogoStackPane.getChildren().add(new javafx.scene.image.ImageView(entryWindowLogo));

    entryWindowVBox.getChildren().addAll(entryWindowLogoStackPane, entryWindowChooseAdventureButton, settingsButton, exitGameButton);

    entryWindow.setVisible(true);
    entryWindow.setCenter(entryWindowVBox);
    entryWindow.setStyle("-fx-background-color: rgb(0,0,0,0)");

    mainMenuRoot.getChildren().addAll(entryWindow, mainWindowDimmer);
    mainMenuRoot.setBackground(BackgroundController.setBackgroundForest());
  }

  public void createAndLayoutControls(){
    entryWindowChooseAdventureButton.setId("mainMenuButton");
    entryWindowChooseAdventureButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> new GameSelectionView(new GameSelectionController(), stage));
    settingsButton.setId("mainMenuButton");
    settingsButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> controller.showSettings(mainMenuRoot, mainWindowDimmer));
  }

  public void updateControllerFromListeners(){
    //exitGameButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> showExitConfirmation());
  }

  public void observeModelAndUpdateControls(){

  }


}
