package it.polimi.ingsw.gc12.view.client.gui.representation;

import it.polimi.ingsw.gc12.model.card.CardDevelopment;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;

/**
 * It represents the card slot owned by the player, it's binded with the imageView in the GUI
 */

public class CardPlayerRepresentation {
    private SimpleObjectProperty<Image> path;
    private boolean occupied;
    /**Constructor:
     * @param url the path of the image representing the card
     */
    public CardPlayerRepresentation(String url){
        Image image = new Image(url);
        this.path = new SimpleObjectProperty<Image>(image);
    }

    public Image getPath() {
        return path.get();
    }

    public ObjectProperty<Image> getPathProperty() {
        return path;
    }

    public void setPath(Image path) {
        this.path.set(path);
    }

    /**
     * It takes a card and set it into the path
     * @param card the object card
     */
    public void placeCard(CardDevelopment card) {
        if(occupied)
            return;
        setPath(new Image("img/Card/card_"+card.getId()+".png"));
        occupied = true;
    }

    /**
     *it says if a card player slot is occupied or not
     * @return boolean
     */
    public boolean isOccupied() {
        return occupied;
    }
}
