package it.polimi.ingsw.gc12.view.client.gui.representation;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.Image;

/**
 * Created by rugge on 09/07/2017.
 */
public class MarketRepresentation {
    private SimpleObjectProperty<Image> path;//path to refresh with fam
    private SimpleStringProperty pathMarket;//saving the image of the market because are 4 different use this for reset

    public MarketRepresentation(String path){
        Image market = new Image(path);
        this.path = new SimpleObjectProperty<Image>(market);
        this.pathMarket = new SimpleStringProperty(path);
    }

    public void setPath(Image path) {
        this.path.set(path);
    }
    public void resetMarket(){
        Image market = new Image(pathMarket.toString());
        path.set(market);
    }
    public ObjectProperty<Image> getPath(){
        return path;
    }
}
