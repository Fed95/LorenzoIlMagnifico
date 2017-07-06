package it.polimi.ingsw.gc12.view.client.gui.representation;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;

/**
 * Created by rugge on 03/07/2017.
 */
public class CardPlayerRepresentation {
    private SimpleObjectProperty<Image> path;
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
}
