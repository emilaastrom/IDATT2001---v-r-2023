package edu.ntnu.idatt2001.view;

import edu.ntnu.idatt2001.Main;
import edu.ntnu.idatt2001.controller.BackgroundController;
import edu.ntnu.idatt2001.controller.PathsController;
import edu.ntnu.idatt2001.controller.UserInformer;
import edu.ntnu.idatt2001.model.Game;
import edu.ntnu.idatt2001.model.Link;
import edu.ntnu.idatt2001.model.Passage;
import edu.ntnu.idatt2001.model.goal.GoldGoal;
import edu.ntnu.idatt2001.model.goal.HealthGoal;
import edu.ntnu.idatt2001.model.goal.ScoreGoal;
import java.util.Objects;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * Constructor for paths window view.
 */
public class PathsView {
  private Passage currentPassage;
  private VBox currentPassageVbox = new VBox();
  static Game game = Game.getInstance();
  private final BorderPane pathsWindowCenterBox = new BorderPane();
  private final BorderPane pathsWindowBottomBox = new BorderPane();
  private final HBox pathsWindowBottomBoxHbox = new HBox();
  private final HBox pathsWindowBottomBoxHbox2 = new HBox();
  private final ImageView pathsWindowBottomBoxHboxImageViewScore = new ImageView(
      "file:src/main/resources/icons/score.png");
  private final ImageView pathsWindowBottomBoxHboxImageViewHeart = new ImageView(
      "file:src/main/resources/icons/heart.png");
  private final ImageView pathsWindowBottomBoxHboxImageViewCoin = new ImageView(
      "file:src/main/resources/icons/coin.png");
  private final ImageView pathsWindowBottomBoxHboxImageViewChest = new ImageView(
      "file:src/main/resources/icons/chest.png");
  private final ImageView pathsWindowBottomBoxHbox2ImageViewUndo = new ImageView(
      "file:src/main/resources/icons/undo.png");
  private final ImageView pathsWindowBottomBoxHbox2ImageViewSettings = new ImageView(
      "file:src/main/resources/icons/settings.png");
  private final BorderPane pathsWindow = new BorderPane();
  private final VBox pathsWindowVbox = new VBox();
  private final PathsController controller;
  private BorderPane pathsDimmer;
  StackPane pathsRoot = new StackPane();
  VBox pathsWindowCenterBoxVbox = new VBox();
  Text titleText = new Text();
  Text contentText = new Text();

  /**
   * Constructor for PathsView.

   * @param controller the controller for the paths window.
   */
  public PathsView(PathsController controller) {
    this.controller = controller;

    createAndConfigurePane();

    createAndLayoutControls();

    updateControllerFromListeners();

    observeModelAndUpdateControls();

    pathsWindowCenterBox.setCenter(currentPassageVbox);

  }

  private void createAndConfigurePane() {
    pathsRoot.setBackground(BackgroundController.getCurrentBackground());
    currentPassage = controller.getStory().getOpeningPassage();


    pathsWindowCenterBox.setPrefWidth(500);
    pathsWindowCenterBox.setPrefHeight(400);
    pathsWindowCenterBox.setStyle(
        "-fx-background-color: rgb(0,0,0,0.5);"
            + "-fx-border-color: rgb(255,255,255);"
            + "-fx-border-width: 5px;"
    );

    pathsWindowBottomBox.setPrefWidth(500);
    pathsWindowBottomBox.setPrefHeight(100);
    pathsWindowBottomBox.setStyle(
        "-fx-background-color: rgb(0,0,0,0.5);"
            + "-fx-border-color: rgb(255,255,255);"
            + "-fx-border-width: 5px;"
    );

    updateBottomBox();

    pathsDimmer = new BorderPane();
    pathsDimmer.setVisible(false);
    pathsDimmer.setMouseTransparent(true);
    pathsDimmer.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5);");
    pathsDimmer.setId("mainWindowDimmer");
    pathsWindowVbox.setSpacing(30);
    pathsWindowVbox.getChildren().addAll(pathsWindowCenterBox, pathsWindowBottomBox);
    pathsWindowVbox.setAlignment(javafx.geometry.Pos.CENTER);

