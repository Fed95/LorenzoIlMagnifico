package it.polimi.ingsw.gc12.view.client.gui.representation;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * Represent the color of the player in java fx format for use t in the other classes
 */
public class PlayerColorReal {
    private int opacity;
    private Paint red;
    private Paint blue;
    private Paint green;
    private Paint yellow;

    /**
     * Constructor
     * @param opacity set the opacity of the color, 0 for not showing the color, 1 for showing it
     */
    public PlayerColorReal(int opacity){
        this.opacity = opacity;
        this.red = new Color(1, 0.2353, 0.1608, opacity);
        this.blue = new Color(0.0314, 0.2627, 0.9255, opacity);
        this.green = new Color(0.0706, 0.6039, 0.2706, opacity);
        this.yellow = new Color(1, 1, 0.0471, opacity);
    }
    public Paint getRed(){
        return red;
    }

    public Paint getBlue(){
        return blue;
    }

    public Paint getGreen() {
        return green;
    }

    public Paint getYellow() {
        return yellow;
    }
}
