package sample;

import java.util.ArrayList;
import java.util.Arrays;

public class Game {
    private Deck deck;
    private ArrayList<String> names;

    private int declarer;
    private int declarer_partner;
    private int opposite1;
    private int opposite2;
    private int declarerOnRound;
    private boolean donaldIsPlayed;
    private Contract curContract = new Contract(Colour.PASS,0);
    private int trickDeclarerwin;
    private int trickOppositewin;
    private int scoreDeclarerTeam = 0;
    private int scoreOppositeTeam = 0;
    private ArrayList<Contract> contracts = new ArrayList<Contract>();
    ArrayList<Integer> score = new ArrayList<Integer>();
    ArrayList<Integer> positions = new ArrayList<Integer>();
    ArrayList<Card> trickHand = new ArrayList<Card>();
    ArrayList<Colour> declarerDonaldIsPlayed = new ArrayList<Colour>();
    ArrayList<Colour> colourList = new ArrayList<Colour>(Arrays.asList(Colour.GREEN, Colour.BLUE, Colour.YELLOW, Colour.RED, Colour.NO_DONALD, Colour.PASS));

    public Game(Deck d) {
        deck = d;
    }

    public Game(){}

    public ArrayList<Hand> deal() {
        ArrayList<Hand> hands = new ArrayList<Hand>();

        for (int i = 0; i < 4; i++) {
            ArrayList<Card> cards = new ArrayList<Card>();
            for (int j = 0; j < 13; j++) {
                cards.add(deck.getCards().get(0));
                deck.getCards().remove(0);
            }

            Hand hand = new Hand(cards);
            hands.add(hand);
        }
        return hands;
    }

    public boolean getDeclarerTeammate(String colour, String rank, Hand declarerHand, Hand hand0, Hand hand1, Hand hand2,Hand hand3) {
        Colour tempColour = convertColour(colour);
        int tempRank = convertRank(rank);
        boolean valid = false;
        if (declarerHand.hasCard(tempColour, tempRank) != true) {
            if (hand0.hasCard(tempColour, tempRank) == true) {
                declarer_partner = 1;
                valid = true;
            } else if (hand1.hasCard(tempColour, tempRank) == true) {
                declarer_partner = 2;
                valid = true;
            } else if (hand2.hasCard(tempColour, tempRank) == true) {
                declarer_partner = 3;
                valid = true;
            } else if (hand3.hasCard(tempColour, tempRank) == true) {
                declarer_partner = 4;
                valid = true;
            }
        }
        else{
            valid = false;
        }
        return valid;
    }
    public void calculateTrickToWin(){
        trickDeclarerwin = 6+ curContract.getNumberOfDonald();
        trickOppositewin = 8- curContract.getNumberOfDonald();
    }

    public void getOppositeTeam(){
        if (declarer == 1 && declarer_partner == 2 || declarer == 2 && declarer_partner == 1) {
            opposite1 = 3;
            opposite2 = 4;
        } else if (declarer == 1 && declarer_partner == 3 || declarer == 3 && declarer_partner == 1) {
            opposite1 = 2;
            opposite2 = 4;
        } else if (declarer == 1 && declarer_partner == 4 || declarer == 4 && declarer_partner == 1) {
            opposite1 = 2;
            opposite2 = 3;
        } else if (declarer == 2 && declarer_partner == 3 || declarer == 3 && declarer_partner == 2) {
            opposite1 = 1;
            opposite2 = 4;
        } else if (declarer == 2 && declarer_partner == 4 || declarer == 4 && declarer_partner == 2) {
            opposite1 = 1;
            opposite2 = 3;
        } else if (declarer == 3 && declarer_partner == 4 || declarer == 4 && declarer_partner == 3) {
            opposite1 = 1;
            opposite2 = 2;
        }
    }

    public boolean passValidity(int handTurn){
        if(handTurn==4 && curContract.getDonaldValue()==Colour.PASS && curContract.getNumberOfDonald()==0){
            return false;
        }
        else{
            Contract tempContract = new Contract(Colour.PASS,0);
            contracts.add(tempContract);
            return true;
        }
    }

