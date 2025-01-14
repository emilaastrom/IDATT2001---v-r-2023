package edu.ntnu.idatt2001.view;

import edu.ntnu.idatt2001.Main;
import edu.ntnu.idatt2001.handlers.BackgroundHandler;
import edu.ntnu.idatt2001.handlers.MusicHandler;
import edu.ntnu.idatt2001.controller.SettingsController;
import edu.ntnu.idatt2001.model.Game;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
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
   * Constructor for the Settings.
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
  }

  /**
   * Configuring the settings stage.
   */
  private void createAndConfigureStage() {
    Scene scene = new Scene(settingsRoot, 500, 600);
    scene.getStylesheets().add(Main.class.getResource("/maintheme.css").toString());
    this.stage = new Stage();
    stage.addEventHandler(WindowEvent.WINDOW_HIDDEN, event -> dimmer.setVisible(false));
    stage.getIcons().add(new Image(Main.class.getResource("/icons/settings.png").toString()));
    stage.initModality(Modality.APPLICATION_MODAL);
    stage.setScene(scene);

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
    Text currentThemeText = new Text(BackgroundHandler.getBackgroundString());
    currentThemeText.setId("DefaultText");
    currentThemeBox.getChildren().add(currentThemeText);
    Button themeButtonRight = new Button(">");
    themeButtonRight.setMaxSize(50, 50);
    changeThemeBox.getChildren().addAll(themeButtonLeft, currentThemeBox, themeButtonRight);
    themeButtonLeft.setOnAction(event -> {
      Background background = BackgroundHandler.rotateBackground();
      controller.getMainMenuRoot().setBackground(background);
      controller.getPathsRoot().setBackground(background);
      currentThemeText.setText(BackgroundHandler.getBackgroundString());
    });
    themeButtonRight.setOnAction(event -> {
      Background background = BackgroundHandler.rotateBackground();
      controller.getMainMenuRoot().setBackground(background);
      controller.getPathsRoot().setBackground(background);
      currentThemeText.setText(BackgroundHandler.getBackgroundString());
    });

    settingsSoundBox.setAlignment(Pos.CENTER);
    Button muteButton = new Button("Stop music");
    muteButton.setMaxWidth(284);
    settingsSoundBox.getChildren().addAll(muteButton);
    Slider settingsSoundSlider = MusicHandler.getVolumeSlider();
    settingsSoundSlider.setMaxWidth(284);

    settingsSoundBox.getChildren().addAll(settingsSoundSlider);

    muteButton.setOnAction(event -> {
      if (muteButton.getText().equals("Stop music")) {
        muteButton.setText("Play music");
        MusicHandler.pauseMusic();
      } else {
        muteButton.setText("Stop music");
        MusicHandler.playMusic();
      }
    });

    //VBox for containing the different buttons and volume slider in settings
    settingsListBox.setAlignment(Pos.CENTER);
    settingsListBox.setSpacing(30);

    restartButton.setMaxWidth(200);
    restartButton.setOnAction(event -> {
      controller.restartGame();
      pathsView.setCurrentPassageVbox(pathsView.showPassages(Game.getInstance().begin()));
      pathsView.updateBottomBox();
      Main.updateStage();
      closeStage();
    });
    mainMenuButton.setMaxWidth(200);
    mainMenuButton.setOnAction(event -> {
      closeStage();
      controller.showMainMenu(pathsView);
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

  /**
   * Show settings stage.
   *
   * @param isGameSettings check to see if the settings are opened from main menu or in game
   */
  public void showStage(boolean isGameSettings) {
    //Showing settings, two different versions depending on if it is game settings or not
    stage.show();
    if (isGameSettings) {
      //Game settings includes buttons for restart and main menu
      settingsListBox.getChildren().clear();
      settingsListBox.getChildren().addAll(restartButton, mainMenuButton, separator, changeThemeBox, settingsSoundBox);
      stage.addEventHandler(WindowEvent.WINDOW_HIDDEN, event -> pathsView.getPathsDimmer().setVisible(false));
    } else {
      settingsListBox.getChildren().clear();
      settingsListBox.getChildren().addAll(changeThemeBox, settingsSoundBox);
    }
  }

  /**
   * Close the settings stage.
   */
  private void closeStage() {
    stage.close();
  }

}
