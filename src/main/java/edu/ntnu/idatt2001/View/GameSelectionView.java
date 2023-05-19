package edu.ntnu.idatt2001.View;

import edu.ntnu.idatt2001.Controller.GameSelectionController;
import edu.ntnu.idatt2001.Model.Game;
import edu.ntnu.idatt2001.Model.Goal.Goal;
import edu.ntnu.idatt2001.Model.Goal.GoldGoal;
import edu.ntnu.idatt2001.Model.Goal.HealthGoal;
import edu.ntnu.idatt2001.Model.Goal.ScoreGoal;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

import static edu.ntnu.idatt2001.Main.currentStylesheet;

public class GameSelectionView {
    BorderPane root;
    Game game;
    GameSelectionController controller;
    Stage stage;
    public GameSelectionView(GameSelectionController controller, Stage stage) {
        root = new BorderPane();
        root.setId("GameSelectionRoot");
        game = Game.getInstance();
        this.controller = controller;
        this.stage = stage;
        createAndConfigurePane();
        createAndLayoutControls();
        updateControllerFromListeners();
        observeModelAndUpdateControls();
    }

    public BorderPane getRoot() {
        return root;
    }

    private void createAndConfigurePane() {

        VBox gameSelectionTitleBox = new VBox();
        AtomicReference<String> pathToGameFile = new AtomicReference<>("");
        Text gameSelectionTitle = new Text("Choose a .paths game!");
            gameSelectionTitle.setId("DefaultText");
            gameSelectionTitleBox.getChildren().addAll(gameSelectionTitle);
            gameSelectionTitleBox.setAlignment(Pos.CENTER);
            gameSelectionTitleBox.setPadding(new Insets(30, 10, 10, 10));
            root.setTop(gameSelectionTitleBox);

        VBox gameSelectionBox = new VBox();
            gameSelectionBox.setSpacing(10);
            gameSelectionBox.setAlignment(Pos.CENTER);
        AtomicReference<File> chosenPathsFile = new AtomicReference<>(null);
        Text currentPathsFile = new Text("Current file: ");
            currentPathsFile.setTextAlignment(TextAlignment.CENTER);
            currentPathsFile.setId("DefaultText");


        Button customGameButton = new Button("Custom");
            customGameButton.setMaxWidth(150);
        Tooltip gameSelectionButtonToolTip = new Tooltip("Choose a .paths file from your computer");
            gameSelectionButtonToolTip.setShowDelay(Duration.millis(50));
            customGameButton.setTooltip(gameSelectionButtonToolTip);

        Button exampleGameButton = new Button("Example");
            exampleGameButton.setMaxWidth(150);
        Tooltip gameSelectionButton2ToolTip = new Tooltip("Choose the provided example game \n(workaround for macOS v13+ issue with file chooser)");
            gameSelectionButton2ToolTip.setShowDelay(Duration.millis(50));
            gameSelectionButton2ToolTip.setWrapText(true);
            exampleGameButton.setTooltip(gameSelectionButton2ToolTip);
        AtomicReference<Boolean> exampleGameChosen = new AtomicReference<>();

        HBox gameSelectionButtonsBox = new HBox();
            gameSelectionButtonsBox.setAlignment(Pos.CENTER);
            gameSelectionButtonsBox.setSpacing(10);
            gameSelectionButtonsBox.getChildren().addAll(customGameButton, exampleGameButton);

        TextField nameField = new TextField();
        //Tooltip that shows when hovering over the nameField, explaining field use and max length
        Tooltip nameFieldToolTip = new Tooltip("Main character name - max 20 characters");
            nameFieldToolTip.setShowDelay(Duration.millis(50));
            nameField.setTooltip(nameFieldToolTip);
            nameField.setId("textField");
            nameField.setPromptText("CHARACTER NAME");
            nameField.setMaxWidth(250);
            nameField.setAlignment(Pos.CENTER);
        //Limiting the input field to 20 characters
            nameField.addEventHandler(KeyEvent.KEY_TYPED, event -> {
            if (nameField.getText().length() >= 20) event.consume();
        });

        HBox scoreGoalBox = new HBox();
            scoreGoalBox.setAlignment(Pos.CENTER);
            scoreGoalBox.setSpacing(10);
        CheckBox enableScoreGoal = new CheckBox();
        enableScoreGoal.setSelected(false);
        enableScoreGoal.setPadding(new Insets(0, 0, 0, 10));
        enableScoreGoal.setTooltip(new Tooltip("Enable or disable the score goal"));
        Tooltip enableScoreGoalToolTip = new Tooltip("Enable or disable the score goal");
        enableScoreGoalToolTip.setShowDelay(Duration.millis(50));
        enableScoreGoal.setTooltip(enableScoreGoalToolTip);

        TextField scoreGoalField = new TextField();
        scoreGoalField.setPromptText("SCORE GOAL");
        scoreGoalField.setMaxWidth(250);
        scoreGoalField.setAlignment(Pos.CENTER);
        scoreGoalField.setDisable(true);
        scoreGoalField.addEventHandler(KeyEvent.KEY_TYPED, event -> {
            if (!event.getCharacter().matches("[0-9]")) event.consume();
        });

        enableScoreGoal.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            scoreGoalField.setDisable(!enableScoreGoal.isSelected());
        });

        scoreGoalBox.getChildren().addAll(enableScoreGoal, scoreGoalField);

        HBox healthGoalBox = new HBox();
        healthGoalBox.setAlignment(Pos.CENTER);
        healthGoalBox.setSpacing(10);
        CheckBox enableHealthGoal = new CheckBox();
        enableHealthGoal.setSelected(false);
        enableHealthGoal.setPadding(new Insets(0, 0, 0, 10));
        enableHealthGoal.setTooltip(new Tooltip("Enable or disable the health goal"));
        Tooltip enableHealthGoalToolTip = new Tooltip("Enable or disable the health goal");
        enableHealthGoalToolTip.setShowDelay(Duration.millis(50));
        enableHealthGoal.setTooltip(enableHealthGoalToolTip);

        TextField healthGoalField = new TextField();
        healthGoalField.setPromptText("HEALTH GOAL");
        healthGoalField.setMaxWidth(250);
        healthGoalField.setAlignment(Pos.CENTER);
        healthGoalField.setDisable(true);
        healthGoalField.addEventHandler(KeyEvent.KEY_TYPED, event -> {
            if (!event.getCharacter().matches("[0-9]")) event.consume();
        });

        enableHealthGoal.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            healthGoalField.setDisable(!enableHealthGoal.isSelected());
        });
        healthGoalBox.getChildren().addAll(enableHealthGoal, healthGoalField);


        HBox goldGoalBox = new HBox();
        goldGoalBox.setAlignment(Pos.CENTER);
        goldGoalBox.setSpacing(10);
        CheckBox enableGoldGoal = new CheckBox();
        enableGoldGoal.setSelected(false);
        enableGoldGoal.setPadding(new Insets(0, 0, 0, 10));
        Tooltip enableGoldGoalToolTip = new Tooltip("Enable or disable the gold goal");
        enableGoldGoalToolTip.setShowDelay(Duration.millis(50));
        enableGoldGoal.setTooltip(enableGoldGoalToolTip);

        TextField goldGoalField = new TextField();
        goldGoalField.setPromptText("GOLD GOAL");
        goldGoalField.setMaxWidth(250);
        goldGoalField.setAlignment(Pos.CENTER);
        goldGoalField.setDisable(true);
        goldGoalField.addEventHandler(KeyEvent.KEY_TYPED, event -> {
            if (!event.getCharacter().matches("[0-9]")) event.consume();
        });

        goldGoalBox.getChildren().addAll(enableGoldGoal, goldGoalField);


        enableGoldGoal.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            goldGoalField.setDisable(!enableGoldGoal.isSelected());
        });

        VBox goalBox = new VBox();
        goalBox.setAlignment(Pos.CENTER);
        goalBox.setSpacing(5);
        goalBox.getChildren().addAll(scoreGoalBox, healthGoalBox, goldGoalBox);

        Separator gameSelectionButtonsSeparator = new Separator(Orientation.HORIZONTAL);
            gameSelectionButtonsSeparator.setMaxWidth(600);


        Button loadGameButton = new Button("Load game");
            loadGameButton.setId("LoadGameButton");
            loadGameButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                List<Goal> goalList = new ArrayList<>();
                if (enableGoldGoal.isSelected() && goldGoalField.getText().length() > 0){
                    goalList.add(new GoldGoal(Integer.parseInt(goldGoalField.getText())));
                }
                if (enableHealthGoal.isSelected() && healthGoalField.getText().length() > 0){
                    goalList.add(new HealthGoal(Integer.parseInt(healthGoalField.getText())));
                }
                if (enableScoreGoal.isSelected() && scoreGoalField.getText().length() > 0){
                    goalList.add(new ScoreGoal(Integer.parseInt(scoreGoalField.getText())));
                }
                //Loading either SELECTED GAME or EXAMPLE GAME
                if (exampleGameChosen.get().equals(false)){
                    controller.chooseGameFile(stage, nameField.getText(), goalList);
                } else {
                    controller.loadExampleFile(stage, String.valueOf(pathToGameFile), nameField.getText(), goalList);
                }
            });
        VBox loadGameBox = new VBox();
            loadGameBox.setAlignment(Pos.CENTER);
            loadGameBox.setPadding(new Insets(10, 10, 30, 10));
            loadGameBox.getChildren().add(loadGameButton);

            gameSelectionBox.getChildren().addAll(
                currentPathsFile,
                gameSelectionButtonsBox,
                gameSelectionButtonsSeparator,
                nameField,
                goalBox
                );
            root.setBottom(loadGameBox);
            gameSelectionBox.setSpacing(50);
            root.setCenter(gameSelectionBox);

        FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choose a .paths file");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Paths file", "*.paths")
            );
            fileChooser.setInitialDirectory(new File("src/main/resources"));

    //        Story[] myStory = new Story[1];
            customGameButton.setOnAction(event -> {
                exampleGameChosen.set(false);
                File selectedFile = fileChooser.showOpenDialog(stage);
    //            myStory[0] = FileHandler.readFile(selectedFile.getAbsolutePath());
                if (chosenPathsFile.get() != null){
                    currentPathsFile.setText("Current file: " + chosenPathsFile.get().getAbsolutePath() + "\nTitle: " + game.getStory().getTitle());
                }
        });

            exampleGameButton.setOnAction(event -> {
            currentPathsFile.setText("Current file: " + "\nsrc/main/resources/exampleStory.paths");
            pathToGameFile.set("src/main/resources/exampleStory.paths");
            exampleGameChosen.set(true);
        });
    }

    private void updateControllerFromListeners() {
    }

    private void observeModelAndUpdateControls() {
    }

    private void createAndLayoutControls() {
    }

}
