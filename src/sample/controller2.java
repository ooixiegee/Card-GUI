package sample;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.Media;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.*;

public class controller2 implements Initializable{
    @FXML
    private Button clicky;
    @FXML
    private AnchorPane gametable;
    @FXML
    private Button play;
    @FXML
    private Button button1;
    @FXML
    private Button button2;
    @FXML
    private Button button3;
    @FXML
    private Button button4;
    @FXML
    private Button button5;
    @FXML
    private Button button6;
    @FXML
    private Button button7;
    @FXML
    private Button button8;
    @FXML
    private Button button9;
    @FXML
    private Button button10;
    @FXML
    private Button button11;
    @FXML
    private Button button12;
    @FXML
    private Button button13;
    @FXML
    private Label bitDonaldText;
    @FXML
    private Button donaldYes;
    @FXML
    private Button donaldPass;
    @FXML
    private ImageView contract;
    @FXML
    private ImageView card1;
    @FXML
    private ImageView card2;
    @FXML
    private ImageView card3;
    @FXML
    private Label labelName;
    @FXML
    private Label scoreDeclarerTeam;
    @FXML
    private Label scoreDT;
    @FXML
    private Label scoreOppositeTeam;
    @FXML
    private Label scoreOT;
    @FXML
    private Label yourScore;
    @FXML
    private Label youscore;

    private boolean afterBiddingPhase = false;
    private SimpleIntegerProperty handTurn = new SimpleIntegerProperty(0);
    private SimpleIntegerProperty roundTurn = new SimpleIntegerProperty(1);
    private SimpleStringProperty playerName = new SimpleStringProperty();
    private SimpleBooleanProperty gameover = new SimpleBooleanProperty();
    private SimpleBooleanProperty CardPlayValidity = new SimpleBooleanProperty();
    private int playerHand;
    private int playerIdentifier;
    private Game game;
    private Deck deck;
    private MusicList musicList = new MusicList();
    private String currentPlayerName;
    private ArrayList<String> names = new ArrayList<String>();
    ArrayList<Hand> hands = new ArrayList<Hand>();
    private Stage stage1;


    public controller2() {
    }

    public void playMou(ActionEvent actionEvent) {
        play.setOnMouseClicked(e -> System.exit(0));
    }

    public void start(ActionEvent actionEvent) {
        handTurn.set(1);
        clicky.setVisible(false);
        button1.setVisible(true);
        button2.setVisible(true);
        button3.setVisible(true);
        button4.setVisible(true);
        button5.setVisible(true);
        button6.setVisible(true);
        button7.setVisible(true);
        button8.setVisible(true);
        button9.setVisible(true);
        button10.setVisible(true);
        button11.setVisible(true);
        button12.setVisible(true);
        button13.setVisible(true);
        bitDonaldText.setVisible(true);
        donaldYes.setVisible(true);
        donaldPass.setVisible(true);
    }

