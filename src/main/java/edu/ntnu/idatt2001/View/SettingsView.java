package edu.ntnu.idatt2001.View;

import edu.ntnu.idatt2001.Controller.BackgroundController;
import edu.ntnu.idatt2001.Controller.MusicController;
import edu.ntnu.idatt2001.Controller.SettingsController;
import edu.ntnu.idatt2001.Main;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class SettingsView {

  private BorderPane settingsRoot;
  private SettingsController controller;
  private Stage stage;
  private Scene scene;
  private Pane superRoot;
  private BorderPane dimmer;

  public SettingsView(SettingsController controller, Pane superRoot, BorderPane dimmer) {
    settingsRoot = new BorderPane();
    settingsRoot.setId("SettingsRoot");
    this.controller = controller;
    this.superRoot = superRoot;
    this.dimmer = dimmer;

    createAndConfigureStage();

    createAndLayoutControls();

    updateControllerFromListeners();

    observeModelAndUpdateControls();

    showStage();
  }
  
  private void createAndConfigureStage() {
    this.scene = new Scene(settingsRoot, 500, 500);
    this.stage = new Stage();
    stage.initModality(Modality.APPLICATION_MODAL);
    stage.setScene(scene);
  }

  private void createAndLayoutControls() {
    scene.getStylesheets().add(Main.currentStylesheet);


    VBox settingsTitleBox = new VBox();
    Text settingsTitle = new Text("Change settings!");
    settingsTitle.setId("DefaultText");
    settingsTitleBox.getChildren().add(settingsTitle);
    settingsTitleBox.setAlignment(Pos.CENTER);

    HBox changeThemeBox = new HBox();
    Button themeButtonLeft = new Button("<");
    VBox currentThemeBox = new VBox();
    Text currentThemeText = new Text(BackgroundController.getBackgroundString());
    currentThemeText.setId("DefaultText");
    currentThemeBox.getChildren().add(currentThemeText);
    currentThemeBox.setId("CurrentThemeBox");
    currentThemeBox.setMinWidth(135);
    currentThemeBox.setMaxWidth(135);
    currentThemeBox.setAlignment(Pos.CENTER);
    Button themeButtonRight = new Button(">");
    themeButtonRight.setMaxSize(50, 50);
    themeButtonLeft.setMaxSize(50, 50);
    changeThemeBox.setSpacing(15);
    changeThemeBox.getChildren().addAll(themeButtonLeft, currentThemeBox, themeButtonRight);
    changeThemeBox.setAlignment(Pos.CENTER);
    changeThemeBox.setMinWidth(200);
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
    muteButton.setOnAction(event -> {
      if (muteButton.getText().equals("Stop music")) {
        muteButton.setText("Play music");
        MusicController.pauseMusic();
      } else {
        muteButton.setText("Stop music");
        MusicController.playMusic();
      }
    });

    VBox settingsSoundSliderBox = new VBox();
    Slider settingsSoundSlider = new Slider();
    settingsSoundSlider.setMin(0);
    settingsSoundSlider.setMax(100);
    settingsSoundSlider.setValue(50);
    settingsSoundSlider.setMaxWidth(284);
    settingsSoundSliderBox.setAlignment(Pos.CENTER);

    settingsSoundBox.getChildren().addAll(settingsSoundSlider);
    settingsSoundSlider.addEventHandler(MouseEvent.MOUSE_DRAGGED, event -> MusicController.musicVolume(settingsSoundSlider.getValue()));
    settingsSoundSlider.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> MusicController.musicVolume(settingsSoundSlider.getValue()));
    settingsSoundSlider.setBlockIncrement(5);

    VBox settingsListBox = new VBox();
    settingsListBox.setAlignment(Pos.CENTER);
    settingsListBox.setSpacing(30);
    settingsListBox.getChildren().addAll(changeThemeBox, settingsSoundSliderBox, settingsSoundBox);

    VBox settingsExitBox = new VBox();
    Button settingsExitButton = new Button("Close");
    settingsExitButton.setMaxWidth(200);
    settingsExitButton.setOnAction(event -> closeStage());
    settingsExitBox.getChildren().add(settingsExitButton);
    settingsExitBox.setAlignment(Pos.CENTER);

    settingsRoot.setTop(settingsTitleBox);
    settingsRoot.setCenter(settingsListBox);
    settingsRoot.setBottom(settingsExitBox);
  }

  private void updateControllerFromListeners() {
  }

  private void observeModelAndUpdateControls() {
    stage.addEventHandler(WindowEvent.WINDOW_HIDDEN, event -> dimmer.setVisible(false));
  }

  private void showStage() {
    stage.show();
  }

  private void closeStage(){
    stage.close();
  }

  public Pane getRoot(){
    return settingsRoot;
  }

}
