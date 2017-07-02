package it.polimi.ingsw.gc_12.java_fx;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;


/**
 * Created by rugge on 23/06/2017.
 */
public class CardFloorRepresentation  {

    private SimpleIntegerProperty floorNumber;
    private SimpleObjectProperty<Image> path;
    private SimpleObjectProperty<Image> pathWhenTaken;//da settare con l'immgine del familymember quando arriva event e il floor e' preso?


    private SimpleBooleanProperty taken;

    public CardFloorRepresentation(String url ,int floor, Boolean taken) {
        this.floorNumber = new SimpleIntegerProperty(floor);
        this.path = new SimpleObjectProperty<Image>();
        this.taken = new SimpleBooleanProperty(taken);
        Image image = new Image(url);
        path.set(image);
        this.pathWhenTaken = new SimpleObjectProperty<Image>();

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

    public Image getPathWhenTaken() {
        return pathWhenTaken.get();
    }

    public ObjectProperty<Image> pathWhenTakenProperty() {
        return pathWhenTaken;
    }

    public void setPathWhenTaken(Image pathWhenTaken) {
        this.pathWhenTaken.set(pathWhenTaken);
    }
}
