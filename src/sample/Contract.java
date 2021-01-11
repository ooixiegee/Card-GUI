package sample;


public class Contract {
    private Colour donald_value;
    private int number_of_donald;
    private Card contractCard;


    public Contract(Colour c, int v)
    {
        donald_value= c;
        number_of_donald = v;
        contractCard = new Card(donald_value,number_of_donald);
    }

    public Card getContractCard() {
        return contractCard;
    }

    public void setContractCard(Card contractCard) {
        this.contractCard = contractCard;
    }

    public Colour getDonaldValue()
    {
        return donald_value;
    }

    public int getNumberOfDonald()
    {
        return number_of_donald;
    }

    public char getCharVal()
    {
        switch(donald_value)
        {
            case RED:
                return 'R';
            case YELLOW:
                return 'Y';
            case BLUE:
                return 'B';
            case GREEN:
                return 'G';
            case NO_DONALD:
                return 'T';
            case PASS:
                return 'P';
        }
        return 0;
    }

    public int getRankVal() {
        switch(donald_value) {
            case RED:
                return 3;
            case YELLOW:
                return 2;
            case BLUE:
                return 1;
            case GREEN:
                return 0;
            case NO_DONALD:
                return -1;
            case PASS:
                return -2;
        }
        return 0;
    }
}