    public boolean checkDonaldvalidity(String donaldValue, String numberofDonald){
        Colour tempColour = convertColour(donaldValue);
        int tempRank = convertRank(numberofDonald);
        Contract tempContract = new Contract(tempColour, tempRank);

        int attempts = 0;
        boolean NumberofDonaldValidity = NumberofDonaldIsValid(tempRank, curContract);
        boolean DonaldValueValidity = DonaldValueIsValid(tempColour, tempRank, curContract);
        if(NumberofDonaldValidity && DonaldValueValidity){
            contracts.add(tempContract);
            curContract = tempContract;
            return true;
        }
        else{
            return false;
        }
    }

    public void addDeclarerPlayCardColour(Colour colour){
        declarerDonaldIsPlayed.add(colour);
    }

    public boolean donaldIsPlayed(){
        for(Colour cards: declarerDonaldIsPlayed){
            if(cards==curContract.getDonaldValue()){
                return true;
            }
        }
        return false;
    }

    public boolean hasColourDonaldIsPlayed(Card card, Colour firstColour, Colour donaldValue){
        if(card.getColour()==firstColour){
            return true;
        }
        else
            return false;
    }

    public boolean noCard(Hand hand, Colour firstColour, Colour donaldValue){
        boolean noCard = true;
        for(Card temp : hand.getCards()){
            if(temp.getColour()==firstColour){
                noCard = false;
            }
        }
        return noCard;
    }

    public boolean checkCardPlayValidity(Hand hand, Card card){
        donaldIsPlayed = donaldIsPlayed();
        boolean hasColour = true;
        boolean noCard = true;
        if(!trickHand.isEmpty()){
            noCard = noCard(hand, trickHand.get(0).getColour(), curContract.getDonaldValue());
            hasColour = hasColourDonaldIsPlayed(card, trickHand.get(0).getColour(), curContract.getDonaldValue());
        }
        else{
            hasColour = true;
        }
        if(noCard){
            return true;
        }
        else if(hasColour){
            return true;
        }
        else if(!hasColour){
            return false;
        }
        else{
            return false;
        }
    }

    public Colour convertColour(String colour){
        switch (colour){
            case "RED":
                return Colour.RED;
            case "BLUE":
                return Colour.BLUE;
            case "YELLOW":
                return Colour.YELLOW;
            case "GREEN":
                return Colour.GREEN;
            case "NO DONALD":
                return Colour.NO_DONALD;
        }
        return null;
    }

    public int convertRank(String rank){
        switch (rank){
            case "1":
                return 1;
            case "2":
                return 2;
            case "3":
                return 3;
            case "4":
                return 4;
            case "5":
                return 5;
            case "6":
                return 6;
            case "7":
                return 7;
            case "8":
                return 8;
            case "9":
                return 9;
            case "10":
                return 10;
            case "A":
                return 11;
            case "B":
                return 12;
            case "C":
                return 13;
        }
        return 0;
    }

    public boolean DonaldValueIsValid(Colour donald_value, int number_of_donald, Contract curContract) {
        Contract tempContract = new Contract(donald_value, number_of_donald);
        int curDonaldValue = curContract.getRankVal();
        int curNumberofDonald = curContract.getNumberOfDonald();
        boolean valST = colourList.contains(donald_value);
        if (tempContract.getRankVal() > curDonaldValue && valST || tempContract.getRankVal() <= curDonaldValue && number_of_donald > curNumberofDonald && valST || tempContract.getCharVal()=='T' && number_of_donald==curNumberofDonald && valST ||tempContract.getCharVal() == 'P') {
            return true;
        }
        return false;
    }

    public boolean NumberofDonaldIsValid(int l, Contract curContract) {
        int curNumberofDonald = curContract.getNumberOfDonald();
        boolean valL = l >= 1 && l <= 7;
        if (l > curNumberofDonald && valL || curNumberofDonald == l && curContract.getCharVal() != 'T' && valL || l == 0) {
            return true;
        }
        return false;
    }

