package edu.ntnu.idatt2001.View;

import edu.ntnu.idatt2001.Controller.BackgroundController;
import edu.ntnu.idatt2001.Controller.GameSelectionController;
import edu.ntnu.idatt2001.Controller.MainMenuController;
import edu.ntnu.idatt2001.Controller.MusicController;
import edu.ntnu.idatt2001.Main;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainMenuView {
  StackPane mainMenuRoot = new StackPane();
  private BorderPane mainWindowDimmer;
  private MainMenuController controller;
  private Pane gameSelectionRoot;
  private StackPane entryWindowLogoStackPane = new StackPane();
  private Image entryWindowLogo = new Image("file:src/main/resources/logo.png");
  private VBox entryWindowVBox = new VBox();
  private Button entryWindowChooseAdventureButton = new Button("CHOOSE YOUR ADVENTURE");
  private Button settingsButton = new Button("SETTINGS");
  private Button exitGameButton = new Button("EXIT GAME");
  private BorderPane entryWindow = new BorderPane();


  public MainMenuView(MainMenuController controller, Pane gameSelectionRoot, BorderPane mainWindowDimmer){
    this.gameSelectionRoot = gameSelectionRoot;
    this.controller = controller;
    this.mainWindowDimmer = mainWindowDimmer;

    MusicController.playMusic();

    createAndConfigurePane();

    createAndLayoutControls();

    updateControllerFromListeners();

    observeModelAndUpdateControls();
  }

  public StackPane getRoot() {
    return mainMenuRoot;
  }

  public void createAndConfigurePane(){

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
    entryWindowChooseAdventureButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> Main.changeScene(gameSelectionRoot));
    settingsButton.setId("mainMenuButton");
    settingsButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> controller.showSettings(mainMenuRoot, mainWindowDimmer));
    exitGameButton.setId("mainMenuButton");
    exitGameButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> controller.showExitConfirmation(mainWindowDimmer));
  }

  public void updateControllerFromListeners(){
    //exitGameButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> showExitConfirmation());
  }

  public void observeModelAndUpdateControls(){

  }

  public BorderPane getDimmer() {
    return mainWindowDimmer;
  }
}
