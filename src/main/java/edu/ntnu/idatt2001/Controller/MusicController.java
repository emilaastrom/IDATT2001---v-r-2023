package edu.ntnu.idatt2001.Controller;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;

public class MusicController {
  private static MediaPlayer mediaPlayer;

  public static void playMusic() {
    String musicFile = "src/main/resources/music.mp3";
    Media sound = new Media(new File(musicFile).toURI().toString());
    mediaPlayer = new MediaPlayer(sound);
    mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(Duration.ZERO));
    mediaPlayer.play();
  }

  public static void pauseMusic() {
    mediaPlayer.pause();
  }

  public static void resumeMusic() {
    mediaPlayer.play();
  }

  public static void musicVolume(double volume) {
    mediaPlayer.volumeProperty().setValue(volume);
    mediaPlayer.setVolume(volume);
  }
}
