package it.polimi.ingsw.gc12.view.client.gui.representation;

import it.polimi.ingsw.gc12.model.player.PlayerColor;
import it.polimi.ingsw.gc12.model.player.resource.ResourceType;
import javafx.beans.property.*;

/**
 * Created by rugge on 24/06/2017.
 */
public class ResourceRepresentation {
    private ObjectProperty<PlayerColor> playerColor;
    private ObjectProperty<ResourceType> resourceType;
    private IntegerProperty resourceValue;

    public ResourceRepresentation(PlayerColor playerColor, int value, ResourceType resourceType){
        this.playerColor = new SimpleObjectProperty<PlayerColor>(playerColor);
        this.resourceValue = new SimpleIntegerProperty(value);
        this.resourceType = new SimpleObjectProperty<ResourceType>(resourceType);
    }

    public ObjectProperty<PlayerColor> getPlayerColor(){
        return playerColor;
    }
    public IntegerProperty getValue() {
        return resourceValue;
    }

}
