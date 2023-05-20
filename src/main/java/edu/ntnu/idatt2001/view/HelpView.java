package edu.ntnu.idatt2001.view;

import edu.ntnu.idatt2001.Main;
import edu.ntnu.idatt2001.controller.HelpController;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

public class HelpView {
  private final BorderPane helpRoot;
  private final BorderPane setupRoot;
  private final BorderPane gameRoot;
  Button setupHelp;
  Button gameHelp;
  private StackPane superRoot;
  private final HelpController controller;


  public HelpView(HelpController controller, StackPane superRoot) {
    this.helpRoot = new BorderPane();
    this.setupRoot = new BorderPane();
    this.gameRoot = new BorderPane();
    this.controller = controller;
    this.superRoot = superRoot;
    helpRoot.setId("HelpRoot");
    setupRoot.setId("HelpRoot");
    gameRoot.setId("HelpRoot");
    createAndConfigureHelpPane();
    createAndConfigureGameHelpPane();
    createAndConfigureSetupHelpPane();
    createAndLayoutControls();
    updateControllerFromListeners();
    observeModelAndUpdateControls();
  }

  private void createAndConfigureHelpPane() {
    //Top part of help
    Text helpTitle = new Text("Help");
    helpTitle.setId("titleText");

    VBox helpTitleBox = new VBox();
    helpTitleBox.setAlignment(javafx.geometry.Pos.CENTER);
    helpTitleBox.getChildren().addAll(helpTitle);
    helpTitleBox.setPadding(new javafx.geometry.Insets(60, 0, 0, 0));

    //Main part of help
    Text helpText = new Text("You can choose between help for the setup of the game"
            + ", \nor help for the game itself.");
    helpText.setId("DefaultText");
    helpText.wrappingWidthProperty().bind(helpRoot.widthProperty().subtract(200));
    helpText.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
    setupHelp = new Button("Setup");
    gameHelp = new Button("Game");
    setupHelp.setId("helpButton");
    gameHelp.setId("helpButton");

    HBox helpButtonBox = new HBox();
    helpButtonBox.setAlignment(javafx.geometry.Pos.CENTER);
    helpButtonBox.setId("helpMainBox");
    helpButtonBox.getChildren().addAll(setupHelp, gameHelp);
    helpButtonBox.setSpacing(50);

    Button mainMenuButton = new Button("Main Menu");
    mainMenuButton.setId("helpMainButton");
    mainMenuButton.setOnAction(event -> {
      try {
        Main.changeScene(superRoot);
        Main.updateStage();
      } catch (Exception e) {
        e.printStackTrace();
      }
    });

    VBox helpMainBox = new VBox();
    helpMainBox.setSpacing(50);
    helpMainBox.setAlignment(javafx.geometry.Pos.CENTER);
    helpMainBox.getChildren().addAll(helpText, helpButtonBox, mainMenuButton);


    helpRoot.setTop(helpTitleBox);
    helpRoot.setCenter(helpMainBox);
  }

  private void createAndConfigureGameHelpPane() {
    //Top part of game-help
    VBox gameHelpTopBox = new VBox();
    gameHelpTopBox.setAlignment(javafx.geometry.Pos.CENTER);
    Text gameHelpTitle = new Text("Game Help");
    gameHelpTitle.setId("titleText");
    gameHelpTopBox.getChildren().addAll(gameHelpTitle);
    gameHelpTopBox.setPadding(new javafx.geometry.Insets(60, 0, 0, 0));

    //Main part of game-help
    Text gameHelpText = new Text("The game is played by two players, \n" +
            "where each player takes turns placing their ships on the board. \n" +
            "The first player to sink all of the other players ships wins. \n" +
            "The game is played by clicking on the board, \n" +
            "and the game will automatically switch between players. \n" +
            "The game will also automatically switch between the setup and game phase. \n" +
            "The game will also automatically switch between the setup and game phase. \n" +
            "The game will also automatically switch between the setup and game phase. \n" +
            "The game will also automatically switch between the setup and game phase. \n" +
            "The game will also automatically switch between the setup and game phase. \n" +
            "The game will also automatically switch between the setup and game phase. \n" +
            "The game will also automatically switch between the setup and game phase. \n" +
            "The game will also automatically switch between the setup and game phase. \n" +
            "The game will also automatically switch between the setup and game phase. \n");
    gameHelpText.setId("DefaultText");
    gameHelpText.wrappingWidthProperty().bind(gameRoot.widthProperty().subtract(200));
    gameHelpText.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);



    Button backToHelp = new Button("Back");
    backToHelp.setId("helpMainButton");
    backToHelp.setOnAction(event -> showHelp());

    VBox gameMainBox = new VBox();
    gameMainBox.setAlignment(javafx.geometry.Pos.CENTER);
    gameMainBox.getChildren().addAll(gameHelpText, backToHelp);

    //General game-help configuration
    gameRoot.setTop(gameHelpTopBox);
    gameRoot.setCenter(gameMainBox);
  }

  private void createAndConfigureSetupHelpPane() {
    //Top part of setup-help
    VBox setupHelpTopBox = new VBox();
    setupHelpTopBox.setAlignment(javafx.geometry.Pos.CENTER);
    Text setupHelpTitle = new Text("Setup Help");
    setupHelpTitle.setId("titleText");
    setupHelpTopBox.getChildren().addAll(setupHelpTitle);
    setupHelpTopBox.setPadding(new javafx.geometry.Insets(60, 0, 0, 0));

    //Main part of setup-help
    Text setupHelpText = new Text("The setup is done by placing your ships on the board. \n" +
            "You can place your ships by clicking on the board. \n" +
            "You can rotate your ships by pressing the R key. \n" +
            "You can remove your ships by pressing the X key. \n" +
            "You can place your ships by clicking on the board. \n" +
            "You can rotate your ships by pressing the R key. \n" +
            "You can remove your ships by pressing the X key. \n" +
            "You can place your ships by clicking on the board. \n" +
            "You can rotate your ships by pressing the R key. \n" +
            "You can remove your ships by pressing the X key. \n" +
            "You can place your ships by clicking on the board. \n" +
            "You can rotate your ships by pressing the R key. \n" +
            "You can remove your ships by pressing the X key. \n" +
            "You can place your ships by clicking on the board. \n" +
            "You can rotate your ships by pressing the R key. \n" +
            "You can remove your ships by pressing the X key. \n");
    setupHelpText.setId("DefaultText");
    setupHelpText.wrappingWidthProperty().bind(setupRoot.widthProperty().subtract(200));
    setupHelpText.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);

    Button backToHelp = new Button("Back");
    backToHelp.setId("helpMainButton");
    backToHelp.setOnAction(event -> showHelp());

    VBox setupMainBox = new VBox();
    setupMainBox.setAlignment(javafx.geometry.Pos.CENTER);
    setupMainBox.getChildren().addAll(setupHelpText, backToHelp);

    //General setup-help configuration
    setupRoot.setTop(setupHelpTopBox);
    setupRoot.setCenter(setupMainBox);
  }

  private void createAndLayoutControls() {
    setupHelp.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, event -> {
      showSetupHelp();
    });
    gameHelp.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, event -> {
      showGameHelp();
    });
  }

  private void updateControllerFromListeners() {
  }

  private void observeModelAndUpdateControls() {
  }

  public void showHelp(){
    Main.changeScene(helpRoot);
    Main.updateStage();
  }

  public void showSetupHelp(){
    Main.changeScene(setupRoot);
    Main.updateStage();
  }

  public void showGameHelp(){
    Main.changeScene(gameRoot);
    Main.updateStage();
  }

}
