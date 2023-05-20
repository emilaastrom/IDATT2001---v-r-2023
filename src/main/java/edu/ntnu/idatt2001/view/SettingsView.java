package edu.ntnu.idatt2001.view;

import edu.ntnu.idatt2001.Main;
import edu.ntnu.idatt2001.controller.BackgroundController;
import edu.ntnu.idatt2001.controller.MusicController;
import edu.ntnu.idatt2001.controller.SettingsController;
import edu.ntnu.idatt2001.model.Game;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


/**
 * The Settings view class responsible for showing general settings and game settings.
 */
public class SettingsView {

  private final BorderPane settingsRoot;
  private final SettingsController controller;
  private Stage stage;
  private final Pane superRoot;
  private final BorderPane dimmer;
  private final Boolean isGameSettings;
  private final PathsView pathsView;


  /**
   * Instantiates a new Settings view.
   *
   * @param controller     the settings controller
   * @param pathsView      the paths view
   * @param superRoot      the super root
   * @param dimmer         the dimmer
   * @param isGameSettings the is game settings
   */
  public SettingsView(
          SettingsController controller,
          PathsView pathsView,
          Pane superRoot,
          BorderPane dimmer,
          Boolean isGameSettings) {
    this.pathsView = pathsView;
    this.controller = controller;
    settingsRoot = new BorderPane();
    settingsRoot.setId("SettingsRoot");
    this.isGameSettings = isGameSettings;
    this.superRoot = superRoot;
    this.dimmer = dimmer;

    createAndConfigureStage();

    createAndLayoutControls();

    updateControllerFromListeners();

    observeModelAndUpdateControls();
  }
  
  private void createAndConfigureStage() {
    Scene scene = new Scene(settingsRoot, 500, 750);
    scene.getStylesheets().add("file:src/main/resources/maintheme.css");
    this.stage = new Stage();
    stage.initModality(Modality.APPLICATION_MODAL);
    stage.setScene(scene);
  }

  private void createAndLayoutControls() {

    VBox settingsTitleBox = new VBox();
    settingsTitleBox.setPadding(new Insets(20));
    Text settingsTitle = new Text("Change settings!");
    settingsTitle.setId("DefaultText");
    settingsTitleBox.getChildren().add(settingsTitle);
    settingsTitleBox.setAlignment(Pos.CENTER);

    HBox changeThemeBox = new HBox();
    changeThemeBox.setSpacing(15);
    changeThemeBox.setAlignment(Pos.CENTER);
    changeThemeBox.setMinWidth(200);
    Button themeButtonLeft = new Button("<");
    themeButtonLeft.setMaxSize(50, 50);
    VBox currentThemeBox = new VBox();
    currentThemeBox.setId("CurrentThemeBox");
    currentThemeBox.setMinWidth(135);
    currentThemeBox.setMaxWidth(135);
    currentThemeBox.setAlignment(Pos.CENTER);
    Text currentThemeText = new Text(BackgroundController.getBackgroundString());
    currentThemeText.setId("DefaultText");
    currentThemeBox.getChildren().add(currentThemeText);
    Button themeButtonRight = new Button(">");
    themeButtonRight.setMaxSize(50, 50);
    changeThemeBox.getChildren().addAll(themeButtonLeft, currentThemeBox, themeButtonRight);
    themeButtonLeft.setOnAction(event -> {
      superRoot.setBackground(BackgroundController.rotateBackground());
      currentThemeText.setText(BackgroundController.getBackgroundString());
    });
    themeButtonRight.setOnAction(event -> {
      superRoot.setBackground(BackgroundController.rotateBackground());
      currentThemeText.setText(BackgroundController.getBackgroundString());
    });

    VBox settingsSoundBox = new VBox();
    settingsSoundBox.setAlignment(Pos.CENTER);
    Button muteButton = new Button("Stop music");
    muteButton.setMaxWidth(284);
    settingsSoundBox.getChildren().addAll(muteButton);

    VBox settingsSoundSliderBox = new VBox();
    Slider settingsSoundSlider = MusicController.getVolumeSlider();
    settingsSoundSlider.setMaxWidth(284);
    settingsSoundSliderBox.setAlignment(Pos.CENTER);

    settingsSoundBox.getChildren().addAll(settingsSoundSlider);

    muteButton.setOnAction(event -> {
      if (muteButton.getText().equals("Stop music")) {
        muteButton.setText("Play music");
        MusicController.pauseMusic();
      } else {
        muteButton.setText("Stop music");
        MusicController.playMusic();
      }
    });

    //VBox for containing the different buttons and volume slider in settings
    VBox settingsListBox = new VBox();
    settingsListBox.setAlignment(Pos.CENTER);
    settingsListBox.setSpacing(30);
    //Adding in game settings if opened from pathsView
    if (isGameSettings) {
      Button restartButton = new Button("Restart");
      restartButton.setMaxWidth(200);
      restartButton.setOnAction(event -> {
        //TODO RESTART GAME
        controller.restartGame();
        pathsView.setCurrentPassageVbox(pathsView.showPassages(Game.getInstance().begin()));
        pathsView.updateBottomBox();
        Main.updateStage();
        closeStage();
      });
      Button mainMenuButton = new Button("Main menu");
      mainMenuButton.setMaxWidth(200);
      mainMenuButton.setOnAction(event -> {
        //TODO MAIN MENU

        controller.showMainMenu();
        closeStage();
      });
      Separator separator = new Separator(Orientation.HORIZONTAL);
      separator.setMaxWidth(350);
      settingsListBox.getChildren().addAll(restartButton, mainMenuButton, separator);
    }
    //Adding general settings to list of buttons in settings
    settingsListBox.getChildren().addAll(changeThemeBox, settingsSoundSliderBox, settingsSoundBox);

    //Creating exit button in its own VBox for alignment purposes
    VBox settingsExitBox = new VBox();
    settingsExitBox.setPadding(new Insets(20));
    Button settingsExitButton = new Button("Close");
    settingsExitButton.setMaxWidth(200);
    settingsExitButton.setOnAction(event -> closeStage());
    settingsExitBox.getChildren().add(settingsExitButton);
    settingsExitBox.setAlignment(Pos.CENTER);

    //Adding different boxes to the root of settings
    settingsRoot.setTop(settingsTitleBox);
    settingsRoot.setCenter(settingsListBox);
    settingsRoot.setBottom(settingsExitBox);
  }

  private void updateControllerFromListeners() {
  }

  private void observeModelAndUpdateControls() {
    stage.addEventHandler(WindowEvent.WINDOW_HIDDEN, event -> dimmer.setVisible(false));
  }

  public void showStage() {
    stage.show();
  }

  private void closeStage() {
    stage.close();
  }

  public Pane getRoot() {
    return settingsRoot;
  }

}
