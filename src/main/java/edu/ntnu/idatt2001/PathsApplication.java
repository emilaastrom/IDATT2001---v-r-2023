package edu.ntnu.idatt2001;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Box;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PathsApplication extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        //STACKPANE FOR DIFFERENT WINDOWS
        StackPane windowStackPane = new StackPane();



        //ENTRYWINDOW
        VBox entryWindowVBox = new VBox();
        entryWindowVBox.setPrefWidth(500);
        entryWindowVBox.setAlignment(javafx.geometry.Pos.CENTER);
        entryWindowVBox.setSpacing(30);

        Image entryWindowLogo = new Image("file:src/main/resources/templogo.png");
        StackPane entryWindowLogoStackPane = new StackPane();
        entryWindowLogoStackPane.getChildren().add(new javafx.scene.image.ImageView(entryWindowLogo));

        Button entryWindowChooseAdventureButton = new Button("CHOOSE YOUR ADVENTURE (.paths file)");
        entryWindowChooseAdventureButton.setStyle(
                "-fx-background-color: #6AA84F; " +
                "-fx-border-color: rgb(0,0,0); " +
                "-fx-border-width: 1px; " +
                "-fx-border-radius: 5px; " +
                "-fx-font-size: 16px; " +
                "-fx-font-weight: bold; " +
                "-fx-padding: 10px 20px 10px 20px; " +
                "-fx-text-fill: rgb(255,255,255); " +
                "-fx-background-radius: 5px;");
        entryWindowVBox.getChildren().addAll(entryWindowLogoStackPane, entryWindowChooseAdventureButton);

        BorderPane entryWindow = new BorderPane();
        entryWindow.setVisible(true);
        entryWindow.setCenter(entryWindowVBox);
        entryWindow.setStyle("-fx-background-color: rgb(0,0,0,0)");



        //PATHSWINDOW
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
        pathsWindow.setCenter(pathsWindowVBox);
        pathsWindow.setPadding(new javafx.geometry.Insets(50, 200, 50, 200));
        pathsWindow.setVisible(false);
        pathsWindow.setStyle("-fx-background-color: rgb(0,0,0,0)");



        //General JavaFX settings
        BackgroundRepeat backgroundRepeat = BackgroundRepeat.REPEAT;
        BackgroundPosition backgroundPosition = BackgroundPosition.CENTER;
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(
                new javafx.scene.image.Image("file:src/main/resources/background.jpg"),
                backgroundRepeat,
                backgroundRepeat,
                backgroundPosition,
                backgroundSize);
        Background background = new Background(backgroundImage);

        windowStackPane.getChildren().addAll(entryWindow, pathsWindow);
        BorderPane root = new BorderPane();
        root.setCenter(windowStackPane);
        root.setBackground(background);


        Scene scene = new Scene(root, 1500  , 750);
        stage.setTitle("Paths");
        stage.setScene(scene);
        stage.show();
    }
}
