package edu.ntnu.idatt2001.Controller;

import javafx.scene.layout.*;

public class BackgroundController {
  private static Background currentBackground = setBackground("file:src/main/resources/backgroundforest.png");
  private static String backgroundString = "";

  private static Background setBackground(String path) {
    setBackgroundString(path);
    BackgroundRepeat backgroundRepeat = BackgroundRepeat.NO_REPEAT;
    BackgroundPosition backgroundPosition = BackgroundPosition.CENTER;
    BackgroundSize backgroundSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, true, true);
    BackgroundImage backgroundImage = new BackgroundImage(
        new javafx.scene.image.Image(path),
        backgroundRepeat,
        backgroundRepeat,
        backgroundPosition,
        backgroundSize);
    return new Background(backgroundImage);
  }

  public static void setBackgroundString(String backgroundPath) {
    //Getting the name of the background based on the name given in the path to the background file
    String pathString = backgroundPath.substring(backgroundPath.lastIndexOf("/") + 1, backgroundPath.lastIndexOf("."));
    int index = pathString.indexOf("background") + "background".length();
    //Formatting the string to capitalize the first letter of the background
    backgroundString = pathString.substring(index).substring(0, 1).toUpperCase() + pathString.substring(index).substring(1);
  }

  public static Background setBackgroundForest() {
    currentBackground = setBackground("file:src/main/resources/backgroundforest.png");
    return currentBackground;
  }

  public static Background setBackgroundSpace() {
    currentBackground = setBackground("file:src/main/resources/backgroundspace.png");
    return currentBackground;
  }

  public static Background setBackgroundPirate() {
    currentBackground = setBackground("file:src/main/resources/backgroundpirate.png");
    return currentBackground;
  }

  public static Background getCurrentBackground() {
    return currentBackground;
  }

  public static String getBackgroundString() {
        return backgroundString;
    }

  public static Background rotateBackground() {
    switch (backgroundString) {
      case "Space" -> setBackgroundForest();
      case "Pirate" -> setBackgroundSpace();
      case "Forest" -> setBackgroundPirate();
      default -> setBackgroundPirate();
    }
    return currentBackground;
  }
}
