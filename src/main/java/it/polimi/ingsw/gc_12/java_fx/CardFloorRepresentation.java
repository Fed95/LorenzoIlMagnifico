package it.polimi.ingsw.gc_12.java_fx;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;



/**
 * Created by rugge on 23/06/2017.
 */
public class CardFloorRepresentation  {

    private SimpleIntegerProperty floorNumber;
    private SimpleObjectProperty<Image> path;

    //quando devo cambiare l'immagine faccio cardFloor0Representation = new(patch nuova immagine) non posso fare set

    public CardFloorRepresentation(String url ,int floor) {
        this.floorNumber = new SimpleIntegerProperty(floor);
        this.path = new SimpleObjectProperty<Image>();
        Image image = new Image(url);
        path.set(image);
    }

    public int getFloorNumber() {
        return floorNumber.get();
    }

    public ObjectProperty<Image> getPathProperty() {
        return path;
    }

    public void setPath(Image path) {
        this.path.set(path);
    }
}
