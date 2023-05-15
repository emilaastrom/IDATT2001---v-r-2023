package edu.ntnu.idatt2001.View;

import edu.ntnu.idatt2001.Controller.BackgroundController;
import edu.ntnu.idatt2001.Controller.MusicController;
import edu.ntnu.idatt2001.Model.FileHandler;
import edu.ntnu.idatt2001.Model.*;
import edu.ntnu.idatt2001.Model.Action.Action;
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
import javafx.stage.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class PathsApplication extends Application {
    static Stage settingsStage;
    static BorderPane settingsRoot;
    static BorderPane pathsWindowRoot;
    static BorderPane mainWindowDimmer;
    static String currentStylesheet;
    static Passage currentPassage;
    static VBox currentPassageVBox;
    static Player player;
    static List<Goal> goals;
    static Game game;
    static Story story;
    static BorderPane pathsWindowCenterBox = new BorderPane();
    static BorderPane pathsWindowBottomBox = new BorderPane();
    static HBox pathsWindowBottomBoxHBox = new HBox();
    static HBox pathsWindowBottomBoxHBox2 = new HBox();
    static Text pathsWindowBottomBoxHBoxTextScore = new Text();
    static Text pathsWindowBottomBoxHBoxTextHeart = new Text();
    static Text pathsWindowBottomBoxHBoxTextCoin = new Text();
    static ImageView pathsWindowBottomBoxHBoxImageViewScore = new ImageView("file:src/main/resources/score.png");
    static ImageView pathsWindowBottomBoxHBoxImageViewHeart = new ImageView("file:src/main/resources/heart.png");
    static ImageView pathsWindowBottomBoxHBoxImageViewCoin = new ImageView("file:src/main/resources/coin.png");
    static ImageView pathsWindowBottomBoxHBoxImageViewChest = new ImageView("file:src/main/resources/chest.png");

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        MusicController.playMusic();
        showMainWindow(stage);

    }

    public static void showMainWindow(Stage stage){
        //STACKPANE FOR DIFFERENT WINDOWS
        StackPane windowStackPane = new StackPane();
        mainWindowDimmer = new BorderPane();
        mainWindowDimmer.setVisible(false);
        mainWindowDimmer.setId("mainWindowDimmer");
        mainWindowDimmer.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5);");


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

        Button entryWindowOpenAdventureButton = new Button("PRESELECTED ADVENTURE");
        entryWindowOpenAdventureButton.setId("mainMenuButton");

        Button settingsButton = new Button("SETTINGS");
        settingsButton.setId("mainMenuButton");

        Button exitGameButton = new Button("EXIT GAME");
        exitGameButton.setId("mainMenuButton");
        exitGameButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> showExitConfirmation());

        entryWindowVBox.getChildren().addAll(entryWindowLogoStackPane, entryWindowChooseAdventureButton, entryWindowOpenAdventureButton, settingsButton, exitGameButton);

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

        pathsWindowBottomBox.setPrefWidth(500);
        pathsWindowBottomBox.setPrefHeight(100);
        pathsWindowBottomBox.setStyle(
                "-fx-background-color: rgb(0,0,0,0.5);" +
                        "-fx-border-color: rgb(255,255,255);" +
                        "-fx-border-width: 5px;"
        );

        updateBottomBox();

        pathsWindowBottomBoxHBox2.setSpacing(30);
        pathsWindowBottomBoxHBox2.setAlignment(Pos.CENTER_RIGHT);
        pathsWindowBottomBoxHBox2.setPadding(new javafx.geometry.Insets(0, 50, 0, 0));
        ImageView pathsWindowBottomBoxHBox2ImageViewHelp = new ImageView("file:src/main/resources/help.png");
        ImageView pathsWindowBottomBoxHBox2ImageViewSettings = new ImageView("file:src/main/resources/settings.png");
        pathsWindowBottomBoxHBox2.getChildren().addAll(
                pathsWindowBottomBoxHBox2ImageViewHelp,
                pathsWindowBottomBoxHBox2ImageViewSettings);

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

        windowStackPane.getChildren().addAll(entryWindow, pathsWindow, mainWindowDimmer);
        pathsWindowRoot = new BorderPane();
        pathsWindowRoot.setCenter(windowStackPane);
        //Defaulting to FOREST background
        pathsWindowRoot.setBackground(BackgroundController.setBackgroundForest());
