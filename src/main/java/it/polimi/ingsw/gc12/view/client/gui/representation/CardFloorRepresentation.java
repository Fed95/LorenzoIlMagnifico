package it.polimi.ingsw.gc12.view.client.gui.representation;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;


public class CardFloorRepresentation  {

    private SimpleIntegerProperty floorNumber;
    private SimpleObjectProperty<Image> pathCard;
    private SimpleObjectProperty<Image> pathFloor;

    private SimpleBooleanProperty taken;

    public CardFloorRepresentation(String urlCard ,String urlFloor, int floor, Boolean taken) {
        this.floorNumber = new SimpleIntegerProperty(floor);
        Image imageCard = new Image(urlCard);
        this.pathCard = new SimpleObjectProperty<Image>(imageCard);
        this.taken = new SimpleBooleanProperty(taken);
        Image imageFloor = new Image(urlFloor);
        this.pathFloor = new SimpleObjectProperty<Image>(imageFloor);
    }

    public int getFloorNumber() {
        return floorNumber.get();
    }

    public ObjectProperty<Image> getPathProperty() {
        return pathCard;
    }

    public void removeCard() {
        setPathCard(new Image("img/Card/transparentCard.png"));
    }

    public void setPathCard(Image pathCard) {
        this.pathCard.set(pathCard);
    }

    public Image getPathFloor() {
        return pathFloor.get();
    }

    public SimpleObjectProperty<Image> getPathFloorProperty() {
        return pathFloor;
    }

    public void setPathFloor(Image pathFloor) {
        this.pathFloor.set(pathFloor);
    }
}
