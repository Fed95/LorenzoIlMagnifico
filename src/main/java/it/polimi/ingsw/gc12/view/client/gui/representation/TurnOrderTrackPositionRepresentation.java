package it.polimi.ingsw.gc12.view.client.gui.representation;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Paint;


/**
 * Created by rugge on 26/06/2017.
 */
public class TurnOrderTrackPositionRepresentation {
    private ObjectProperty<Paint> playerProperty;


    public TurnOrderTrackPositionRepresentation(Paint pl1){
        this.playerProperty = new SimpleObjectProperty<>(pl1);

    }

    public ObjectProperty<Paint> getPlayerProperty() {
        return playerProperty;
    }

}