//        root.setBackground(background);


        Scene scene = new Scene(pathsWindowRoot, 1250  , 650);
        stage.setTitle("Paths");
        stage.setScene(scene);
        stage.setMinWidth(750);
        stage.setMinHeight(500);
        stage.show();

//        Settings settingsWindow = new Settings(stage.getWidth(), stage.getHeight());

        currentStylesheet = "file:src/main/resources/maintheme.css";
        scene.getStylesheets().add(currentStylesheet);

        //EVENTS

        //TEMPORARY CHOOSE ADVENTURE BUTTON
        entryWindowChooseAdventureButton.setOnAction(event -> {
            if(FileHandler.openGame(stage)) {
                entryWindow.setVisible(false);
                pathsWindow.setVisible(true);

                currentPassage = Game.getInstance().getStory().getOpeningPassage();
                currentPassageVBox = writePassage(currentPassage, stage);

                pathsWindowCenterBox.setCenter(currentPassageVBox);

                stage.show();
            }
        });

        entryWindowOpenAdventureButton.setOnAction(event -> {
            //FileHandler.openStaticGame(stage);
            File selectedFile = new File("src/main/resources/exampleStory.paths");
            String path = selectedFile.getAbsolutePath();
            try{
                story = FileHandler.readFile(path);
                try{
                    player = new Player.PlayerBuilder("Ola Nordmann").build();
                    goals = new ArrayList<>();
                    game = new Game();
                    game.setPlayer(player);
                    game.setStory(story);
                    game.setGoals(goals);
                    entryWindow.setVisible(false);
                    pathsWindow.setVisible(true);
                    game.begin();

                    currentPassage = game.getStory().getOpeningPassage();
                    currentPassageVBox = writePassage(currentPassage, stage);

                    pathsWindowCenterBox.setCenter(currentPassageVBox);

                    stage.show();
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        settingsButton.setOnAction(event -> showSettings());

        //Allowing the stage to be moved around even with UNDECORATED StageStyle
        windowStackPane.setOnMousePressed(pressEvent -> windowStackPane.setOnMouseDragged(dragEvent -> {
            stage.setX(dragEvent.getScreenX() - pressEvent.getSceneX());
            stage.setY(dragEvent.getScreenY() - pressEvent.getSceneY());
        }));
    }

    public static void showSettings(){
        mainWindowDimmer.setVisible(true);
        settingsStage = new Stage();
        settingsStage.initModality(Modality.APPLICATION_MODAL);
        settingsRoot = new BorderPane();
        settingsRoot.setId("SettingsRoot");
//        settingsRoot.setBackground(BackgroundController.setBackgroundSpace());
        Scene settingsScene = new Scene(settingsRoot, 500, 500);
        settingsStage.setScene(settingsScene);
        settingsStage.setTitle("Settings");
        settingsStage.addEventHandler(WindowEvent.WINDOW_HIDDEN, windowEvent -> mainWindowDimmer.setVisible(false));

        VBox settingsTitleBox = new VBox();
        Text settingsTitle = new Text("Change settings!");
        settingsTitle.setId("DefaultText");
        settingsTitleBox.getChildren().add(settingsTitle);
        settingsTitleBox.setAlignment(Pos.CENTER);

        HBox changeThemeBox = new HBox();
        Button themeButtonLeft = new Button("<");
        VBox currentThemeBox = new VBox();
        Text currentThemeText = new Text(BackgroundController.getBackgroundString());
        currentThemeText.setId("DefaultText");
        currentThemeBox.getChildren().add(currentThemeText);
        currentThemeBox.setId("CurrentThemeBox");
        currentThemeBox.setMinWidth(135);
        currentThemeBox.setMaxWidth(135);
        currentThemeBox.setAlignment(Pos.CENTER);
        Button themeButtonRight = new Button(">");
        themeButtonRight.setMaxSize(50, 50);
        themeButtonLeft.setMaxSize(50, 50);
        changeThemeBox.setSpacing(15);
        changeThemeBox.getChildren().addAll(themeButtonLeft, currentThemeBox, themeButtonRight);
        changeThemeBox.setAlignment(Pos.CENTER);
        changeThemeBox.setMinWidth(200);
        themeButtonLeft.setOnAction(event -> {
//            settingsRoot.setBackground(BackgroundController.rotateBackground());
            pathsWindowRoot.setBackground(BackgroundController.rotateBackground());
            currentThemeText.setText(BackgroundController.getBackgroundString());
        });
        themeButtonRight.setOnAction(event -> {
//            settingsRoot.setBackground(BackgroundController.rotateBackground());
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
        settingsSoundSlider.addEventHandler(MouseEvent.MOUSE_DRAGGED, event -> {
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

    public static void showExitConfirmation(){
        mainWindowDimmer.setVisible(true);
        Stage exitConfirmationStage = new Stage();
        exitConfirmationStage.setTitle("Exit confirmation");
        exitConfirmationStage.initModality(Modality.APPLICATION_MODAL);
        BorderPane exitConfirmationRoot = new BorderPane();
        exitConfirmationRoot.setId("ExitConfirmationRoot");
        Scene exitConfirmationScene = new Scene(exitConfirmationRoot, 500, 150);
        exitConfirmationStage.setScene(exitConfirmationScene);

        Button exitConfirmationButton = new Button("Exit");
        exitConfirmationButton.setId("ExitConfirmationButton");
        exitConfirmationButton.setMaxWidth(150);
        exitConfirmationButton.setOnAction(event -> System.exit(0));
        Button exitConfirmationCancelButton = new Button("Cancel");
        exitConfirmationCancelButton.setMaxWidth(150);
        exitConfirmationCancelButton.setOnAction(event -> {
            mainWindowDimmer.setVisible(false);
            exitConfirmationStage.close();
        });
        exitConfirmationStage.addEventHandler(WindowEvent.WINDOW_HIDDEN, windowEvent -> mainWindowDimmer.setVisible(false));
        HBox exitConfirmationButtonBox = new HBox();
        exitConfirmationButtonBox.getChildren().addAll(exitConfirmationButton, exitConfirmationCancelButton);
        exitConfirmationButtonBox.setAlignment(Pos.CENTER);
        exitConfirmationButtonBox.setSpacing(20);
        exitConfirmationRoot.setCenter(exitConfirmationButtonBox);
        exitConfirmationScene.getStylesheets().add(currentStylesheet);
        exitConfirmationStage.show();
    }

    public static VBox writePassage(Passage passage, Stage stage) {
        VBox pathsWindowCenterBoxVBox = new VBox();
        pathsWindowCenterBoxVBox.setSpacing(40);
        pathsWindowCenterBoxVBox.setAlignment(Pos.CENTER);

        Text titleText = new Text();
        titleText.setText(Objects.requireNonNull(passage.getTitle()));
        titleText.setId("titleText");

        Text contentText = new Text();
        contentText.setText(Objects.requireNonNull(passage.getContent()));
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
                updateBottomBox();

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

    private static void updateBottomBox() {
        pathsWindowBottomBox.getChildren().clear();
        pathsWindowBottomBoxHBox.getChildren().clear();
        pathsWindowBottomBoxHBox.setSpacing(30);
        pathsWindowBottomBoxHBox.setAlignment(Pos.CENTER_LEFT);
        pathsWindowBottomBoxHBox.setPadding(new javafx.geometry.Insets(0, 0, 0, 50));

        pathsWindowBottomBoxHBoxTextScore = new Text(Integer.toString(Game.getInstance().getPlayer().getScore()));
        pathsWindowBottomBoxHBoxTextHeart = new Text(Integer.toString(Game.getInstance().getPlayer().getHealth()));
        pathsWindowBottomBoxHBoxTextCoin = new Text(Integer.toString(Game.getInstance().getPlayer().getGold()));

        pathsWindowBottomBoxHBoxTextScore.setId("scoreText");
        pathsWindowBottomBoxHBoxTextHeart.setId("heartText");
        pathsWindowBottomBoxHBoxTextCoin.setId("coinText");
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
        pathsWindowBottomBox.setRight(pathsWindowBottomBoxHBox2);
    }
}
