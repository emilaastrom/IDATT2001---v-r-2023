package edu.ntnu.idatt2001.handlers;

import java.io.File;

import edu.ntnu.idatt2001.Main;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

/**
 * Music controller class for playing soundtrack.
 */
public class MusicHandler {
  private static MediaPlayer mediaPlayer;
  private static final Slider volumeSlider = new Slider(0, 100, 50);

  /**
   * Play music.
   */
  public static void playMusic() {
    //gets the music file
    Media sound = new Media(Main.class.getResource("/soundtrack.mp3").toString());
    mediaPlayer = new MediaPlayer(sound);
    //loops the music
    mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(Duration.ZERO));
    mediaPlayer.play();
    //binds the volume slider to the volume of the media player
    mediaPlayer.volumeProperty().bind(volumeSlider.valueProperty().divide(200));
  }

  /**
   * Pause music.
   */
  public static void pauseMusic() {
    mediaPlayer.pause();
  }

  /**
   * gets the volume slider.
   */
  public static Slider getVolumeSlider() {
    return volumeSlider;
  }
}
