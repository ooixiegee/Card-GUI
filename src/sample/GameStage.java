package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.util.ArrayList;

public class GameStage {
    private ArrayList<Button> cardButton = new ArrayList<Button>();
    private Hand hand;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    private Button button9;
    private Button button10;
    private Button button11;
    private Button button12;
    private Button button13;

    public GameStage(Button button1, Button button2, Button button3, Button button4, Button button5, Button button6, Button button7, Button button8, Button button9, Button button10, Button button11, Button button12, Button button13){
        this.button1 = button1;
        this.button2 = button2;
        this.button3 = button3;
        this.button4 = button4;
        this.button5 = button5;
        this.button6 = button6;
        this.button7 = button7;
        this.button8 = button8;
        this.button9 = button9;
        this.button10 = button10;
        this.button11 = button11;
        this.button12 = button12;
        this.button13 = button13;
        bindCardIcon();
    }

    public void bindCardIcon(){
        cardButton.add(button1);
        cardButton.add(button2);
        cardButton.add(button3);
        cardButton.add(button4);
        cardButton.add(button5);
        cardButton.add(button6);
        cardButton.add(button7);
        cardButton.add(button8);
        cardButton.add(button9);
        cardButton.add(button10);
        cardButton.add(button11);
        cardButton.add(button12);
        cardButton.add(button13);
    }

    public void setCardIcon(){
        //=== match all the button to all the current Hand Card ===
        for(int i=0; i<hand.getHandCardSize(); i++){
            cardButton.get(i).setGraphic(hand.getCard(i).getRectangle());
        }
        for(int j= hand.getHandCardSize(); j<13; j++){
            cardButton.get(j).setVisible(false);
        }
    }


    public void setHand(Hand hand){
        this.hand = hand;
    }
}