    public void highestCard() {
        Colour donaldValue = curContract.getDonaldValue();
        int tempMaxValue = 0;
        int tempMaxIndex = 0;
        ArrayList<Integer> ranking = new ArrayList<Integer>();

        if (donaldIsPlayed == true) {
            if (curContract.getCharVal() == 'T') {
                for (Card tempCard : trickHand) {
                    if (tempCard.getColour() == trickHand.get(0).getColour()) {
                        ranking.add(tempCard.getRank());
                    } else {
                        ranking.add(1);
                    }
                }
                for (Integer tempRank : ranking) {
                    if (tempRank > tempMaxValue) {
                        tempMaxValue = tempRank;
                        tempMaxIndex = ranking.indexOf(tempRank);
                    }
                }
            }
            else {
                for (int i=0; i<4; i++) {
                    if(i==0){
                        if(trickHand.get(0).getColour()==donaldValue){
                            ranking.add(trickHand.get(i).getRank() * 20);
                            continue;
                        }
                        else{
                            ranking.add(trickHand.get(i).getRank());
                            continue;
                        }
                    }
                    if (trickHand.get(i).getColour() == trickHand.get(0).getColour() && trickHand.get(0).getColour() != donaldValue && trickHand.get(i).getColour()!=donaldValue) {
                        ranking.add(trickHand.get(i).getRank());
                    }
                    else if (trickHand.get(i).getColour() == donaldValue) {
                        ranking.add(trickHand.get(i).getRank() * 20);
                    }
                    else if (trickHand.get(i).getColour() != donaldValue && trickHand.get(i).getColour() != trickHand.get(0).getColour()) {
                        ranking.add(1);
                    }
                }
                for (Integer tempRank : ranking) {
                    if (tempRank > tempMaxValue) {
                        tempMaxValue = tempRank;
                        tempMaxIndex = ranking.indexOf(tempRank);
                    }
                }
            }

        }
        else if (donaldIsPlayed == false) {
            for (int i=0; i<4; i++) {
                if(i==0){
                    ranking.add(trickHand.get(i).getRank());
                    continue;
                }
                else if (trickHand.get(i).getColour() == trickHand.get(0).getColour()) {
                    ranking.add(trickHand.get(i).getRank());
                } else {
                    ranking.add(1);
                }
            }
            for (Integer tempRank : ranking) {
                if (tempRank > tempMaxValue) {
                    tempMaxValue = tempRank;
                    tempMaxIndex = ranking.indexOf(tempRank);
                }
            }
        }
        declarerOnRound = tempMaxIndex + 1;
    }

    public void calculateScore(){
        int turns = positions.get(declarerOnRound-1)+1;
        if(turns == declarer || turns == declarer_partner){
            scoreDeclarerTeam++;
            score.set(turns-1,score.get(turns-1)+1);
        }
        else{
            scoreOppositeTeam++;
            score.set(turns-1,score.get(turns-1)+1);
        }
    }


    /**==================================================Setter and Getter==========================================*/

    public Contract getCurContract() {
        return curContract;
    }

    public void setCurContract(Contract curContract) {
        this.curContract = curContract;
    }

    public void setDeclarer(int declarer) {
        this.declarer = declarer;
    }
    public int getDeclarer() {
        for (Contract tempContract : contracts) {
            if (tempContract.getDonaldValue() == curContract.getDonaldValue() && tempContract.getNumberOfDonald() == curContract.getNumberOfDonald()) {
                declarer = contracts.indexOf(tempContract)+1;
            }
        }
        return declarer;
    }

    public int getDeclarer_partner() {
        return declarer_partner;
    }

    public void setDeclarer_partner(int declarer_partner) {
        this.declarer_partner = declarer_partner;
    }

    public int getDeclarerOnRound() {
        return declarerOnRound;
    }

    public void setDeclarerOnRound(int declarerOnRound) {
        this.declarerOnRound = declarerOnRound;
    }
    public int getOpposite1() {
        return opposite1;
    }

    public void setOpposite1(int opposite1) {
        this.opposite1 = opposite1;
    }

    public int getOpposite2() {
        return opposite2;
    }

    public void setOpposite2(int opposite2) {
        this.opposite2 = opposite2;
    }
    public int getTrickDeclarerwin() {
        return trickDeclarerwin;
    }

    public void setTrickDeclarerwin(int trickDeclarerwin) {
        this.trickDeclarerwin = trickDeclarerwin;
    }

    public int getTrickOppositewin() {
        return trickOppositewin;
    }

    public void setTrickOppositewin(int trickOppositewin) {
        this.trickOppositewin = trickOppositewin;
    }
    public int getScoreDeclarerTeam() {
        return scoreDeclarerTeam;
    }

    public void setScoreDeclarerTeam(int scoreDeclarerTeam) {
        this.scoreDeclarerTeam = scoreDeclarerTeam;
    }

    public int getScoreOppositeTeam() {
        return scoreOppositeTeam;
    }

    public void setScoreOppositeTeam(int scoreOppositeTeam) {
        this.scoreOppositeTeam = scoreOppositeTeam;
    }
}
