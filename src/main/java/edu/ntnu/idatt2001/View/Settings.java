package edu.ntnu.idatt2001.View;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.io.FileNotFoundException;

class Settings extends Scene {

  public Settings(String path) {
    super(new BorderPane(), 1000, 750);
    BorderPane root = (BorderPane) this.getRoot();
    //GridPane settings

    HBox changeThemeBox = new HBox();
    Button themeButtonLeft = new Button("<");
    Text currentTheme = new Text("Forest");
    Button themeButtonRight = new Button(">");
    changeThemeBox.getChildren().addAll(themeButtonLeft, currentTheme, themeButtonRight);

    //TODO Fullscreen button

  }
}