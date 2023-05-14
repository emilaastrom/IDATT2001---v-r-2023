package edu.ntnu.idatt2001.View;

import edu.ntnu.idatt2001.Controller.BackgroundController;
import edu.ntnu.idatt2001.Controller.MusicController;
import edu.ntnu.idatt2001.Model.*;
import edu.ntnu.idatt2001.Model.Action.Action;
import edu.ntnu.idatt2001.Model.Goal.Goal;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PathsApplication extends Application {
    Passage currentPassage;
    VBox currentPassageVBox;
    BorderPane pathsWindowCenterBox = new BorderPane();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        MusicController.playMusic();

        //STACKPANE FOR DIFFERENT WINDOWS
        StackPane windowStackPane = new StackPane();

        ////////////////////////////////////////////////////////////
        //ENTRYWINDOW
        ////////////////////////////////////////////////////////////

        VBox entryWindowVBox = new VBox();
        entryWindowVBox.setMinHeight(0);
        entryWindowVBox.setMinWidth(0);
        entryWindowVBox.setAlignment(javafx.geometry.Pos.CENTER);
        entryWindowVBox.setSpacing(30);

        Image entryWindowLogo = new Image("file:src/main/resources/logo.png");
        StackPane entryWindowLogoStackPane = new StackPane();
        entryWindowLogoStackPane.getChildren().add(new javafx.scene.image.ImageView(entryWindowLogo));

        Button entryWindowChooseAdventureButton = new Button("CHOOSE YOUR ADVENTURE");
        entryWindowChooseAdventureButton.setId("mainMenuButton");

        Button settingsButton = new Button("SETTINGS");
        settingsButton.setId("mainMenuButton");

        Button exitGameButton = new Button("EXIT GAME");
        exitGameButton.setId("mainMenuButton");

        entryWindowVBox.getChildren().addAll(entryWindowLogoStackPane, entryWindowChooseAdventureButton, settingsButton, exitGameButton);

        BorderPane entryWindow = new BorderPane();
        entryWindow.setVisible(true);
        entryWindow.setCenter(entryWindowVBox);
        entryWindow.setStyle("-fx-background-color: rgb(0,0,0,0)");


        ////////////////////////////////////////////////////////////
        //PATHSWINDOW
        ////////////////////////////////////////////////////////////

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
        Text pathsWindowBottomBoxHBoxTextScore = new Text(Integer.toString(Game.getInstance().getPlayer().getScore()));
        pathsWindowBottomBoxHBoxTextScore.setId("scoreText");
        ImageView pathsWindowBottomBoxHBoxImageViewHeart = new ImageView("file:src/main/resources/heart.png");
        Text pathsWindowBottomBoxHBoxTextHeart = new Text(Integer.toString(Game.getInstance().getPlayer().getHealth()));
        pathsWindowBottomBoxHBoxTextHeart.setId("heartText");
        ImageView pathsWindowBottomBoxHBoxImageViewCoin = new ImageView("file:src/main/resources/coin.png");
        Text pathsWindowBottomBoxHBoxTextCoin = new Text(Integer.toString(Game.getInstance().getPlayer().getGold()));
        pathsWindowBottomBoxHBoxTextCoin.setId("coinText");
        ImageView pathsWindowBottomBoxHBoxImageViewChest = new ImageView("file:src/main/resources/chest.png");
        pathsWindowBottomBoxHBox.setFillHeight(true);

        pathsWindowBottomBoxHBox.getChildren().addAll(
                pathsWindowBottomBoxHBoxImageViewScore,
                pathsWindowBottomBoxHBoxTextScore,
                pathsWindowBottomBoxHBoxImageViewHeart,
                pathsWindowBottomBoxHBoxTextHeart,
                pathsWindowBottomBoxHBoxImageViewCoin,
                pathsWindowBottomBoxHBoxTextCoin,
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
        pathsWindow.setMinWidth(0);
        pathsWindow.setMinHeight(0);
        pathsWindow.setCenter(pathsWindowVBox);
        pathsWindow.setPadding(new javafx.geometry.Insets(50, 200, 50, 200));
        pathsWindow.setVisible(false);
        pathsWindow.setStyle("-fx-background-color: rgb(0,0,0,0)");



        //General JavaFX settings

        windowStackPane.getChildren().addAll(entryWindow, pathsWindow);
        BorderPane root = new BorderPane();
        root.setCenter(windowStackPane);
        //root.setBackground(background);


        Scene scene = new Scene(root, 1250  , 650);
        stage.setTitle("Paths");
        stage.setScene(scene);
        stage.setMinWidth(750);
        stage.setMinHeight(500);
        //stage.setResizable(false);
        //stage.initStyle(StageStyle.UNDECORATED);
        //stage.setMaximized(true);
        stage.show();

        Settings settingsWindow = new Settings(stage.getWidth(), stage.getHeight());

        String currentStylesheet = "file:src/main/resources/maintheme.css";
        scene.getStylesheets().add(currentStylesheet);

        root.setBackground(BackgroundController.getCurrentBackground());
        //EVENTS

        //TEMPORARY CHOOSE ADVENTURE BUTTON
        entryWindowChooseAdventureButton.setOnAction(event -> {
           if(FileHandler.openGame(stage)) {
                entryWindow.setVisible(false);
                pathsWindow.setVisible(true);

               currentPassage = Game.getInstance().getStory().getOpeningPassage();
               currentPassageVBox = writePassage(currentPassage, stage);
               System.out.println(currentPassageVBox.getChildren().size() + " children");

               pathsWindowCenterBox.setCenter(currentPassageVBox);

               stage.show();
            }
        });

        settingsButton.setOnAction(event -> {
            Settings settings = new Settings(stage.getWidth(), stage.getHeight());
            settings.show();
            //stage.setMaximized(true);
        });

        //Allowing the stage to be moved around even with UNDECORATED StageStyle
        windowStackPane.setOnMousePressed(pressEvent -> windowStackPane.setOnMouseDragged(dragEvent -> {
            stage.setX(dragEvent.getScreenX() - pressEvent.getSceneX());
            stage.setY(dragEvent.getScreenY() - pressEvent.getSceneY());
        }));
    }

    public VBox writePassage(Passage passage, Stage stage) {
        VBox pathsWindowCenterBoxVBox = new VBox();
        pathsWindowCenterBoxVBox.setSpacing(40);
        pathsWindowCenterBoxVBox.setAlignment(Pos.CENTER);

        Text titleText = new Text();
        titleText.setText(passage.getTitle());
        titleText.setId("titleText");

        Text contentText = new Text();
        contentText.setText(passage.getContent());
        contentText.setId("contentText");

        pathsWindowCenterBoxVBox.getChildren().clear();
        pathsWindowCenterBoxVBox.getChildren().addAll(titleText, contentText);

        VBox buttonsVBox = new VBox();
        buttonsVBox.setSpacing(10);
        buttonsVBox.setAlignment(Pos.CENTER);
        for(Link link : passage.getLinks()) {
            Button linkButton = new Button();
            linkButton.setText(link.getText());
            linkButton.setPrefWidth(Region.USE_COMPUTED_SIZE);
            linkButton.setId("linkButton");
            linkButton.setOnAction(event -> {
                for(Action action : link.getActions()) {
                    action.execute(Game.getInstance().getPlayer());
                }
                currentPassage = Game.getInstance().getStory().getPassage(link);
                currentPassageVBox = writePassage(currentPassage, stage);
                pathsWindowCenterBox.setCenter(currentPassageVBox);
                stage.show();
            });
            buttonsVBox.getChildren().add(linkButton);
        }
        pathsWindowCenterBoxVBox.getChildren().add(buttonsVBox);
        return pathsWindowCenterBoxVBox;
    }
}
