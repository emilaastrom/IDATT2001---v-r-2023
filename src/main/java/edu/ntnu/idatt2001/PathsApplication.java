package edu.ntnu.idatt2001;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Box;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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

        Text entryWindowText = new Text("(PATHS LOGO PLACEMENT)");
        entryWindowText.setStyle(
                "-fx-font-size: 24px; " +
                "-fx-font-weight: bold; " +
                "-fx-padding: 10px 20px 10px 20px; " +
                "-fx-border-width: 1px; " +
                "-fx-border-radius: 5px; " +
                "-fx-background-radius: 5px");
        entryWindowText.setFill(javafx.scene.paint.Color.WHITE);

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
        entryWindowVBox.getChildren().addAll(entryWindowText, entryWindowChooseAdventureButton);

        BorderPane entryWindow = new BorderPane();
        entryWindow.setVisible(false);
        entryWindow.setCenter(entryWindowVBox);
        entryWindow.setStyle("-fx-background-color: rgb(23,56,23)");

        //PATHSWINDOW
        BorderPane pathsWindowCenterBox = new BorderPane();
        pathsWindowCenterBox.setPrefWidth(500);
        pathsWindowCenterBox.setPrefHeight(200);
        pathsWindowCenterBox.setStyle(
                "-fx-background-color: rgb(0,0,0);" +
                "-fx-border-color: rgb(255,255,255);" +
                "-fx-border-width: 5px;"
        );

        BorderPane pathsWindowBottomBox = new BorderPane();
        pathsWindowBottomBox.setPrefWidth(500);
        pathsWindowBottomBox.setPrefHeight(100);
        pathsWindowBottomBox.setStyle(
                "-fx-background-color: rgb(0,0,0);" +
                "-fx-border-color: rgb(255,255,255);" +
                "-fx-border-width: 5px;"
        );

        VBox pathsWindowVBox = new VBox();
        pathsWindowVBox.setSpacing(30);
        pathsWindowVBox.getChildren().addAll(pathsWindowCenterBox, pathsWindowBottomBox);
        pathsWindowVBox.setAlignment(javafx.geometry.Pos.CENTER);

        BorderPane pathsWindow = new BorderPane();
        pathsWindow.setCenter(pathsWindowVBox);
        pathsWindow.setPadding(new javafx.geometry.Insets(50, 200, 50, 200));
        pathsWindow.setVisible(true);
        pathsWindow.setStyle("-fx-background-color: rgb(23,56,23)");

        //General JavaFX settings
        windowStackPane.getChildren().addAll(entryWindow, pathsWindow);
        BorderPane root = new BorderPane();
        root.setCenter(windowStackPane);

        Scene scene = new Scene(root, 1500  , 750);
        stage.setTitle("Paths");
        stage.setScene(scene);
        stage.show();
    }
}
