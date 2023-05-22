package edu.ntnu.idatt2001.view;

import edu.ntnu.idatt2001.Main;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * The type Help view.
 */
public class HelpView {
  private final BorderPane helpRoot;
  private final BorderPane setupRoot;
  private final BorderPane gameRoot;
  private Button setupHelp;
  private Button gameHelp;
  private Button gameToHelp;
  private Button setupToHelp;
  private final StackPane superRoot;

  /**
   * Constructor for help view.
   *
   * @param superRoot the root of the main menu
   */
  public HelpView(StackPane superRoot) {
    this.helpRoot = new BorderPane();
    this.setupRoot = new BorderPane();
    this.gameRoot = new BorderPane();
    this.superRoot = superRoot;
    helpRoot.setId("HelpRoot");
    setupRoot.setId("HelpRoot");
    gameRoot.setId("HelpRoot");
    createAndConfigureHelpPane();
    createAndConfigureGameHelpPane();
    createAndConfigureSetupHelpPane();
    createAndLayoutControls();
  }

  private void createAndConfigureHelpPane() {
    //Top part of help pane
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

    //Adding elements to helpRoot
    helpRoot.setTop(helpTitleBox);
    helpRoot.setCenter(helpMainBox);
  }

  private void createAndConfigureGameHelpPane() {
    //Top part of game-help pane
    VBox gameHelpTopBox = new VBox();
    gameHelpTopBox.setAlignment(javafx.geometry.Pos.CENTER);
    Text gameHelpTitle = new Text("Game Help");
    gameHelpTitle.setId("titleText");
    gameHelpTopBox.getChildren().addAll(gameHelpTitle);
    gameHelpTopBox.setPadding(new javafx.geometry.Insets(60, 0, 0, 0));

    //Main part of game-help
    Text gameHelpText = new Text("The game is played by pressing buttons based on your wishes, \n" +
            "For each button pressed, you are presented with different opportunities. \n\n" +
            "In some cases, your actions will affect your player state. \n" +
            "You may gain or lose score, health, gold or even items, \n\n" +
            "Check your items by hovering the backpack icon. \n\n" +
            "If you wish to undo moves, you can click the red undo icon. \n\n" +
            "If you wish to reset, return to the main menu or change settings \n" +
            "you can open the settings by clicking the cogwheel icon. \n\n\n");
    gameHelpText.setId("DefaultText");
    gameHelpText.wrappingWidthProperty().bind(gameRoot.widthProperty().subtract(200));
    gameHelpText.setTextAlignment(TextAlignment.JUSTIFY);

    gameToHelp = new Button("Back");

    VBox gameMainBox = new VBox();
    gameMainBox.setAlignment(javafx.geometry.Pos.CENTER);
    gameMainBox.getChildren().addAll(gameHelpText, gameToHelp);

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
    Text setupHelpText = new Text("Start off by selecting your game file. \n" +
            "You can choose your own '.paths' file, or play our example game. \n\n" +
            "Choose the name of your character (up to 20 characters). \n\n" +
            "If you wish, you can set up different goals. \n" +
            "These goals will be presented to you in the end game screen. \n" +
            "Enable the goals you wish by checking the boxes left of the input fields.\n\n" +
            "Once you are done with the setup, press the 'Load Game' button to start playing.\n\n\n");
    setupHelpText.setId("DefaultText");
    setupHelpText.wrappingWidthProperty().bind(setupRoot.widthProperty().subtract(200));
    setupHelpText.setTextAlignment(TextAlignment.JUSTIFY);

    setupToHelp = new Button("Back");

    VBox setupMainBox = new VBox();
    setupMainBox.setAlignment(javafx.geometry.Pos.CENTER);
    setupMainBox.getChildren().addAll(setupHelpText, setupToHelp);

    //General setup-help configuration
    setupRoot.setTop(setupHelpTopBox);
    setupRoot.setCenter(setupMainBox);
  }

  /**
   * Create and layout controls for the different help panes.
   */
  private void createAndLayoutControls() {
    //Adding event handlers and setting ID for CSS styling
    setupHelp.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, event -> showSetupHelp());
    gameHelp.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, event -> showGameHelp());
    setupHelp.setId("helpButton");
    gameHelp.setId("helpButton");
    gameToHelp.setId("helpMainButton");
    gameToHelp.setOnAction(event -> showHelp());
    setupToHelp.setId("helpMainButton");
    setupToHelp.setOnAction(event -> showHelp());
  }

  /**
   * Display the first help page.
   */
  public void showHelp(){
    //Swapping root to general help pane
    Main.changeScene(helpRoot);
    Main.updateStage();
  }

  /**
   * Display the setup help page.
   */
  public void showSetupHelp(){
    //Swapping root to set up help pane
    Main.changeScene(setupRoot);
    Main.updateStage();
  }

  /**
   * Display the game help page.
   */
  public void showGameHelp(){
    //Swapping root to game help pane
    Main.changeScene(gameRoot);
    Main.updateStage();
  }

}
