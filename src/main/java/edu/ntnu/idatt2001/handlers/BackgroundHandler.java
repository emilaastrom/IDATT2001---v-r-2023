package edu.ntnu.idatt2001.handlers;

import edu.ntnu.idatt2001.Main;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;

/**
 * Controller for BackgroundView.
 */
public class BackgroundHandler {
  private static Background currentBackground = setBackground(
      Main.class.getResource("/backgrounds/backgroundforest.png").toString());
  private static String backgroundString = "";

  /**
   * sets the background from a given path.

   * @param path the path to the background image file
   * @return the new background
   */
  private static Background setBackground(String path) {
    //Setting the background string
    setBackgroundString(path);

    //Setting the background image
    BackgroundRepeat backgroundRepeat = BackgroundRepeat.NO_REPEAT;
    BackgroundPosition backgroundPosition = BackgroundPosition.CENTER;
    BackgroundSize backgroundSize = new BackgroundSize(
            BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, true, true);
    BackgroundImage backgroundImage = new BackgroundImage(
        new javafx.scene.image.Image(path),
        backgroundRepeat,
        backgroundRepeat,
        backgroundPosition,
        backgroundSize);
    return new Background(backgroundImage);
  }

  /**
   * Sets the background string.
   *
   * @param backgroundPath the path to the background image file
   */
  public static void setBackgroundString(String backgroundPath) {
    //Getting the name of the background based on the name given in the path to the background file
    String pathString = backgroundPath.substring(backgroundPath.lastIndexOf("/")
            + 1, backgroundPath.lastIndexOf("."));
    int index = pathString.indexOf("background") + "background".length();
    //Formatting the string to capitalize the first letter of the background
    backgroundString = pathString.substring(index).substring(0, 1).toUpperCase()
            + pathString.substring(index).substring(1);
  }

  /**
   * Sets the background to forest.
   *
   * @return the new background
   */
  public static Background setBackgroundForest() {
    currentBackground = setBackground(Main.class.getResource("/backgrounds/backgroundforest.png").toString());
    return currentBackground;
  }

  /**
   * Sets the background to space.
   *
   * @return the new background
   */
  public static Background setBackgroundSpace() {
    currentBackground = setBackground(Main.class.getResource("/backgrounds/backgroundspace.png").toString());
    return currentBackground;
  }

  /**
   * Sets the background to pirate.
   *
   * @return the new background
   */
  public static Background setBackgroundPirate() {
    currentBackground = setBackground(Main.class.getResource("/backgrounds/backgroundpirate.png").toString());
    return currentBackground;
  }

  /**
   * Gets the current background.
   *
   * @return the current background
   */
  public static Background getCurrentBackground() {
    return currentBackground;
  }

  /**
   * Gets the background string.
   *
   * @return the background string
   */
  public static String getBackgroundString() {
    return backgroundString;
  }

  /**
   * Rotates between different backgrounds.
   *
   * @return the new background
   */
  public static Background rotateBackground() {
    //Switching between different backgrounds based on the background string
    switch (backgroundString) {
      case "Space" -> setBackgroundForest();
      case "Pirate" -> setBackgroundSpace();
      case "Forest" -> setBackgroundPirate();
      default -> setBackgroundPirate();
    }
    return currentBackground;
  }
}
