package it.polimi.ingsw.gc12.view.client.gui.representation;

import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.model.player.PlayerColor;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Paint;


public class TurnOrderTrackPositionRepresentation {
    private ObjectProperty<Paint> playerProperty;

    public TurnOrderTrackPositionRepresentation(PlayerColor playerColor, PlayerColorReal playerColorReal){
        setPlayerProperty(playerColor, playerColorReal);
    }

    public ObjectProperty<Paint> getPlayerProperty() {
        return playerProperty;
    }

    public void setPlayerProperty(PlayerColor playerColor, PlayerColorReal playerColorReal) {
        this.playerProperty = new SimpleObjectProperty<>(getPlayerPaint(playerColor, playerColorReal));
    }

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
