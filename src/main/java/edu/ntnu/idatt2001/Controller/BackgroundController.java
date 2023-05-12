package edu.ntnu.idatt2001.Controller;

import javafx.scene.layout.*;

public class BackgroundController {
  private static Background currentBackground = setBackground("file:src/main/resources/backgroundforest.png");

  private static Background setBackground(String path) {
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

  public Background setBackgroundForest() {
    currentBackground = setBackground("file:/src/main/resources/backgroundforest.png");
    return currentBackground;
  }

  public Background setBackgroundSpace() {
    currentBackground = setBackground("file:src/main/resources/backgroundspace.png");
    return currentBackground;
  }

  public Background setBackgroundPirate() {
    currentBackground = setBackground("file:src/main/resources/backgroundpirate.png");
    return currentBackground;
  }

  public static Background getCurrentBackground() {
    return currentBackground;
  }
}
