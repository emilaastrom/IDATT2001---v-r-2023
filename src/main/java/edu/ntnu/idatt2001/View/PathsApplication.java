/*
package edu.ntnu.idatt2001.View;

import edu.ntnu.idatt2001.Controller.BackgroundController;
import edu.ntnu.idatt2001.Controller.MusicController;
import edu.ntnu.idatt2001.Controller.UserInformer;
import edu.ntnu.idatt2001.Model.Action.InventoryAction;
import edu.ntnu.idatt2001.Model.FileHandler;
import edu.ntnu.idatt2001.Model.*;
import edu.ntnu.idatt2001.Model.Action.Action;
import edu.ntnu.idatt2001.Model.Goal.Goal;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;


public class PathsApplication  {
    static Stage settingsStage;
    static BorderPane settingsRoot;
    static Stage gameSelectionStage;
    static BorderPane gameSelectionRoot;
    static BorderPane pathsWindowRoot;
    static BorderPane mainWindowDimmer;
    static String currentStylesheet;
    static Passage currentPassage;
    static VBox currentPassageVBox;
    static Player player;
    static List<Goal> goals;
    static Game game = Game.getInstance();
    static Story story;
    static Stage inventoryStage;
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
    static ImageView pathsWindowBottomBoxHBox2ImageViewHelp = new ImageView("file:src/main/resources/help.png");
    static ImageView pathsWindowBottomBoxHBox2ImageViewSettings = new ImageView("file:src/main/resources/settings.png");



    public static void showMainWindow(Stage stage){
        //STACKPANE FOR DIFFERENT WINDOWS
        StackPane windowStackPane = new StackPane();
        mainWindowDimmer = new BorderPane();
        mainWindowDimmer.setVisible(false);
        mainWindowDimmer.setMouseTransparent(true);
        mainWindowDimmer.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5);");
        mainWindowDimmer.setId("mainWindowDimmer");


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

        Scene scene = new Scene(pathsWindowRoot, 1250  , 650);
        stage.setTitle("Paths");
        stage.setScene(scene);
        stage.setMinWidth(750);
        stage.setMinHeight(500);
        stage.show();

        currentStylesheet = "file:src/main/resources/maintheme.css";
        scene.getStylesheets().add(currentStylesheet);

        //EVENTS

        //TEMPORARY CHOOSE ADVENTURE BUTTON
        entryWindowChooseAdventureButton.setOnAction(event -> {
            if(FileHandler.openGame(stage, "TESTPLAYERNAME in pathsapplication")) {
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

    public static void showGameSelection(){
        mainWindowDimmer.setVisible(true);
        gameSelectionStage = new Stage();
        gameSelectionStage.initModality(Modality.APPLICATION_MODAL);
        gameSelectionRoot = new BorderPane();
        gameSelectionRoot.setId("GameSelectionRoot");

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
            }
        });

        VBox settingsSoundSliderBox = new VBox();
        Slider settingsSoundSlider = new Slider();
        settingsSoundSlider.setMin(0);
        settingsSoundSlider.setMax(100);
        settingsSoundSlider.setValue(50);
        settingsSoundSlider.setMaxWidth(284);
        settingsSoundSliderBox.setAlignment(Pos.CENTER);


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
        try{
        VBox pathsWindowCenterBoxVBox = new VBox();
        pathsWindowCenterBoxVBox.setSpacing(40);
        pathsWindowCenterBoxVBox.setAlignment(Pos.CENTER);

        Text titleText = new Text();
        titleText.setText(Objects.requireNonNull(passage.getTitle()));
        titleText.setId("titleText");

        Text contentText = new Text();
        contentText.setText(Objects.requireNonNull(passage.getContent()));
        contentText.setId("contentText");
        contentText.setWrappingWidth(800);
        contentText.setTextAlignment(TextAlignment.CENTER);

        pathsWindowCenterBoxVBox.getChildren().clear();
        pathsWindowCenterBoxVBox.getChildren().addAll(titleText, contentText);

        VBox buttonsVBox = new VBox();
        buttonsVBox.setSpacing(10);
        buttonsVBox.setAlignment(Pos.CENTER);
            for (Link link : passage.getLinks()) {
                //check if link is active
                List<Action> actions = link.getActions();
                boolean activeLink = true;
                boolean itemNotFound = false;
                for (Object action : actions) {
                    //check if action is inventory action
                    if (action instanceof InventoryAction inventoryAction) {
                        //check if action is subtract item-action
                        if (inventoryAction.getAmount().startsWith("-")) {
                            boolean found = false;
                            for (String item : Game.getInstance().getPlayer().getInventory()) {
                                if (item.equals(inventoryAction.getAmount().substring(1))) {
                                    found = true;
                                    break;
                                }
                            }
                            if (!found) {
                                activeLink = false;
                                break;
                            }
                        }
                    }
                }

                if (!activeLink) {
                    itemNotFound = true;
                }
                //if item not found, skip this link
                if (itemNotFound) {
                    continue;
                }

            Button linkButton = new Button();
            linkButton.setText(link.getText());
            linkButton.setPrefWidth(Region.USE_COMPUTED_SIZE);
            linkButton.setId("linkButton");
            linkButton.setOnAction(event -> {
                for(Action action : link.getActions()) {
                    try {
                        action.execute(Game.getInstance().getPlayer());
                    } catch (Exception e) {
                        UserInformer.errorWarning("You can't do that action",e.getMessage());
                        throw new RuntimeException(e);
                    }
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
        return pathsWindowCenterBoxVBox;}
        catch (Exception e){
            System.out.println("Error in writePassage");
            return null;
        }
    }

    private static void updateBottomBox() {
        pathsWindowBottomBox.getChildren().clear();
        pathsWindowBottomBoxHBox.getChildren().clear();
        pathsWindowBottomBoxHBox.setSpacing(5);
        pathsWindowBottomBoxHBox.setAlignment(Pos.CENTER_LEFT);
        pathsWindowBottomBoxHBox.setPadding(new javafx.geometry.Insets(0, 0, 0, 20));

        pathsWindowBottomBoxHBoxTextScore = new Text(Integer.toString(Game.getInstance().getPlayer().getScore()));
        pathsWindowBottomBoxHBoxTextHeart = new Text(Integer.toString(Game.getInstance().getPlayer().getHealth()));
        pathsWindowBottomBoxHBoxTextCoin = new Text(Integer.toString(Game.getInstance().getPlayer().getGold()));

        pathsWindowBottomBoxHBoxTextScore.setId("scoreText");
        pathsWindowBottomBoxHBoxTextHeart.setId("heartText");
        pathsWindowBottomBoxHBoxTextCoin.setId("coinText");
        pathsWindowBottomBoxHBox.setFillHeight(true);

        HBox scoreBox = new HBox();
        scoreBox.setSpacing(10);
        scoreBox.setAlignment(Pos.CENTER_LEFT);
        scoreBox.setMinWidth(175);
        scoreBox.setMaxWidth(175);
        scoreBox.getChildren().addAll(pathsWindowBottomBoxHBoxImageViewScore, pathsWindowBottomBoxHBoxTextScore);

        HBox heartBox = new HBox();
        heartBox.setSpacing(10);
        heartBox.setAlignment(Pos.CENTER_LEFT);
        heartBox.setMinWidth(175);
        heartBox.setMaxWidth(175);
        heartBox.getChildren().addAll(pathsWindowBottomBoxHBoxImageViewHeart, pathsWindowBottomBoxHBoxTextHeart);

        HBox coinBox = new HBox();
        coinBox.setSpacing(10);
        coinBox.setAlignment(Pos.CENTER_LEFT);
        coinBox.setMinWidth(175);
        coinBox.setMaxWidth(175);
        coinBox.getChildren().addAll(pathsWindowBottomBoxHBoxImageViewCoin, pathsWindowBottomBoxHBoxTextCoin);

        pathsWindowBottomBoxHBox.getChildren().addAll(
                scoreBox,
                heartBox,
                coinBox);

        StackPane chestPane = new StackPane();
        chestPane.setId("chestPane");
        chestPane.setMinWidth(75);
        chestPane.setMinHeight(75);
        StackPane.setAlignment(pathsWindowBottomBoxHBoxImageViewChest, Pos.CENTER);
        chestPane.getChildren().add(pathsWindowBottomBoxHBoxImageViewChest);
        chestPane.setPadding(new javafx.geometry.Insets(0, 15, 0, 15));

        StackPane helpPane = new StackPane();
        helpPane.setId("helpPane");
        helpPane.setMinWidth(75);
        helpPane.setMinHeight(75);
        StackPane.setAlignment(pathsWindowBottomBoxHBox2ImageViewHelp, Pos.CENTER);
        helpPane.getChildren().add(pathsWindowBottomBoxHBox2ImageViewHelp);
        helpPane.setPadding(new javafx.geometry.Insets(0, 15, 0, 15));

        StackPane settingsPane = new StackPane();
        settingsPane.setId("settingsPane");
        settingsPane.setMinWidth(75);
        settingsPane.setMinHeight(75);
        StackPane.setAlignment(pathsWindowBottomBoxHBox2ImageViewSettings, Pos.CENTER);
        settingsPane.getChildren().add(pathsWindowBottomBoxHBox2ImageViewSettings);
        settingsPane.setPadding(new javafx.geometry.Insets(0, 15, 0, 15));

        pathsWindowBottomBoxHBox2.getChildren().clear();
        pathsWindowBottomBoxHBox2.setSpacing(0);
        pathsWindowBottomBoxHBox2.setAlignment(Pos.CENTER_RIGHT);
        pathsWindowBottomBoxHBox2.getChildren().addAll(
            new Separator(Orientation.VERTICAL),
            chestPane,
            new Separator(Orientation.VERTICAL),
            helpPane,
            new Separator(Orientation.VERTICAL),
            settingsPane);

        pathsWindowBottomBox.setLeft(pathsWindowBottomBoxHBox);
        pathsWindowBottomBox.setRight(pathsWindowBottomBoxHBox2);


        chestPane.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> showInventory());
        chestPane.addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
            inventoryStage.close();
            mainWindowDimmer.setVisible(false);
        });

        helpPane.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> showHelp());

        settingsPane.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> showSettings());

    }

    public static void showHelp(){

    }

    public static void showInventory(){
        inventoryStage = new Stage(StageStyle.UNDECORATED);
        BorderPane inventoryRoot = new BorderPane();
        inventoryRoot.setPadding(new Insets(30, 30, 30, 30));
        inventoryRoot.setId("inventoryRoot");
        Text tooltip = new Text();
        tooltip.setId("tooltip");
        StringBuilder inventoryString = new StringBuilder("Inventory:\r\n\n");
        for(String item : game.getPlayer().getInventory()){
            inventoryString
                .append("- ")
                .append(item.substring(0, 1).toUpperCase())
                .append(item.substring(1).toLowerCase())
                .append("\r\n");
        }
        tooltip.setText(inventoryString.toString());
        inventoryRoot.setTop(tooltip);
        Scene tooltipScene = new Scene(inventoryRoot,pathsWindowCenterBox.getWidth()/2,pathsWindowCenterBox.getHeight()/5*4);
        tooltipScene.getStylesheets().add(currentStylesheet);
        inventoryStage.setScene(tooltipScene);
        mainWindowDimmer.setVisible(true);
        inventoryStage.show();
    }

}
*/
