package it.polimi.ingsw.gc12.view.client.gui.representation;

import it.polimi.ingsw.gc12.model.player.PlayerColor;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;

public class BonusTilesRepresentation {
    private ObjectProperty<Image> pathTile;
    private ObjectProperty<PlayerColor> owner;

    public BonusTilesRepresentation(String path, PlayerColor playerColor ){
        Image tile = new Image(path);
        this.pathTile = new SimpleObjectProperty<Image>(tile);
        this.owner = new SimpleObjectProperty<PlayerColor>(playerColor);
    }

    public ObjectProperty<Image> getPathTile(){
        return pathTile;
    }
}