    currentPassageVbox = showPassages(currentPassage);
    pathsWindowCenterBox.setCenter(currentPassageVbox);

    pathsWindow.setMinWidth(0);
    pathsWindow.setMinHeight(0);
    pathsWindow.setCenter(pathsWindowVbox);
    pathsWindow.setPadding(new javafx.geometry.Insets(50, 200, 50, 200));
    pathsWindow.setStyle("-fx-background-color: rgb(0, 0, 0, 0)");



    pathsRoot.getChildren().addAll(pathsWindow, pathsDimmer);

  }

  private void createAndLayoutControls() {

  }

  private void updateControllerFromListeners() {

  }

  private void observeModelAndUpdateControls() {

  }

  /**
   * Method for getting the root of the paths window.
   */
  public StackPane getRoot() {
    return pathsRoot;
  }

  /**
   * Method for updating the bottom box of the paths window.
   */
  public void updateBottomBox() {
    pathsWindowBottomBox.getChildren().clear();
    pathsWindowBottomBoxHbox.getChildren().clear();
    pathsWindowBottomBoxHbox.setSpacing(5);
    pathsWindowBottomBoxHbox.setAlignment(Pos.CENTER_LEFT);
    pathsWindowBottomBoxHbox.setPadding(new Insets(0, 0, 0, 20));

    Text pathsWindowBottomBoxHboxTextScore = new Text(
            Integer.toString(Game.getInstance().getPlayer().getScore()));
    Text pathsWindowBottomBoxHboxTextHeart = new Text(
            Integer.toString(Game.getInstance().getPlayer().getHealth()));
    Text pathsWindowBottomBoxHboxTextCoin = new Text(
            Integer.toString(Game.getInstance().getPlayer().getGold()));

    pathsWindowBottomBoxHboxTextScore.setId("scoreText");
    pathsWindowBottomBoxHboxTextHeart.setId("heartText");
    pathsWindowBottomBoxHboxTextCoin.setId("coinText");
    pathsWindowBottomBoxHbox.setFillHeight(true);

    HBox scoreBox = new HBox();
    scoreBox.setSpacing(10);
    scoreBox.setAlignment(Pos.CENTER_LEFT);
    scoreBox.setMinWidth(175);
    scoreBox.setMaxWidth(175);
    scoreBox.getChildren().addAll(
        pathsWindowBottomBoxHboxImageViewScore, pathsWindowBottomBoxHboxTextScore);

    HBox heartBox = new HBox();
    heartBox.setSpacing(10);
    heartBox.setAlignment(Pos.CENTER_LEFT);
    heartBox.setMinWidth(175);
    heartBox.setMaxWidth(175);
    heartBox.getChildren().addAll(
        pathsWindowBottomBoxHboxImageViewHeart, pathsWindowBottomBoxHboxTextHeart);

    HBox coinBox = new HBox();
    coinBox.setSpacing(10);
    coinBox.setAlignment(Pos.CENTER_LEFT);
    coinBox.setMinWidth(175);
    coinBox.setMaxWidth(175);
    coinBox.getChildren().addAll(
        pathsWindowBottomBoxHboxImageViewCoin, pathsWindowBottomBoxHboxTextCoin);

    pathsWindowBottomBoxHbox.getChildren().addAll(
        scoreBox,
        heartBox,
        coinBox);

    StackPane chestPane = new StackPane();
    chestPane.setId("chestPane");
    chestPane.setMinWidth(75);
    chestPane.setMinHeight(75);
    StackPane.setAlignment(pathsWindowBottomBoxHboxImageViewChest, Pos.CENTER);
    chestPane.getChildren().add(pathsWindowBottomBoxHboxImageViewChest);
    chestPane.setPadding(new Insets(0, 15, 0, 15));

    StackPane undoPane = new StackPane();
    undoPane.setId("undoPane");
    undoPane.setMinWidth(75);
    undoPane.setMinHeight(75);
    StackPane.setAlignment(pathsWindowBottomBoxHbox2ImageViewUndo, Pos.CENTER);
    undoPane.getChildren().add(pathsWindowBottomBoxHbox2ImageViewUndo);
    undoPane.setPadding(new Insets(0, 15, 0, 15));
    Tooltip undoTooltip = new Tooltip("Undo");
    Tooltip.install(undoPane, undoTooltip);

    StackPane settingsPane = new StackPane();
    settingsPane.setId("settingsPane");
    settingsPane.setMinWidth(75);
    settingsPane.setMinHeight(75);
    StackPane.setAlignment(pathsWindowBottomBoxHbox2ImageViewSettings, Pos.CENTER);
    settingsPane.getChildren().add(pathsWindowBottomBoxHbox2ImageViewSettings);
    settingsPane.setPadding(new Insets(0, 15, 0, 15));
    Tooltip settingsTooltip = new Tooltip("Settings");
    Tooltip.install(settingsPane, settingsTooltip);

    pathsWindowBottomBoxHbox2.getChildren().clear();
    pathsWindowBottomBoxHbox2.setSpacing(0);
    pathsWindowBottomBoxHbox2.setAlignment(Pos.CENTER_RIGHT);
    pathsWindowBottomBoxHbox2.getChildren().addAll(
        new Separator(Orientation.VERTICAL),
        chestPane,
        new Separator(Orientation.VERTICAL),
        undoPane,
        new Separator(Orientation.VERTICAL),
        settingsPane);

    pathsWindowBottomBox.setLeft(pathsWindowBottomBoxHbox);
    pathsWindowBottomBox.setRight(pathsWindowBottomBoxHbox2);


    chestPane.addEventHandler(MouseEvent.MOUSE_ENTERED, event ->
        controller.showInventory(pathsWindowCenterBox.getWidth() / 2,
            pathsWindowCenterBox.getHeight() / 2, pathsDimmer));
    chestPane.addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
      controller.hideInventory(pathsDimmer);
    });

    undoPane.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> undoMove());

    settingsPane.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
      controller.showSettings();
      pathsDimmer.setVisible(true);
    });
  }

  /**
   * Method for showing the passages.
   */
  public VBox showPassages(Passage passage) {
    try {
      pathsWindowCenterBoxVbox.setSpacing(40);
      pathsWindowCenterBoxVbox.setAlignment(Pos.CENTER);

      titleText.setText(Objects.requireNonNull(passage.getTitle()));
      titleText.setId("titleText");

      contentText.setText(Objects.requireNonNull(passage.getContent()));
      contentText.setId("contentText");
      contentText.setWrappingWidth(800);
      contentText.setTextAlignment(TextAlignment.CENTER);

      pathsWindowCenterBoxVbox.getChildren().clear();
      pathsWindowCenterBoxVbox.getChildren().addAll(titleText, contentText);

      VBox buttonsVbox = new VBox();
      buttonsVbox.setSpacing(10);
      buttonsVbox.setAlignment(Pos.CENTER);
      if (controller.getCurrentPassageLinks(passage).isEmpty()) {
        Button statsButton = new Button("View stats");
        statsButton.setId("endButton");
        buttonsVbox.getChildren().add(statsButton);
        statsButton.setOnAction(event -> showStats());

      } else {
        controller.getCurrentPassageLinks(passage).forEach(
            link -> buttonsVbox.getChildren().add(newButton(link)));
      }
      pathsWindowCenterBoxVbox.getChildren().add(buttonsVbox);
      return pathsWindowCenterBoxVbox;
    } catch (Exception e) {
      System.out.println("Error in writePassage");
      return null;
    }
  }

  /**
   * Method showing the stats of the player.
   */
  public void showStats() {
    VBox contentVboxText = new VBox();
    contentVboxText.setAlignment(Pos.CENTER);
    pathsWindowCenterBoxVbox.setSpacing(10);
    titleText.setText("Game Over\n");
    if (Game.getInstance().getGoals().isEmpty()) {
      contentText.setText(
          Game.getInstance().getPlayer().getName() + " reached the end of the game!");
      contentVboxText.getChildren().add(contentText);
    } else {
      contentText.setText(Game.getInstance().getPlayer().getName() + "'s stats/goals:");
      contentVboxText.getChildren().add(contentText);
      Game.getInstance().getGoals().stream()
              .filter(goal -> goal instanceof ScoreGoal || goal instanceof GoldGoal || goal instanceof HealthGoal)
              .forEach(goal -> {
                Text goalText;
                if (goal instanceof ScoreGoal) {
                  goalText = new Text("Score: " + Game.getInstance().getPlayer().getScore() + "/" + ((ScoreGoal) goal).getScoreGoal());
                } else if (goal instanceof GoldGoal) {
                  goalText = new Text("Gold: " + Game.getInstance().getPlayer().getGold() + "/" + ((GoldGoal) goal).getGoldGoal());
                } else {
                  goalText = new Text("Health: " + Game.getInstance().getPlayer().getHealth() + "/" + ((HealthGoal) goal).getHealthGoal());
                }

                if (goal.isFulfilled(Game.getInstance().getPlayer())) {
                  goalText.setFill(Color.GREEN);
                } else {
                  goalText.setFill(Color.RED);
                }

                goalText.setId("goalText");
                goalText.setTextAlignment(TextAlignment.CENTER);
                contentVboxText.getChildren().add(goalText);
              });
    }

    Button restartButton = new Button("Restart");
    restartButton.setId("endButton");
    restartButton.setOnAction(event -> {
      try {
        controller.restartGame();
        setCurrentPassageVbox(showPassages(Game.getInstance().begin()));
        updateBottomBox();
        Main.updateStage();
      } catch (Exception e) {
        e.printStackTrace();
      }
    });

    Button exitButton = new Button("Main Menu");
    exitButton.setId("endButton");
    exitButton.setOnAction(event -> {
      try {
        Main.showExitConfirmation(this);
      } catch (Exception e) {
        e.printStackTrace();
      }
    });

    pathsWindowCenterBoxVbox.getChildren().clear();
    pathsWindowCenterBoxVbox.getChildren().addAll(
        titleText, contentVboxText, restartButton, exitButton);
  }

  /**
   * Method for creating a button for a link.
   */
  private Button newButton(Link link) {
    Button linkButton = new Button();
    linkButton.setText(link.getText());
    linkButton.setPrefWidth(Region.USE_COMPUTED_SIZE);
    //Checking if the link is broken, if so it will be red.
    if (Game.getInstance().getStory().getBrokenLinks().contains(link)) {
      linkButton.setId("brokenLinkButton");
    } else {
      linkButton.setId("linkButton");
      linkButton.setOnAction(event -> {
        link.getActions().forEach(action -> {
          try {
            action.execute(Game.getInstance().getPlayer());
          } catch (Exception e) {
            UserInformer.errorWarning("You can't do that action", e.getMessage());
          }
        });
        updateBottomBox();
        currentPassage = game.go(link);
        currentPassageVbox = showPassages(currentPassage);
        pathsWindowCenterBox.setCenter(currentPassageVbox);
        Main.updateStage();
      });
    }
    linkButton.setWrapText(true);
    return linkButton;
  }

  /**
   * Method undoing the last move.
   */
  public void undoMove() {
    try {
      //goSilent() not record the move, and goBack() to return to the previous passage
      currentPassage = game.goSilent(game.goBack());
      currentPassageVbox = showPassages(currentPassage);
      pathsWindowCenterBox.setCenter(currentPassageVbox);
      updateBottomBox();
      Main.updateStage();
    } catch (Exception e) {
      UserInformer.errorWarning("Error", "Make a move first!");
    }
  }

  /**
   * Method for setting the current passage in the view.
   */
  public void setCurrentPassageVbox(VBox currentPassageVbox) {
    this.currentPassageVbox = currentPassageVbox;
    pathsWindowCenterBox.setCenter(currentPassageVbox);
  }

  /**
   * Getter for the pathsView dimmer to be enabled/disabled by different stages.
   */
  public BorderPane getPathsDimmer() {
    return pathsDimmer;
  }

}
