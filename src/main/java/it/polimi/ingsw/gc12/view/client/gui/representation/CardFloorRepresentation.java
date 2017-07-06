package it.polimi.ingsw.gc12.view.client.gui.representation;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;


public class CardFloorRepresentation  {

    private SimpleIntegerProperty floorNumber;
    private SimpleObjectProperty<Image> path;
    private SimpleObjectProperty<Image> pathWhenTaken;


    private SimpleBooleanProperty taken;

    public CardFloorRepresentation(String url ,int floor, Boolean taken) {
        this.floorNumber = new SimpleIntegerProperty(floor);
        Image image = new Image(url);
        this.path = new SimpleObjectProperty<Image>(image);
        this.taken = new SimpleBooleanProperty(taken);
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
