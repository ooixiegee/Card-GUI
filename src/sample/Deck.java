package sample;

import java.util.ArrayList;
import java.util.Collections;

/**
 * This class represent Deck object.
 * Deck is the collection of 52 cards which will called by the main method.
 */

public class Deck{

    //Initialize the deck of cards
    private ArrayList<Card> cards = new ArrayList<Card>();

    //Class connstructor for Deck object //
    public Deck() {
    }

    public void createDonaldDeck(){
        for(Colour cardColour : Colour.values()){
            if(cardColour!=Colour.NO_DONALD && cardColour!=Colour.PASS)
            {
                for(int i=1;i<=13;i++){
                    cards.add(new Card(cardColour,i));
                    /**
                     * Using array list function to add the cards to Card objects.
                     * Once the game started, card are dispensed to Card object
                     * and will reloaded after the game completed
                     * */
                }
            }
        }
    }
    //Method to shuffle the cards Array list before assign  to each player//
    public void shuffle()
    {
        Collections.shuffle(cards);
    }

    //Method to return Array list of cards/
    public ArrayList<Card> getCards() {
        return cards;
    }

    //Get card from deck

    //Add card to deck
    public void addCard(Card addCard){
        this.cards.add(addCard);
    }

    public Card getCard(int i){
        return this.cards.get(i);
    }

    public void removeCard(int i){
        this.cards.remove(i);
    }
}
