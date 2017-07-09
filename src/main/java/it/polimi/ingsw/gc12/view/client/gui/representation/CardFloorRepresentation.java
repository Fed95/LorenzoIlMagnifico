package it.polimi.ingsw.gc12.view.client.gui.representation;

import it.polimi.ingsw.gc12.model.player.PlayerColor;
import it.polimi.ingsw.gc12.model.player.familymember.FamilyMember;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;

/**
 * It represent the floor of the tower in all is function both for showing card on the tower and for handling the click on a tower floor
 * showing the floor busy.
 * So this elements is binded with two elements on the GUI, the imageView for showing card and the imageView of the floor
 */
public class CardFloorRepresentation  {

    private SimpleIntegerProperty floorNumber;
    private SimpleObjectProperty<Image> pathCard;
    private SimpleObjectProperty<Image> pathFloor;
    private final int floorCount;
    private SimpleBooleanProperty taken;

    /**
     * constructor
     * @param urlCard path of the card on the tower
     * @param urlFloor path for showing the floor busy
     * @param floor number of the floor considering the single tower represented (0-3)
     * @param taken boolean for know if the floor is busy
     * @param floorCount number of the floor considering all the floor tower (0-15)
     */
    public CardFloorRepresentation(String urlCard ,String urlFloor, int floor, Boolean taken, int floorCount) {
        this.floorNumber = new SimpleIntegerProperty(floor);
        Image imageCard = new Image(urlCard);
        this.pathCard = new SimpleObjectProperty<Image>(imageCard);
        this.taken = new SimpleBooleanProperty(taken);
        Image imageFloor = new Image(urlFloor);
        this.pathFloor = new SimpleObjectProperty<Image>(imageFloor);
        this.floorCount = floorCount;
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

    /**
     * It set the object family member on the floor and call the method for set the image of the  family member ont he floor
     * @param familyMember family memner to set
     * @param playerColor color of the player
     */
    public void setFamilyMember(FamilyMember familyMember, PlayerColor playerColor) {
        setPathFloor(new Image("img/players/"+playerColor.toString()+"/"+playerColor.toString()+"_"+familyMember.getColor().toString()+".png"));
    }

    public SimpleObjectProperty<Image> getPathFloorProperty() {
        return pathFloor;
    }

    /**
     * It set the image of the family member on the floor showing it occupied
     * @param pathFloor
     */
    public void setPathFloor(Image pathFloor) {
        this.pathFloor.set(pathFloor);
    }

    public int getFloorCount() {
        return floorCount;
    }
}
