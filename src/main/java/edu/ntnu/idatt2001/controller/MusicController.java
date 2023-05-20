package edu.ntnu.idatt2001.controller;

import java.io.File;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

/**
 * Music controller class for playing soundtrack.
 */
public class MusicController {
  private static MediaPlayer mediaPlayer;
  private static Slider volumeSlider = new Slider(0, 100, 50);

  /**
   * Play music.
   */
  public static void playMusic() {
    String musicFile = "src/main/resources/music.mp3";
    Media sound = new Media(new File(musicFile).toURI().toString());
    mediaPlayer = new MediaPlayer(sound);
    mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(Duration.ZERO));
    mediaPlayer.play();
    mediaPlayer.volumeProperty().bind(volumeSlider.valueProperty().divide(200));
  }

  /**
   * Pause music.
   */
  public static void pauseMusic() {
    mediaPlayer.pause();
  }

  public static MediaPlayer getMediaPlayer() {
    return mediaPlayer;
  }

  public static Slider getVolumeSlider() {
    return volumeSlider;
  }
}
