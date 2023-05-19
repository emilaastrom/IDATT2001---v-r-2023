package edu.ntnu.idatt2001.Controller;

import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;

public class MusicController {
  private static MediaPlayer mediaPlayer;
  private static Slider volumeSlider = new Slider(0, 100, 50);

  public static void playMusic() {
    String musicFile = "src/main/resources/music.mp3";
    Media sound = new Media(new File(musicFile).toURI().toString());
    mediaPlayer = new MediaPlayer(sound);
    mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(Duration.ZERO));
    mediaPlayer.play();
    mediaPlayer.volumeProperty().bind(volumeSlider.valueProperty().divide(200));
  }

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
