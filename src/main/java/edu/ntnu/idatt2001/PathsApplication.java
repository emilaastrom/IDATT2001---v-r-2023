package edu.ntnu.idatt2001;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;

public class PathsApplication extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        playMusic();

        //STACKPANE FOR DIFFERENT WINDOWS
        StackPane windowStackPane = new StackPane();

        //ENTRYWINDOW
        VBox entryWindowVBox = new VBox();
        entryWindowVBox.setPrefWidth(500);
        entryWindowVBox.setAlignment(javafx.geometry.Pos.CENTER);
        entryWindowVBox.setSpacing(30);

        Image entryWindowLogo = new Image("file:src/main/resources/logo.png");
        StackPane entryWindowLogoStackPane = new StackPane();
        entryWindowLogoStackPane.getChildren().add(new javafx.scene.image.ImageView(entryWindowLogo));

        Button entryWindowChooseAdventureButton = new Button("CHOOSE YOUR ADVENTURE");
        entryWindowChooseAdventureButton.setFont(Font.loadFont("file:src/main/resources/textFont.otf", 16));
        entryWindowChooseAdventureButton.setPrefWidth(400);
        entryWindowChooseAdventureButton.setId("mainMenuButton");

        Button settingsButton = new Button("SETTINGS");
        settingsButton.setFont(Font.loadFont("file:src/main/resources/textFont.otf", 16));
        settingsButton.setPrefWidth(400);
        settingsButton.setId("mainMenuButton");

        Button exitGameButton = new Button("EXIT GAME");
        exitGameButton.setFont(Font.loadFont("file:src/main/resources/textFont.otf", 16));
        exitGameButton.setPrefWidth(400);
        exitGameButton.setId("mainMenuButton");

        entryWindowVBox.getChildren().addAll(entryWindowLogoStackPane, entryWindowChooseAdventureButton, settingsButton, exitGameButton);

        BorderPane entryWindow = new BorderPane();
        entryWindow.setVisible(true);
        entryWindow.setCenter(entryWindowVBox);
        entryWindow.setStyle("-fx-background-color: rgb(0,0,0,0)");



        //PATHSWINDOW
        BorderPane pathsWindowCenterBox = new BorderPane();
        pathsWindowCenterBox.setPrefWidth(500);
        pathsWindowCenterBox.setPrefHeight(400);
        pathsWindowCenterBox.setStyle(
                "-fx-background-color: rgb(0,0,0,0.5);" +
                "-fx-border-color: rgb(255,255,255);" +
                "-fx-border-width: 5px;"
        );

        BorderPane pathsWindowBottomBox = new BorderPane();
        pathsWindowBottomBox.setPrefWidth(500);
        pathsWindowBottomBox.setPrefHeight(100);
        pathsWindowBottomBox.setStyle(
                "-fx-background-color: rgb(0,0,0,0.5);" +
                "-fx-border-color: rgb(255,255,255);" +
                "-fx-border-width: 5px;"
        );

        HBox pathsWindowBottomBoxHBox = new HBox();
        pathsWindowBottomBoxHBox.setSpacing(30);
        pathsWindowBottomBoxHBox.setAlignment(Pos.CENTER_LEFT);
        pathsWindowBottomBoxHBox.setPadding(new javafx.geometry.Insets(0, 0, 0, 50));
        ImageView pathsWindowBottomBoxHBoxImageViewScore = new ImageView("file:src/main/resources/score.png");
        ImageView pathsWindowBottomBoxHBoxImageViewHeart = new ImageView("file:src/main/resources/heart.png");
        ImageView pathsWindowBottomBoxHBoxImageViewCoin = new ImageView("file:src/main/resources/coin.png");
        ImageView pathsWindowBottomBoxHBoxImageViewChest = new ImageView("file:src/main/resources/chest.png");
        pathsWindowBottomBoxHBox.setFillHeight(true);

        pathsWindowBottomBoxHBox.getChildren().addAll(pathsWindowBottomBoxHBoxImageViewScore,
                pathsWindowBottomBoxHBoxImageViewHeart,
                pathsWindowBottomBoxHBoxImageViewCoin,
                pathsWindowBottomBoxHBoxImageViewChest);
        pathsWindowBottomBox.setLeft(pathsWindowBottomBoxHBox);

        HBox pathsWindowBottomBoxHBox2 = new HBox();
        pathsWindowBottomBoxHBox2.setSpacing(30);
        pathsWindowBottomBoxHBox2.setAlignment(Pos.CENTER_RIGHT);
        pathsWindowBottomBoxHBox2.setPadding(new javafx.geometry.Insets(0, 50, 0, 0));
        ImageView pathsWindowBottomBoxHBox2ImageViewHelp = new ImageView("file:src/main/resources/help.png");
        ImageView pathsWindowBottomBoxHBox2ImageViewSettings = new ImageView("file:src/main/resources/settings.png");
        pathsWindowBottomBoxHBox2.getChildren().addAll(
                pathsWindowBottomBoxHBox2ImageViewHelp,
                pathsWindowBottomBoxHBox2ImageViewSettings);
        pathsWindowBottomBox.setRight(pathsWindowBottomBoxHBox2);

        VBox pathsWindowVBox = new VBox();
        pathsWindowVBox.setSpacing(30);
        pathsWindowVBox.getChildren().addAll(pathsWindowCenterBox, pathsWindowBottomBox);
        pathsWindowVBox.setAlignment(javafx.geometry.Pos.CENTER);

        BorderPane pathsWindow = new BorderPane();
        pathsWindow.setCenter(pathsWindowVBox);
        pathsWindow.setPadding(new javafx.geometry.Insets(50, 200, 50, 200));
        pathsWindow.setVisible(false);
        pathsWindow.setStyle("-fx-background-color: rgb(0,0,0,0)");



        //General JavaFX settings
        BackgroundRepeat backgroundRepeat = BackgroundRepeat.REPEAT;
        BackgroundPosition backgroundPosition = BackgroundPosition.CENTER;
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(
                new javafx.scene.image.Image("file:src/main/resources/forestBackground.jpg"),
                backgroundRepeat,
                backgroundRepeat,
                backgroundPosition,
                backgroundSize);
        Background background = new Background(backgroundImage);

        windowStackPane.getChildren().addAll(entryWindow, pathsWindow);
        BorderPane root = new BorderPane();
        root.setCenter(windowStackPane);
        root.setBackground(background);


        Scene scene = new Scene(root, 1500  , 750);
        stage.setTitle("Paths");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();

        String currentStylesheet = "file:src/main/resources/maintheme.css";
        scene.getStylesheets().add(currentStylesheet);

        //EVENTS

        //TEMPORARY CHOOSE ADVENTURE BUTTON
        entryWindowChooseAdventureButton.setOnAction(event -> {
            pathsWindow.setVisible(true);
            entryWindow.setVisible(false);
        });

        //Allowing the stage to be moved around even with UNDECORATED StageStyle
        windowStackPane.setOnMousePressed(pressEvent -> windowStackPane.setOnMouseDragged(dragEvent -> {
            stage.setX(dragEvent.getScreenX() - pressEvent.getSceneX());
            stage.setY(dragEvent.getScreenY() - pressEvent.getSceneY());
        }));
    }

    MediaPlayer mediaPlayer;
    public void playMusic() {
        String musicFile = "src/main/resources/music.mp3";
        Media sound = new Media(new File(musicFile).toURI().toString());
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(Duration.ZERO));
        mediaPlayer.play();
    }
}
