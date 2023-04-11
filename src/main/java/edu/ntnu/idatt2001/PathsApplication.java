package edu.ntnu.idatt2001;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PathsApplication extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {


        //ENTRYWINDOW
        StackPane entryWindowStackPane = new StackPane();
        VBox entryWindowVBox = new VBox();
        entryWindowVBox.setPrefWidth(500);
        entryWindowVBox.setAlignment(javafx.geometry.Pos.CENTER);
        entryWindowVBox.setSpacing(30);

        Text entryWindowText = new Text("PATHS LOGO NICE");
        entryWindowText.setStyle(
                "-fx-font-size: 24px; " +
                "-fx-font-weight: bold; " +
                "-fx-padding: 10px 20px 10px 20px; " +
                "-fx-border-width: 1px; " +
                "-fx-border-radius: 5px; " +
                "-fx-background-radius: 5px");
        entryWindowText.setFill(javafx.scene.paint.Color.BLACK);


        Button entryWindowChooseAdventureButton = new Button("CHOOSE YOUR ADVENTURE (.paths file)");
        entryWindowChooseAdventureButton.setStyle(
                "-fx-background-color: #6AA84F; " +
                "-fx-border-color: rgb(0,0,0); " +
                "-fx-border-width: 1px; " +
                "-fx-border-radius: 5px; " +
                "-fx-font-style: bold;" +
                "-fx-font-size: 16px; " +
                "-fx-font-weight: bold; " +
                "-fx-padding: 10px 20px 10px 20px; " +
                "-fx-text-fill: rgb(255,255,255); " +
                "-fx-background-radius: 5px;");
        entryWindowVBox.getChildren().addAll(entryWindowText, entryWindowChooseAdventureButton);

        BorderPane entryWindow = new BorderPane();
        entryWindow.setVisible(true);
        entryWindow.setCenter(entryWindowVBox);
        entryWindow.setStyle("-fx-background-color: rgb(255,255,255)");


        entryWindowStackPane.getChildren().addAll(entryWindow);

        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: rgb(255,255,255)");
        root.setCenter(entryWindowStackPane);

        Scene scene = new Scene(root, 1500  , 750);
        stage.setTitle("Mappevurderings2023/Paths");
        stage.setScene(scene);
        stage.show();
    }
}
