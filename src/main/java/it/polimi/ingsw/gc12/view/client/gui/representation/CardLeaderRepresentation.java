package it.polimi.ingsw.gc12.view.client.gui.representation;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;

/**
 * Created by rugge on 03/07/2017.
 */
public class CardLeaderRepresentation {
    private SimpleObjectProperty<Image> path;
    private SimpleObjectProperty<Image> pathWhenPlayed;
    public CardLeaderRepresentation(String url, String urlWhenPlayed){
        Image image = new Image(url);
        Image imagePlayed = new Image(urlWhenPlayed);
        this.path = new SimpleObjectProperty<Image>(image);
        this.pathWhenPlayed = new SimpleObjectProperty<Image>(imagePlayed);
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

    public Image getPathWhenPlayed() {
        return pathWhenPlayed.get();
    }

    public ObjectProperty<Image> getPathWhenPlayedProperty() {
        return pathWhenPlayed;
    }

    public void setPathWhenPlayed(Image pathWhenPlayed) {
        this.pathWhenPlayed.set(pathWhenPlayed);
    }
}
