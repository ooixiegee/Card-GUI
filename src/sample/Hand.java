package sample;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.collections.ObservableList;
import javafx.scene.Node;

/**
 * This class is Hand class. A hand contains 13 hand cards.*/
public class Hand {

    private ArrayList<Card> cards;

    public Hand(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public Hand() {
    }

    public int getHandCardSize() {
        return cards.size();
    }

    public Card getCard(int i) {
        return cards.get(i);
    }
    public void removeCard(int index){
        cards.remove(index);
    }

    public boolean hasCard(Colour colour, int rank){
        boolean has = false;
        for(Card tempCard : cards){
            if(tempCard.getColour()==colour && tempCard.getRank()==rank){
                return true;
            }
        }
        return has;
    }

    public ArrayList<Card> getCards(){
        return cards;
    }
}