    public void initializeScore(){
        game.score.add(0);
        game.score.add(0);
        game.score.add(0);
        game.score.add(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        deck = new Deck();
        deck.createDonaldDeck();
        deck.shuffle();
        game = new Game(deck);
        hands= game.deal();
        enterNameWindow();
        GameStage gameStage = new GameStage(button1,button2,button3,button4,button5,button6,button7,button8,button9,button10,button11,button12,button13);
        initializeScore();

        handTurn.addListener((obs, old, newValue) -> {
            playerHand = newValue.intValue()%4;
            switch (playerHand){
                case 1:
                    gameStage.setHand(hands.get(0));
                    gameStage.setCardIcon();
                    labelName.setText(names.get(0));
                    playerIdentifier = 1;
                    break;
                case 2:
                    gameStage.setHand(hands.get(1));
                    gameStage.setCardIcon();
                    labelName.setText(names.get(1));
                    playerIdentifier = 2;
                    break;
                case 3:
                    gameStage.setHand(hands.get(2));
                    gameStage.setCardIcon();
                    labelName.setText(names.get(2));
                    playerIdentifier = 3;
                    break;
                case 0:
                    gameStage.setHand(hands.get(3));
                    gameStage.setCardIcon();
                    labelName.setText(names.get(3));
                    playerIdentifier = 4;
                    break;
            }
            scoreDT.setText(String.valueOf(game.getScoreDeclarerTeam()));
            scoreOT.setText(String.valueOf(game.getScoreOppositeTeam()));
            youscore.setText(String.valueOf(game.score.get(playerIdentifier-1)));
        });

        roundTurn.addListener((obs, old, newValue) -> {
            switch (newValue.intValue()){
                case 2:
                    card1.setImage(game.trickHand.get(0).getIMG());
                    break;
                case 3:
                    card2.setImage(game.trickHand.get(1).getIMG());
                    break;
                case 4:
                    card3.setImage(game.trickHand.get(2).getIMG());
                    break;
            }
            if(newValue.intValue()==5){
                game.highestCard();
                game.calculateScore();
                if(getOver()){
                    gameover.set(true);
                }
                handTurn.set(game.positions.get(game.getDeclarerOnRound()-1)+1);
                youscore.setText(String.valueOf(game.score.get(game.positions.get(game.getDeclarerOnRound()-1))));
                scoreDT.setText(String.valueOf(game.getScoreDeclarerTeam()));
                scoreOT.setText(String.valueOf(game.getScoreOppositeTeam()));
                card1.setImage(null);
                card2.setImage(null);
                card3.setImage(null);
                game.trickHand.clear();
                game.positions.clear();
                roundTurn.set(1);
            }
        });

        gameover.addListener((obs, old, newValue) -> {
            if(newValue.booleanValue()){
                button1.setVisible(false);
                button2.setVisible(false);
                button3.setVisible(false);
                button4.setVisible(false);
                button5.setVisible(false);
                button6.setVisible(false);
                button7.setVisible(false);
                button8.setVisible(false);
                button9.setVisible(false);
                button10.setVisible(false);
                button11.setVisible(false);
                button12.setVisible(false);
                button13.setVisible(false);

                Stage stage = new Stage();
                Text text = new Text("");
                Font font = Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12);
                text.setFont(font);
                text.setTranslateX(250);
                text.setTranslateY(55);
                text.setFill(Color.BROWN);
                text.maxWidth(580);
                text.setWrappingWidth(580);
                if(game.getScoreDeclarerTeam()>=game.getTrickDeclarerwin()){
                    text.setText("Game Over!\n" + names.get(game.getDeclarer()-1) + " and " + names.get(game.getDeclarer_partner()-1) + " win!");
                }
                else{
                    text.setText("Game Over!\n" + names.get(game.getOpposite1()-1) + " and " + names.get(game.getOpposite2()-1) + " win!");
                }
                Group root = new Group(text);
                Scene scene = new Scene(root, 595, 150, Color.BEIGE);
                stage.setTitle("Game Over");
                stage.setScene(scene);
                stage.initStyle(StageStyle.UNDECORATED);
                stage.show();
            }
        });
    }

