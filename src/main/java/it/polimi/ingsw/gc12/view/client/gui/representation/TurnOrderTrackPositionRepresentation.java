package it.polimi.ingsw.gc12.view.client.gui.representation;

import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.model.player.PlayerColor;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Paint;

/**
 * Represent a pawn into the turn order track on the GUI
 */
public class TurnOrderTrackPositionRepresentation {
    private ObjectProperty<Paint> playerProperty;

    /**
     * Constructor
     * @param playerColor player Color owning the pawn
     * @param playerColorReal colors of the java fx representing the players
     */
    public TurnOrderTrackPositionRepresentation(PlayerColor playerColor, PlayerColorReal playerColorReal){
        this.playerProperty = new SimpleObjectProperty<>(getPlayerPaint(playerColor, playerColorReal));
    }

    public ObjectProperty<Paint> getPlayerProperty() {
        return playerProperty;
    }

    public void setPlayerProperty(PlayerColor playerColor, PlayerColorReal playerColorReal) {
        playerProperty.set(getPlayerPaint(playerColor, playerColorReal));
    }

    /**
     * Returning the real player Color in java fx giving the player color
     * @param playerColor player color
     * @param playerColorReal has all the color in java fx
     * @return Paint
     */
    private Paint getPlayerPaint(PlayerColor playerColor,PlayerColorReal playerColorReal) {
        if(playerColor == PlayerColor.BLUE){
            return playerColorReal.getBlue();
        }else if(playerColor == PlayerColor.GREEN){
            return playerColorReal.getGreen();
        }else if(playerColor == PlayerColor.RED){
            return playerColorReal.getRed();
        }else{
            return playerColorReal.getYellow();
        }
    }
}
