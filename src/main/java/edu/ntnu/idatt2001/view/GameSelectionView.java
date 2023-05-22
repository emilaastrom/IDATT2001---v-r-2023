package edu.ntnu.idatt2001.view;

import edu.ntnu.idatt2001.controller.GameSelectionController;
import edu.ntnu.idatt2001.model.Game;
import edu.ntnu.idatt2001.model.goal.Goal;
import edu.ntnu.idatt2001.model.goal.GoldGoal;
import edu.ntnu.idatt2001.model.goal.HealthGoal;
import edu.ntnu.idatt2001.model.goal.ScoreGoal;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
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


/**
 * The type Game selection view.
 */
public class GameSelectionView {
  private final BorderPane root;
  private final Game game;
  private final GameSelectionController controller;
  private Stage stage;

  /**
   * Instantiates a new Game selection view.
   *
   * @param controller the controller
   */
  public GameSelectionView(GameSelectionController controller) {
    this.controller = controller;
    //Creating a new root that will replace the main menu
    root = new BorderPane();
    root.setId("GameSelectionRoot");
    game = Game.getInstance();
    createAndConfigurePane();
  }

  /**
   * Gets root.
   *
   * @return the root
   */
  public BorderPane getRoot() {
    return root;
  }

  private void createAndConfigurePane() {
    //Configuring the GameSelectionView elements of the root
    VBox gameSelectionTitleBox = new VBox();
    Text gameSelectionTitle = new Text("Choose a .paths game!");
    gameSelectionTitle.setId("DefaultText");
    gameSelectionTitleBox.getChildren().addAll(gameSelectionTitle);
    gameSelectionTitleBox.setAlignment(Pos.CENTER);
    gameSelectionTitleBox.setPadding(new Insets(30, 10, 10, 10));
    root.setTop(gameSelectionTitleBox);

    VBox gameSelectionBox = new VBox();
    gameSelectionBox.setSpacing(10);
    gameSelectionBox.setAlignment(Pos.CENTER);
    Text currentPathsFile = new Text("Current file: ");
    currentPathsFile.setTextAlignment(TextAlignment.CENTER);
    currentPathsFile.setId("DefaultText");
    currentPathsFile.setWrappingWidth(700);


    Button customGameButton = new Button("Custom");
    customGameButton.setMaxWidth(150);
    Tooltip gameSelectionButtonToolTip = new Tooltip("Choose a .paths file from your computer");
    gameSelectionButtonToolTip.setShowDelay(Duration.millis(50));
    customGameButton.setTooltip(gameSelectionButtonToolTip);

    Button exampleGameButton = new Button("Example");
    exampleGameButton.setMaxWidth(150);
    Tooltip gameSelectionButton2ToolTip = new Tooltip("Choose the provided example game "
            + "\n(workaround for macOS v13+ issue with file chooser)");
    gameSelectionButton2ToolTip.setShowDelay(Duration.millis(50));
    gameSelectionButton2ToolTip.setWrapText(true);
    exampleGameButton.setTooltip(gameSelectionButton2ToolTip);


    HBox gameSelectionButtonsBox = new HBox();
    gameSelectionButtonsBox.setAlignment(Pos.CENTER);
    gameSelectionButtonsBox.setSpacing(10);
    gameSelectionButtonsBox.getChildren().addAll(customGameButton, exampleGameButton);

    //Creating a field to input player name and adding custom tooltip
    TextField nameField = new TextField();
    Tooltip nameFieldToolTip = new Tooltip("Main character name - max 20 characters");
    nameFieldToolTip.setShowDelay(Duration.millis(50));
    nameField.setTooltip(nameFieldToolTip);
    nameField.setId("textField");
    nameField.setPromptText("CHARACTER NAME");
    nameField.setMaxWidth(250);
    nameField.setAlignment(Pos.CENTER);
    //Limiting the input field for player name to be 20 characters
    nameField.addEventHandler(KeyEvent.KEY_TYPED, event -> {
      if (nameField.getText().length() >= 20) {
        event.consume();
      }
    });

    //Creating boxes for enabling score goals, health goals and gold goals
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
    //Ensuring that only numbers can be inputted in the score goal field
    scoreGoalField.addEventHandler(KeyEvent.KEY_TYPED, event -> {
      if (!event.getCharacter().matches("[0-9]")) {
        event.consume();
      }
    });

    //Enabling and disabling the score goal field based on the checkbox
    enableScoreGoal.addEventHandler(MouseEvent.MOUSE_CLICKED, event ->
            scoreGoalField.setDisable(!enableScoreGoal.isSelected()));

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
    //Ensuring that only numbers can be inputted in the score goal field
    healthGoalField.addEventHandler(KeyEvent.KEY_TYPED, event -> {
      if (!event.getCharacter().matches("[0-9]")) {
        event.consume();
      }
    });

    //Enabling and disabling the health goal field based on the checkbox
    enableHealthGoal.addEventHandler(MouseEvent.MOUSE_CLICKED, event ->
            healthGoalField.setDisable(!enableHealthGoal.isSelected()));
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
    //Ensuring that only numbers can be inputted in the score goal field
    goldGoalField.addEventHandler(KeyEvent.KEY_TYPED, event -> {
      if (!event.getCharacter().matches("[0-9]")) {
        event.consume();
      }
    });

    goldGoalBox.getChildren().addAll(enableGoldGoal, goldGoalField);


    //Enabling and disabling the gold goal field based on the checkbox
    enableGoldGoal.addEventHandler(MouseEvent.MOUSE_CLICKED, event ->
            goldGoalField.setDisable(!enableGoldGoal.isSelected()));

    VBox goalBox = new VBox();
    goalBox.setAlignment(Pos.CENTER);
    goalBox.setSpacing(5);
    goalBox.getChildren().addAll(scoreGoalBox, healthGoalBox, goldGoalBox);

    Separator gameSelectionButtonsSeparator = new Separator(Orientation.HORIZONTAL);
    gameSelectionButtonsSeparator.setMaxWidth(600);


    AtomicReference<String> pathToGameFile = new AtomicReference<>("");
    AtomicReference<Boolean> exampleGameChosen = new AtomicReference<>();
    Button loadGameButton = new Button("Load game");
    loadGameButton.setId("LoadGameButton");
    loadGameButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
      List<Goal> goalList = new ArrayList<>();
      //Checking if the goals are both enabled and not empty before adding them
      if (enableGoldGoal.isSelected() && goldGoalField.getText().length() > 0) {
        goalList.add(new GoldGoal(Integer.parseInt(goldGoalField.getText())));
      }
      if (enableHealthGoal.isSelected() && healthGoalField.getText().length() > 0) {
        goalList.add(new HealthGoal(Integer.parseInt(healthGoalField.getText())));
      }
      if (enableScoreGoal.isSelected() && scoreGoalField.getText().length() > 0) {
        goalList.add(new ScoreGoal(Integer.parseInt(scoreGoalField.getText())));
      }
      //Loading either SELECTED GAME FILE or EXAMPLE GAME FILE
      if (exampleGameChosen.get().equals(false)) {
        controller.chooseGameFile(String.valueOf(pathToGameFile), nameField.getText(), goalList);
      } else {
        controller.loadExampleFile(
                String.valueOf(pathToGameFile),
                nameField.getText(),
                goalList);
      }
    });

    VBox loadGameBox = new VBox();
    loadGameBox.setAlignment(Pos.CENTER);
    loadGameBox.setPadding(new Insets(10, 10, 30, 10));
    loadGameBox.getChildren().add(loadGameButton);

    gameSelectionBox.setSpacing(50);
    gameSelectionBox.getChildren().addAll(
            currentPathsFile,
            gameSelectionButtonsBox,
            gameSelectionButtonsSeparator,
            nameField,
            goalBox
    );

    root.setBottom(loadGameBox);
    root.setCenter(gameSelectionBox);

    //Using FileChooser to add a custom game file
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Choose a .paths file");
    fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Paths file", "*.paths")
    );
    fileChooser.setInitialDirectory(new File("src/main/resources"));

    customGameButton.setOnAction(event -> {
      exampleGameChosen.set(false);
      File selectedFile = fileChooser.showOpenDialog(stage);
      if (selectedFile != null) {
        currentPathsFile.setText("Current file: " + selectedFile.getAbsolutePath());
        pathToGameFile.set(selectedFile.getAbsolutePath());
      }
    });

    //Loading pre-made example game
    exampleGameButton.setOnAction(event -> {
      currentPathsFile.setText("Current file: " + "\nsrc/main/resources/exampleStory.paths");
      pathToGameFile.set("src/main/resources/exampleStory.paths");
      exampleGameChosen.set(true);
    });
  }
}
