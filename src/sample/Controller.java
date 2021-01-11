package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.media.AudioClip;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.shape.Rectangle;


import javax.print.attribute.standard.Media;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
        Main obj = new Main();

        /**String path = "C:/Users/user/Downloads/sthlm sunset.mp3";

        Media media = new Media() (new File(path).toURI().toString());


        MediaPlayer mediaPlayer = new MediaPlayer(media);*/

        @FXML
        private AnchorPane rootpane;
        @FXML
        private Button butClose, modButton;
        @FXML
        private Label label;
        @FXML
        private Region background;
        @FXML
        private Button audioPlay;
        @FXML
        private MediaView mv;
        private MediaPlayer mp;
        private Media me;

        @FXML
        private Rectangle rectangleLeftBG, rectangleRightBG;
        private Object AudioClip;
        private MusicList musicList;

        @FXML
        public void changeScene(javafx.event.ActionEvent actionEvent) throws IOException {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/resources/card/abcdefg.fxml"));
            rootpane.getChildren().setAll(pane);
        }


        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
            butClose.setOnMouseClicked(e -> System.exit(0));

        }


        public void saveName(ActionEvent actionEvent) {
        }

        public void playSong(javafx.event.ActionEvent actionEvent) throws IOException {
                musicList = new MusicList();
                musicList.play();
               }

        }








