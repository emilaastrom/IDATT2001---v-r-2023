package edu.ntnu.idatt2001.View;

import edu.ntnu.idatt2001.Controller.GameSelectionController;
import edu.ntnu.idatt2001.Model.Game;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
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


        Button gameSelectionButton = new Button("Custom");
            gameSelectionButton.setMaxWidth(150);
        Tooltip gameSelectionButtonToolTip = new Tooltip("Choose a .paths file from your computer");
            gameSelectionButtonToolTip.setShowDelay(Duration.millis(50));
            gameSelectionButton.setTooltip(gameSelectionButtonToolTip);

        Button gameSelectionButton2 = new Button("Example");
            gameSelectionButton2.setMaxWidth(150);
        Tooltip gameSelectionButton2ToolTip = new Tooltip("Choose the provided example game \n(workaround for macOS v13+ issue with file chooser)");
            gameSelectionButton2ToolTip.setShowDelay(Duration.millis(50));
            gameSelectionButton2ToolTip.setWrapText(true);
            gameSelectionButton2.setTooltip(gameSelectionButton2ToolTip);
        AtomicReference<Boolean> exampleGameChosen = new AtomicReference<>();

        HBox gameSelectionButtonsBox = new HBox();
            gameSelectionButtonsBox.setAlignment(Pos.CENTER);
            gameSelectionButtonsBox.setSpacing(10);
            gameSelectionButtonsBox.getChildren().addAll(gameSelectionButton, gameSelectionButton2);

        TextField nameField = new TextField();
        //Tooltip that shows when hovering over the nameField, explaining field use and max length
        Tooltip nameFieldToolTip = new Tooltip("Main character name - max 20 characters");
            nameFieldToolTip.setShowDelay(Duration.millis(50));
            nameField.setTooltip(nameFieldToolTip);
            nameField.setTooltip(nameFieldToolTip);
            nameField.setId("textField");
            nameField.setPromptText("CHARACTER NAME");
            nameField.setMaxWidth(250);
            nameField.setAlignment(Pos.CENTER);
        //Limiting the input field to 20 characters
            nameField.addEventHandler(KeyEvent.KEY_TYPED, event -> {
            if (nameField.getText().length() >= 20) event.consume();
        });


        Separator gameSelectionButtonsSeparator = new Separator(Orientation.HORIZONTAL);
            gameSelectionButtonsSeparator.setMaxWidth(600);


        Button loadGameButton = new Button("Load game");
            loadGameButton.setId("LoadGameButton");
            loadGameButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                if (exampleGameChosen.get().equals(false)){
                    controller.chooseGameFile(stage);
                } else {
                    controller.loadExampleFile(stage, String.valueOf(pathToGameFile));
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
                nameField);
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
            gameSelectionButton.setOnAction(event -> {
                exampleGameChosen.set(false);
                File selectedFile = fileChooser.showOpenDialog(stage);
    //            myStory[0] = FileHandler.readFile(selectedFile.getAbsolutePath());
                if (chosenPathsFile.get() != null){
                    currentPathsFile.setText("Current file: " + chosenPathsFile.get().getAbsolutePath() + "\nTitle: " + game.getStory().getTitle());
                }
        });

            gameSelectionButton2.setOnAction(event -> {
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
