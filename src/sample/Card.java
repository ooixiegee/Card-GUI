package sample;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import java.io.File;

public class Card extends Parent implements interface1
{
    public Colour colour;
    public int rank;
    public Suit suit;
    public Value value;
    private  String IMG_PATH;
    private ImagePattern IMG_PATTERN;
    private Image IMG;
    private Rectangle card;
    public Card(Colour colour, int rank) {
        this.colour=colour;
        this.rank=rank;
        IMG_PATH = "C:/Users/user/Desktop/resources/gamecard/" + colour.toString().toLowerCase()+Integer.toString(rank)+ ".png";
        IMG = new Image(new File(IMG_PATH).toURI().toString());
        IMG_PATTERN = new ImagePattern(IMG);

        // === View to hold card ===
        card = new Rectangle(CARD_WIDTH, CARD_HEIGHT);
        card.setArcWidth(15);
        card.setArcHeight(15);
        card.setFill(IMG_PATTERN);
    }
    public Rectangle getRectangle(){
        return card;
    }


    public Colour getColour() {
        return colour;
    }

    public int getRank() {
        return rank;
    }

    public int getHCP() {
        int HCP = this.getRank()-10;
        if(HCP>0)
        {
            return HCP;
        }
        return 0;
    }

    /**==================================================Setter and Getter==========================================*/
    public Image getIMG() {
        return IMG;
    }

    public void setIMG(Image IMG) {
        this.IMG = IMG;
    }
    public String getIMG_PATH() {
        return IMG_PATH;
    }

    public void setIMG_PATH(String IMG_PATH) {
        this.IMG_PATH = IMG_PATH;
    }
}