    public void enterNameWindow(){
        Stage stage = new Stage();
        Label player1 =new Label("Player 1: ");
        Label player2 = new Label("Player 2: ");
        Label player3 = new Label("Player 3: ");
        Label player4 = new Label("Player 4: ");
        TextField p1 = new TextField();
        TextField p2 = new TextField();
        TextField p3 = new TextField();
        TextField p4 = new TextField();
        Button b = new Button("Submit");
        b.setTranslateX(300);
        b.setStyle("-fx-outline:none;\n" +
                "    -fx-height: 40px;\n" +
                "    -fx-text-align: center;\n" +
                "    -fx-width: 130px;\n" +
                "    -fx-border-radius:40px;\n" +
                "    -fx-background: #fff;\n" +
                "    -fx-border-width: 2px;\n" +
                "    -fx-border-style:solid;\n" +
                "    -fx-border-color:GREEN;\n" +
                "    -fx-color:GREEN;\n" +
                "    -fx-text-shadow:0;\n" +
                "    -fx-font-size:12px;\n" +
                "    -fx-font-weight:bold;\n" +
                "    -fx-cursor: hand;\n" +
                "    -fx-transition: all 0.25s ease;\n" +
                "    font-family:'FontAwesome';");
        b.setOnAction(ev -> {
            names.add(p1.getText());
            names.add(p2.getText());
            names.add(p3.getText());
            names.add(p4.getText());
            stage.close();
            clicky.setVisible(true);
        });

        HBox box1 = new HBox(5);
        HBox box2 = new HBox(5);
        HBox box3 = new HBox(5);
        HBox box4 = new HBox(5);
        HBox box5 = new HBox();
        box1.getChildren().addAll(player1,p1);
        box2.getChildren().addAll(player2,p2);
        box3.getChildren().addAll(player3,p3);
        box4.getChildren().addAll(player4,p4);
        box5.getChildren().add(b);
        box1.setPadding(new Insets(25, 5 , 5, 50));
        box2.setPadding(new Insets(25, 5 , 5, 50));
        box3.setPadding(new Insets(25, 5 , 5, 50));
        box4.setPadding(new Insets(25, 5 , 5, 50));
        VBox vbox = new VBox();
        vbox.getChildren().addAll(box1, box2, box3, box4,box5);
        Group root = new Group(vbox);
        Scene scene = new Scene(root, 400, 260, Color.BEIGE);
        stage.setTitle("Enter Player Name");
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }

