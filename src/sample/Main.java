package sample;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import javax.print.attribute.standard.Media;
import java.io.File;

public class Main extends Application implements interface1{

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/resources/card/coolgui.fxml"));
        Scene scene = new Scene(root, APP_WIDTH, APP_HEIGHT);
        scene.getStylesheets().add(getClass().getResource("/resources/gold.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();


        primaryStage.setTitle("Donald Game");

        primaryStage.show();



    }

    public static void main(String[] args) {
        launch(args);
    }
}
