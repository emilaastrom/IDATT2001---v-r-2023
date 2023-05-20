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
import javafx.scene.layout.*;
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
  private final BorderPane dimmer;
  private final PathsView pathsView;
  private final VBox settingsListBox = new VBox();
  private final Button restartButton = new Button("Restart");
  private final Button mainMenuButton = new Button("Main menu");
  private final Separator separator = new Separator(Orientation.HORIZONTAL);
  private final VBox settingsSoundBox = new VBox();
  private final HBox changeThemeBox = new HBox();



  /**
   * Instantiates a new Settings view.
   *
   * @param controller     the settings controller
   * @param pathsView      the paths view
   * @param dimmer         the dimmer
   */
  public SettingsView(
          SettingsController controller,
          PathsView pathsView,
          BorderPane dimmer) {
    this.pathsView = pathsView;
    this.controller = controller;
    settingsRoot = new BorderPane();
    settingsRoot.setId("SettingsRoot");
    this.dimmer = dimmer;

    createAndConfigureStage();

    createAndLayoutControls();

    updateControllerFromListeners();

    observeModelAndUpdateControls();
  }
  
  private void createAndConfigureStage() {
    Scene scene = new Scene(settingsRoot, 500, 600);
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
      Background background = BackgroundController.rotateBackground();
      controller.getMainMenuRoot().setBackground(background);
      controller.getPathsRoot().setBackground(background);
      currentThemeText.setText(BackgroundController.getBackgroundString());
    });
    themeButtonRight.setOnAction(event -> {
      Background background = BackgroundController.rotateBackground();
      controller.getMainMenuRoot().setBackground(background);
      controller.getPathsRoot().setBackground(background);
      currentThemeText.setText(BackgroundController.getBackgroundString());
    });

    settingsSoundBox.setAlignment(Pos.CENTER);
    Button muteButton = new Button("Stop music");
    muteButton.setMaxWidth(284);
    settingsSoundBox.getChildren().addAll(muteButton);
    Slider settingsSoundSlider = MusicController.getVolumeSlider();
    settingsSoundSlider.setMaxWidth(284);

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
    settingsListBox.setAlignment(Pos.CENTER);
    settingsListBox.setSpacing(30);

      restartButton.setMaxWidth(200);
      restartButton.setOnAction(event -> {
        //TODO RESTART GAME
        controller.restartGame();
        pathsView.setCurrentPassageVbox(pathsView.showPassages(Game.getInstance().begin()));
        pathsView.updateBottomBox();
        Main.updateStage();
        closeStage();
      });
      mainMenuButton.setMaxWidth(200);
      mainMenuButton.setOnAction(event -> {
        //TODO MAIN MENU

        controller.showMainMenu();
        closeStage();
      });
      separator.setMaxWidth(350);
      settingsListBox.getChildren().addAll(restartButton, mainMenuButton, separator);

    //Adding general settings to list of buttons in settings
    settingsListBox.getChildren().addAll(changeThemeBox, settingsSoundBox);

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

  public void showStage(boolean isGameSettings) {
    stage.show();
    if (isGameSettings) {
      settingsListBox.getChildren().clear();
      settingsListBox.getChildren().addAll(restartButton, mainMenuButton, separator, changeThemeBox, settingsSoundBox);
    } else {
      settingsListBox.getChildren().clear();
      settingsListBox.getChildren().addAll(changeThemeBox, settingsSoundBox);
    }
  }

  private void closeStage() {
    stage.close();
  }

  public Pane getRoot() {
    return settingsRoot;
  }

}
