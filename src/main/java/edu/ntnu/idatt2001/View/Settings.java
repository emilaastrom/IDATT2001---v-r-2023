package edu.ntnu.idatt2001.View;

import edu.ntnu.idatt2001.Controller.BackgroundController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import static javafx.scene.layout.BorderPane.setAlignment;


class Settings extends Stage {

  public Settings(double width, double height) {
    //this = stage
    super();
    this.setWidth(width-(width/2));
    this.setHeight(height-(height/2));
    BorderPane root = new BorderPane();
    Scene scene = new Scene(root, width-(width/2), height-(height/2));
    this.setScene(scene);
    this.initModality(Modality.APPLICATION_MODAL);
    root.setBackground(BackgroundController.getCurrentBackground());
    root.getStylesheets().add("file:src/main/resources/maintheme.css");

    BorderPane settingsBorderPane = new BorderPane();

    //Settings for changing theme
    HBox changeThemeBox = new HBox();
    Button themeButtonLeft = new Button("<");
    Text currentTheme = new Text("Forest");
    currentTheme.setId("DefaultText");
    Button themeButtonRight = new Button(">");
    changeThemeBox.setSpacing(15);
    changeThemeBox.getChildren().addAll(themeButtonLeft, currentTheme, themeButtonRight);

    //General settings for the settings window
    VBox settingsVBox = new VBox();
    settingsVBox.setAlignment(Pos.CENTER);
    settingsVBox.getChildren().addAll(changeThemeBox);

    Text settingsTitle = new Text("Settings!");
    settingsBorderPane.setCenter(settingsVBox);
    setAlignment(settingsVBox, Pos.CENTER);

    settingsBorderPane.setTop(settingsTitle);
    settingsBorderPane.setMinWidth(0);
    settingsBorderPane.setPrefWidth(200);
    settingsBorderPane.setPadding(new Insets(30, 30, 30, 30));
    root.getChildren().addAll(settingsBorderPane);

    //TODO Fullscreen button

  }

}