    public void passBidDonald(ActionEvent actionEvent){
        boolean canPass = game.passValidity(handTurn.getValue());
        if(canPass){
            if(handTurn.getValue()<4){
                handTurn.set(handTurn.getValue()+1);
            }
            else{
                bitDonaldText.setVisible(false);
                donaldYes.setVisible(false);
                donaldPass.setVisible(false);
                contract.setImage(game.getCurContract().getContractCard().getIMG());
                handTurn.set(game.getDeclarer());
                getTeammate();
                scoreDeclarerTeam.setVisible(true);
                scoreOppositeTeam.setVisible(true);
                yourScore.setVisible(true);
                scoreOT.setVisible(true);
                scoreDT.setVisible(true);
                youscore.setVisible(true);
                game.calculateTrickToWin();
            }
        }
        else{
            Stage stage = new Stage();
            Text text = new Text("");
            Font font = Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12);
            text.setFont(font);
            text.setTranslateX(130);
            text.setTranslateY(55);
            text.setFill(Color.BROWN);
            text.maxWidth(580);
            text.setWrappingWidth(580);
            text.setText("Previous three player did not bid for Donald!");
            Group root = new Group(text);
            Scene scene = new Scene(root, 595, 150, Color.BEIGE);
            stage.setTitle("Bid for Donald");
            stage.setScene(scene);
            stage.show();
        }
    }

    public void bidDonald(ActionEvent actionEvent){
        Stage stage = new Stage();
        ComboBox comboBox = new ComboBox();
        Label labelColour = new Label("Colour: ");
        comboBox.getItems().add("NO DONALD");
        comboBox.getItems().add("RED");
        comboBox.getItems().add("YELLOW");
        comboBox.getItems().add("BLUE");
        comboBox.getItems().add("GREEN");

        ComboBox comboBox2 = new ComboBox();
        Label labelRank = new Label("Rank: ");
        comboBox2.getItems().add("1");
        comboBox2.getItems().add("2");
        comboBox2.getItems().add("3");
        comboBox2.getItems().add("4");
        comboBox2.getItems().add("5");
        comboBox2.getItems().add("6");
        comboBox2.getItems().add("7");
        labelRank.setTranslateX(40);
        comboBox2.setTranslateX(40);

        comboBox.setValue("NO DONALD");
        comboBox2.setValue("1");

        Text text = new Text("");
        Font font = Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 10);
        text.setFont(font);
        text.setTranslateX(15);
        text.setTranslateY(125);
        text.setFill(Color.BROWN);
        text.maxWidth(580);
        text.setWrappingWidth(580);


        Button button = new Button("Bid");
        button.setTranslateX(230);
        button.setTranslateY(75);
        button.setStyle("-fx-outline:none;\n" +
                "    -fx-height: 40px;\n" +
                "    -fx-text-align: center;\n" +
                "    -fx-width: 130px;\n" +
                "    -fx-border-radius:40px;\n" +
                "    -fx-background: #fff;\n" +
                "    -fx-border-width: 2px;\n" +
                "    -fx-border-style:solid;\n" +
                "    -fx-border-color:GREEN;\n" +
                "    -fx-color:GREEN;\n" +
                "    -fx-text-shadow:0;\n" +
                "    -fx-font-size:12px;\n" +
                "    -fx-font-weight:bold;\n" +
                "    -fx-cursor: hand;\n" +
                "    -fx-transition: all 0.25s ease;\n" +
                "    font-family:'FontAwesome';");
        button.setOnAction(e -> {
            String bidDonald = comboBox.getSelectionModel().getSelectedItem().toString();
            String bidRank = comboBox2.getSelectionModel().getSelectedItem().toString();

            boolean valid = game.checkDonaldvalidity(bidDonald, bidRank);

            if(valid){
                if(handTurn.getValue()<4){
                    text.setText("You have bid " + bidDonald + " " + bidRank + "!");
                    stage.close();
                    handTurn.set(handTurn.getValue() + 1);
                }
                else{
                    text.setText("You have bid "+ bidDonald + " " + bidRank + "!");
                    bitDonaldText.setVisible(false);
                    donaldYes.setVisible(false);
                    donaldPass.setVisible(false);
                    contract.setImage(game.getCurContract().getContractCard().getIMG());
                    handTurn.set(game.getDeclarer());
                    stage.close();
                    getTeammate();
                    scoreDeclarerTeam.setVisible(true);
                    scoreOppositeTeam.setVisible(true);
                    yourScore.setVisible(true);
                    scoreOT.setVisible(true);
                    scoreDT.setVisible(true);
                    youscore.setVisible(true);
                    game.calculateTrickToWin();
                }
            }
            else{
                text.setText("Please bid correct Donald!");
            }
        });
        HBox box = new HBox(5);
        box.setPadding(new Insets(25, 5 , 5, 50));
        box.getChildren().addAll(labelColour,comboBox, labelRank, comboBox2);
        Group root = new Group(box, button, text);
        Scene scene = new Scene(root, 595, 150, Color.BEIGE);
        stage.setTitle("Bid for Donald");
        stage.setScene(scene);
        stage.show();
    }

    public boolean getOver(){
        if(game.getScoreOppositeTeam()>=game.getTrickOppositewin() || game.getScoreDeclarerTeam()>=game.getTrickDeclarerwin()){
            return true;
        }
        return false;
    }

    public void getTeammate(){
        Stage stage2 = new Stage();
        ComboBox comboBox3 = new ComboBox();
        Label labelColour2 = new Label("Colour: ");
        comboBox3.getItems().add("RED");
        comboBox3.getItems().add("YELLOW");
        comboBox3.getItems().add("BLUE");
        comboBox3.getItems().add("GREEN");
        ComboBox comboBox4 = new ComboBox();
        Label labelRank2 = new Label("Rank: ");
        comboBox4.getItems().add("1");
        comboBox4.getItems().add("2");
        comboBox4.getItems().add("3");
        comboBox4.getItems().add("4");
        comboBox4.getItems().add("5");
        comboBox4.getItems().add("6");
        comboBox4.getItems().add("7");
        comboBox4.getItems().add("8");
        comboBox4.getItems().add("9");
        comboBox4.getItems().add("10");
        comboBox4.getItems().add("A");
        comboBox4.getItems().add("B");
        comboBox4.getItems().add("C");
        comboBox3.setValue("RED");
        comboBox4.setValue("1");

        Text text2 = new Text("");
        Font font2 = Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 10);
        text2.setFont(font2);
        text2.setTranslateX(150);
        text2.setTranslateY(125);
        text2.setFill(Color.BROWN);
        text2.maxWidth(580);
        text2.setWrappingWidth(580);
        labelRank2.setTranslateX(80);
        comboBox4.setTranslateX(90);

        Button button2 = new Button("Choose");
        button2.setTranslateX(220);
        button2.setTranslateY(75);
        button2.setStyle("-fx-outline:none;\n" +
                "    -fx-height: 40px;\n" +
                "    -fx-text-align: center;\n" +
                "    -fx-width: 130px;\n" +
                "    -fx-border-radius:40px;\n" +
                "    -fx-background: #fff;\n" +
                "    -fx-border-width: 2px;\n" +
                "    -fx-border-style:solid;\n" +
                "    -fx-border-color:GREEN;\n" +
                "    -fx-color:GREEN;\n" +
                "    -fx-text-shadow:0;\n" +
                "    -fx-font-size:12px;\n" +
                "    -fx-font-weight:bold;\n" +
                "    -fx-cursor: hand;\n" +
                "    -fx-transition: all 0.25s ease;\n" +
                "    font-family:'FontAwesome';");

        button2.setOnAction(ev -> {
            String teamColour = comboBox3.getSelectionModel().getSelectedItem().toString();
            String teamRank = comboBox4.getSelectionModel().getSelectedItem().toString();
            boolean validCard = game.getDeclarerTeammate(teamColour, teamRank, hands.get(game.getDeclarer()-1), hands.get(0), hands.get(1), hands.get(2), hands.get(3));
            if(validCard){
                game.getOppositeTeam();
                stage2.close();
                afterBiddingPhase = true;
            }
            else{
                text2.setText("Don't choose your own card!");
            }
        });
        HBox box2 = new HBox(5);
        box2.setPadding(new Insets(25, 5 , 5, 50));
        box2.getChildren().addAll(labelColour2,comboBox3, labelRank2, comboBox4);
        Group root = new Group(box2, button2, text2);
        Scene scene = new Scene(root, 500, 150, Color.BEIGE);
        stage2.setTitle("Declarer Choose your Teammate");
        stage2.setScene(scene);
        stage2.initStyle(StageStyle.UNDECORATED);
        stage2.show();
    }

    public void stageCardWrongPlayingPhase(){
        Stage stage = new Stage();
        Text text = new Text("");
        Font font = Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12);
        text.setFont(font);
        text.setTranslateX(200);
        text.setTranslateY(55);
        text.setFill(Color.BROWN);
        text.maxWidth(580);
        text.setWrappingWidth(580);
        text.setText("Please choose correct card!");
        Group root = new Group(text);
        Scene scene = new Scene(root, 595, 150, Color.BEIGE);
        stage.setTitle("Wrong Play Card");
        stage.setScene(scene);
        stage.show();
    }

    public void handleButton1(ActionEvent actionEvent) {
        if(afterBiddingPhase){
            if (checkCardValidity(0)) {
                handleCardButton(0);
            }
            else{
                stageCardWrongPlayingPhase();
            }
        }
        else{
        }
    }

    public void handleButton2(ActionEvent actionEvent) {
        if(afterBiddingPhase){
            if (checkCardValidity(1)) {
                handleCardButton(1);
            }
            else{
                stageCardWrongPlayingPhase();
            }
        }
        else{
        }
    }

    public void handleButton3(ActionEvent actionEvent) {
        if(afterBiddingPhase){
            if (checkCardValidity(2)) {
                handleCardButton(2);
            }
            else{
                stageCardWrongPlayingPhase();
            }
        }
        else{
        }
    }

    public void handleButton4(ActionEvent actionEvent) {
        if(afterBiddingPhase){
            if (checkCardValidity(3)) {
                handleCardButton(3);
            }
            else{
                stageCardWrongPlayingPhase();
            }
        }
        else{
        }
    }

    public void handleButton5(ActionEvent actionEvent) {
        if(afterBiddingPhase){
            if (checkCardValidity(4)) {
                handleCardButton(4);
            }
            else{
                stageCardWrongPlayingPhase();
            }
        }
        else{
        }
    }

    public void handleButton6(ActionEvent actionEvent) {
        if(afterBiddingPhase){
            if (checkCardValidity(5)) {
                handleCardButton(5);
            }
            else{
                stageCardWrongPlayingPhase();
            }
        }
        else{
        }
    }

    public void handleButton7(ActionEvent actionEvent) {
        if(afterBiddingPhase){
            if (checkCardValidity(6)) {
                handleCardButton(6);
            }
            else{
                stageCardWrongPlayingPhase();
            }
        }
        else{
        }
    }

    public void handleButton8(ActionEvent actionEvent) {
        if(afterBiddingPhase){
            if (checkCardValidity(7)) {
                handleCardButton(7);
            }
            else{
                stageCardWrongPlayingPhase();
            }
        }
        else{
        }
    }

    public void handleButton9(ActionEvent actionEvent) {
        if(afterBiddingPhase){
            if (checkCardValidity(8)) {
                handleCardButton(8);
            }
            else{
                stageCardWrongPlayingPhase();
            }
        }
        else{
        }
    }

    public void handleButton10(ActionEvent actionEvent) {
        if(afterBiddingPhase){
            if (checkCardValidity(9)) {
                handleCardButton(9);
            }
            else{
                stageCardWrongPlayingPhase();
            }
        }
        else{
        }
    }

    public void handleButton11(ActionEvent actionEvent) {
        if(afterBiddingPhase){
            if (checkCardValidity(10)) {
                handleCardButton(10);
            }
            else{
                stageCardWrongPlayingPhase();
            }
        }
        else{
        }
    }

    public void handleButton12(ActionEvent actionEvent) {
        if(afterBiddingPhase){
            if (checkCardValidity(11)) {
                handleCardButton(11);
            }
            else{
                stageCardWrongPlayingPhase();
            }
        }
        else{
        }
    }

    public void handleButton13(ActionEvent actionEvent) {
        if(afterBiddingPhase){
            if (checkCardValidity(12)) {
                handleCardButton(12);
            }
            else{
                stageCardWrongPlayingPhase();
            }
        }
        else{
        }
    }

    public boolean checkCardValidity(int cardIndex){
        return game.checkCardPlayValidity(hands.get(playerIdentifier-1), hands.get(playerIdentifier-1).getCard(cardIndex));
    }

    public void handleCardButton(int cardIndex){
        game.positions.add(playerIdentifier-1);
        game.trickHand.add(hands.get(playerIdentifier-1).getCard(cardIndex));
        if (playerIdentifier != game.getDeclarer()) {
            hands.get(playerIdentifier-1).removeCard(cardIndex);
            handTurn.setValue(playerIdentifier + 1);
            roundTurn.setValue(roundTurn.getValue() + 1);
            Media hit = new Media(new File("C:/Users/user/Desktop/resources/gamecard/click.mp3").toURI().toString());
            MediaPlayer player = new MediaPlayer(hit);
            player.setCycleCount(1);
            player.setStartTime(Duration.seconds(0.8));
            player.setStopTime(Duration.seconds(1.5));
            player.play();
        }
        else {
            game.addDeclarerPlayCardColour(hands.get(game.getDeclarer()-1).getCard(0).getColour());
            hands.get(playerIdentifier-1).removeCard(cardIndex);
            handTurn.setValue(playerIdentifier + 1);
            roundTurn.setValue(roundTurn.getValue() + 1);
            Media hit = new Media(new File("C:/Users/user/Desktop/resources/gamecard/click.mp3").toURI().toString());
            MediaPlayer player = new MediaPlayer(hit);
            player.setCycleCount(1);
            player.setStartTime(Duration.seconds(0.8));
            player.setStopTime(Duration.seconds(1.5));
            player.play();
        }
    }
}


