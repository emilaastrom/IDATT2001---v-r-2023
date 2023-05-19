package edu.ntnu.idatt2001.View;

import edu.ntnu.idatt2001.Controller.BackgroundController;
import edu.ntnu.idatt2001.Controller.PathsController;
import edu.ntnu.idatt2001.Controller.UserInformer;
import edu.ntnu.idatt2001.Main;
import edu.ntnu.idatt2001.Model.*;
import edu.ntnu.idatt2001.Model.Action.Action;
import edu.ntnu.idatt2001.Model.Goal.Goal;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.util.List;
import java.util.Objects;

public class PathsView {
  private String currentStylesheet;
  private Passage currentPassage;
  private VBox currentPassageVBox = new VBox();
  static Player player;
  static List<Goal> goals;
  static Game game = Game.getInstance();
  static Story story;
  private Stage inventoryStage;
  private BorderPane pathsWindowCenterBox = new BorderPane();
  private BorderPane pathsWindowBottomBox = new BorderPane();
  private HBox pathsWindowBottomBoxHBox = new HBox();
  private HBox pathsWindowBottomBoxHBox2 = new HBox();
  private Text pathsWindowBottomBoxHBoxTextScore = new Text();
  private Text pathsWindowBottomBoxHBoxTextHeart = new Text();
  private Text pathsWindowBottomBoxHBoxTextCoin = new Text();
  private ImageView pathsWindowBottomBoxHBoxImageViewScore = new ImageView("file:src/main/resources/score.png");
  private ImageView pathsWindowBottomBoxHBoxImageViewHeart = new ImageView("file:src/main/resources/heart.png");
  private ImageView pathsWindowBottomBoxHBoxImageViewCoin = new ImageView("file:src/main/resources/coin.png");
  private ImageView pathsWindowBottomBoxHBoxImageViewChest = new ImageView("file:src/main/resources/chest.png");
  private ImageView pathsWindowBottomBoxHBox2ImageViewHelp = new ImageView("file:src/main/resources/help.png");
  private ImageView pathsWindowBottomBoxHBox2ImageViewSettings = new ImageView("file:src/main/resources/settings.png");
  private BorderPane pathsWindow = new BorderPane();
  private VBox pathsWindowVBox = new VBox();

  private PathsController controller ;
  private BorderPane pathsDimmer;

  StackPane pathsRoot = new StackPane();


  public PathsView(PathsController controller) {
    this.controller = controller;

    createAndConfigurePane();

    createAndLayoutControls();

    updateControllerFromListeners();

    observeModelAndUpdateControls();

    pathsWindowCenterBox.setCenter(currentPassageVBox);

}

  private void createAndConfigurePane() {
    pathsRoot.setBackground(BackgroundController.getCurrentBackground());
    currentPassage = controller.getStory().getOpeningPassage();


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

    pathsDimmer = new BorderPane();
    pathsDimmer.setVisible(false);
    pathsDimmer.setMouseTransparent(true);
    pathsDimmer.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5);");
    pathsDimmer.setId("mainWindowDimmer");
    pathsWindowVBox.setSpacing(30);
    pathsWindowVBox.getChildren().addAll(pathsWindowCenterBox, pathsWindowBottomBox);
    pathsWindowVBox.setAlignment(javafx.geometry.Pos.CENTER);

    currentPassageVBox = showPassages(currentPassage);
    pathsWindowCenterBox.setCenter(currentPassageVBox);

    pathsWindow.setMinWidth(0);
    pathsWindow.setMinHeight(0);
    pathsWindow.setCenter(pathsWindowVBox);
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

    public StackPane getRoot() {
        return pathsRoot;
    }

  public void updateBottomBox() {
    pathsWindowBottomBox.getChildren().clear();
    pathsWindowBottomBoxHBox.getChildren().clear();
    pathsWindowBottomBoxHBox.setSpacing(5);
    pathsWindowBottomBoxHBox.setAlignment(Pos.CENTER_LEFT);
    pathsWindowBottomBoxHBox.setPadding(new Insets(0, 0, 0, 20));

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
    chestPane.setPadding(new Insets(0, 15, 0, 15));

    StackPane helpPane = new StackPane();
    helpPane.setId("helpPane");
    helpPane.setMinWidth(75);
    helpPane.setMinHeight(75);
    StackPane.setAlignment(pathsWindowBottomBoxHBox2ImageViewHelp, Pos.CENTER);
    helpPane.getChildren().add(pathsWindowBottomBoxHBox2ImageViewHelp);
    helpPane.setPadding(new Insets(0, 15, 0, 15));

    StackPane settingsPane = new StackPane();
    settingsPane.setId("settingsPane");
    settingsPane.setMinWidth(75);
    settingsPane.setMinHeight(75);
    StackPane.setAlignment(pathsWindowBottomBoxHBox2ImageViewSettings, Pos.CENTER);
    settingsPane.getChildren().add(pathsWindowBottomBoxHBox2ImageViewSettings);
    settingsPane.setPadding(new Insets(0, 15, 0, 15));

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


    chestPane.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> controller.showInventory(pathsWindowCenterBox.getWidth()/2, pathsWindowCenterBox.getHeight()/2, pathsDimmer));
    chestPane.addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
      controller.hideInventory(pathsDimmer);
    });

    helpPane.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
      undoMove();
    });

    settingsPane.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> controller.showSettings());
  }

  public VBox showPassages(Passage passage) {
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
      controller.getCurrentPassageLinks(passage).forEach(link -> buttonsVBox.getChildren().add(newButton(link)));

      pathsWindowCenterBoxVBox.getChildren().add(buttonsVBox);
      return pathsWindowCenterBoxVBox;}
    catch (Exception e){
      System.out.println("Error in writePassage");
      return null;
    }
  }
  private Button newButton(Link link){
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
          //throw new RuntimeException(e);
        }
      }
      updateBottomBox();
      currentPassage = game.go(link);
      currentPassageVBox = showPassages(currentPassage);
      pathsWindowCenterBox.setCenter(currentPassageVBox);
      Main.updateStage();
    });
    linkButton.setWrapText(true);
    return linkButton;
  }

  public void undoMove(){
    updateBottomBox();
    currentPassage = game.goSilent(game.goBack());
    currentPassageVBox = showPassages(currentPassage);
    pathsWindowCenterBox.setCenter(currentPassageVBox);
    updateBottomBox();
    Main.updateStage();
  }

  public void setCurrentPassageVBox(VBox currentPassageVBox) {
    this.currentPassageVBox = currentPassageVBox;
    pathsWindowCenterBox.setCenter(currentPassageVBox);
  }
}
