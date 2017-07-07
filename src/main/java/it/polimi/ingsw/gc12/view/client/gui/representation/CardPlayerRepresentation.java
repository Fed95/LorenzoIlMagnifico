package it.polimi.ingsw.gc12.view.client.gui.representation;

import it.polimi.ingsw.gc12.model.card.CardDevelopment;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;

public class CardPlayerRepresentation {
    private SimpleObjectProperty<Image> path;
    private boolean occupied;
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

    public void placeCard(CardDevelopment card) {
        if(occupied)
            return;
        setPath(new Image("img/Card/card_"+card.getId()+".png"));
        occupied = true;
    }

    public boolean isOccupied() {
        return occupied;
    }
}
