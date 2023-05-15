package edu.ntnu.idatt2001.View;

import edu.ntnu.idatt2001.Controller.BackgroundController;
import edu.ntnu.idatt2001.Controller.MusicController;
import edu.ntnu.idatt2001.Model.FileHandler;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class PathsApplication extends Application {
    static Stage settingsStage;
    static BorderPane settingsRoot;
    static BorderPane pathsWindowRoot;

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
        pathsWindow.setMinWidth(0);
        pathsWindow.setMinHeight(0);
        pathsWindow.setCenter(pathsWindowVBox);
        pathsWindow.setPadding(new javafx.geometry.Insets(50, 200, 50, 200));
        pathsWindow.setVisible(false);
        pathsWindow.setStyle("-fx-background-color: rgb(0,0,0,0)");



        //General JavaFX settings

        windowStackPane.getChildren().addAll(entryWindow, pathsWindow);
        pathsWindowRoot = new BorderPane();
        pathsWindowRoot.setCenter(windowStackPane);
//        root.setBackground(background);


        Scene scene = new Scene(pathsWindowRoot, 1250  , 650);
        stage.setTitle("Paths");
        stage.setScene(scene);
        stage.setMinWidth(750);
        stage.setMinHeight(500);
        stage.show();

        Settings settingsWindow = new Settings(stage.getWidth(), stage.getHeight());

        String currentStylesheet = "file:src/main/resources/maintheme.css";
        scene.getStylesheets().add(currentStylesheet);

        pathsWindowRoot.setBackground(BackgroundController.getCurrentBackground());
        //EVENTS

        //TEMPORARY CHOOSE ADVENTURE BUTTON
        entryWindowChooseAdventureButton.setOnAction(event -> {
           if(FileHandler.openGame(stage)) {
                entryWindow.setVisible(false);
                pathsWindow.setVisible(true);
            }
        });

        settingsButton.setOnAction(event -> {
            showSettings();
        });

        //Allowing the stage to be moved around even with UNDECORATED StageStyle
        windowStackPane.setOnMousePressed(pressEvent -> windowStackPane.setOnMouseDragged(dragEvent -> {
            stage.setX(dragEvent.getScreenX() - pressEvent.getSceneX());
            stage.setY(dragEvent.getScreenY() - pressEvent.getSceneY());
        }));
    }

    public static void showSettings(){
        settingsStage = new Stage();
        settingsStage.initModality(Modality.APPLICATION_MODAL);
        settingsRoot = new BorderPane();
        settingsRoot.setId("SettingsRoot");
        settingsRoot.setBackground(BackgroundController.getCurrentBackground());
        Scene settingsScene = new Scene(settingsRoot, 500, 500);
        settingsStage.setScene(settingsScene);
        settingsStage.setTitle("Settings");

        VBox settingsTitleBox = new VBox();
        Text settingsTitle = new Text("Change settings!");
        settingsTitle.setId("DefaultText");
        settingsTitleBox.getChildren().add(settingsTitle);
        settingsTitleBox.setAlignment(Pos.CENTER);

        HBox changeThemeBox = new HBox();
        Button themeButtonLeft = new Button("<");
        VBox currentThemeBox = new VBox();
        Text currentThemeText = new Text("Forest");
        currentThemeText.setId("DefaultText");
        currentThemeBox.getChildren().add(currentThemeText);
        currentThemeBox.setId("CurrentThemeBox");
        Button themeButtonRight = new Button(">");
        themeButtonRight.setMaxSize(50, 50);
        themeButtonLeft.setMaxSize(50, 50);
        changeThemeBox.setSpacing(15);
        changeThemeBox.getChildren().addAll(themeButtonLeft, currentThemeBox, themeButtonRight);
        changeThemeBox.setAlignment(Pos.CENTER);
        changeThemeBox.setMinWidth(200);
        themeButtonLeft.setOnAction(event -> {
            settingsRoot.setBackground(BackgroundController.rotateBackground());
            pathsWindowRoot.setBackground(BackgroundController.rotateBackground());
            currentThemeText.setText(BackgroundController.getBackgroundString());
        });
        themeButtonRight.setOnAction(event -> {
            settingsRoot.setBackground(BackgroundController.rotateBackground());
            pathsWindowRoot.setBackground(BackgroundController.rotateBackground());
            currentThemeText.setText(BackgroundController.getBackgroundString());
        });

        VBox settingsSoundBox = new VBox();
        settingsSoundBox.setAlignment(Pos.CENTER);
        Button muteButton = new Button("Stop music");
        muteButton.setMaxWidth(284);
        settingsSoundBox.getChildren().addAll(muteButton);
        muteButton.setOnAction(event -> {
            if (muteButton.getText().equals("Stop music")) {
                muteButton.setText("Play music");
                MusicController.pauseMusic();
            } else {
                muteButton.setText("Stop music");
                MusicController.playMusic();
            }
        });

        VBox settingsSoundSliderBox = new VBox();
        Slider settingsSoundSlider = new Slider();
        settingsSoundSlider.setMin(0);
        settingsSoundSlider.setMax(100);
        settingsSoundSlider.setValue(50);
        settingsSoundSlider.setMaxWidth(284);
        settingsSoundSliderBox.setAlignment(Pos.CENTER);

        settingsSoundBox.getChildren().addAll(settingsSoundSlider);
        settingsSoundSlider.addEventHandler(MouseEvent.DRAG_DETECTED, event -> {
            MusicController.musicVolume(settingsSoundSlider.getValue());
            System.out.println(settingsSoundSlider.getValue());
        });



        VBox settingsListBox = new VBox();
        settingsListBox.setAlignment(Pos.CENTER);
        settingsListBox.setSpacing(30);
        settingsListBox.getChildren().addAll(changeThemeBox, settingsSoundSliderBox, settingsSoundBox);

        VBox settingsExitBox = new VBox();
        Button settingsExitButton = new Button("Close");
        settingsExitButton.setMaxWidth(200);
        settingsExitButton.setOnAction(event -> settingsStage.close());
        settingsExitBox.getChildren().add(settingsExitButton);
        settingsExitBox.setAlignment(Pos.CENTER);

        settingsRoot.setTop(settingsTitleBox);
        settingsRoot.setCenter(settingsListBox);
        settingsRoot.setBottom(settingsExitBox);
        settingsRoot.setMinWidth(0);
        settingsRoot.setPrefWidth(500);
        settingsRoot.setPadding(new Insets(30, 30, 30, 30));
//
        settingsScene.getStylesheets().add("file:src/main/resources/maintheme.css");
        settingsRoot.setBackground(BackgroundController.getCurrentBackground());
        settingsStage.show();
    }
}
