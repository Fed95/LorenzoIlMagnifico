package it.polimi.ingsw.gc12.view.client.gui.representation;

import it.polimi.ingsw.gc12.model.player.PlayerColor;
import it.polimi.ingsw.gc12.model.player.resource.ResourceType;
import javafx.beans.property.*;

/**
 * It represent a resource in the player Board, is a semplification of the real Resource on the server.
 * The elements contains property that are binded with the java fx with Observable lists or maps.
 */
public class ResourceRepresentation {
    private ObjectProperty<PlayerColor> playerColor;
    private ObjectProperty<ResourceType> resourceType;
    private IntegerProperty resourceValue;

    /**
     * Resource representation constructor
     * @param playerColor the resource belongs to this player
     * @param value value of the resource
     * @param resourceType the resource has this type
     */
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

    public void setResourceValue(int resourceValue) {
        this.resourceValue.set(resourceValue);
    }
}
