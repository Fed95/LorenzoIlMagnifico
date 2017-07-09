package it.polimi.ingsw.gc12.view.client.gui.representation;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;

/**
 * Represent the card leader owned by the player both when is not played and when is played.
 * So this element it ill be binded with two imageView in the GUI, the imageView for the card when is
 * not played visible only to the player and not to the others and the imageView when is not played
 */
public class CardLeaderRepresentation {
    private SimpleObjectProperty<Image> path;
    private SimpleObjectProperty<Image> pathWhenPlayed;
    private SimpleBooleanProperty visible;
    private int id;

    /**
     * constructor
     * @param url image of the card when is not played
     * @param urlWhenPlayed image of the card when is played
     * @param id id of the leader card
     */
    public CardLeaderRepresentation(String url, String urlWhenPlayed, int id){
        Image image = new Image(url);
        Image imagePlayed = new Image(urlWhenPlayed);
        this.path = new SimpleObjectProperty<Image>(image);
        this.pathWhenPlayed = new SimpleObjectProperty<Image>(imagePlayed);
        this.visible = new SimpleBooleanProperty(false);
        this.id = id;
    }

    public Image getPath() {
        return path.get();
    }

    public ObjectProperty<Image> getPathProperty() {
        return path;
    }

    /**
     * set the path of the image card leader is not played, so it will be the card player until
     * is not played , than when played, it will be a transparent card
     * @param path
     */
    public void setPath(Image path) {
        this.path.set(path);
    }

    public Image getPathWhenPlayed() {
        return pathWhenPlayed.get();
    }

    public void setVisible(boolean visible) {
        this.visible.set(visible);
    }
    
    public BooleanProperty getVisibility(){
        return visible;
    }
    
    public ObjectProperty<Image> getPathWhenPlayedProperty() {
        return pathWhenPlayed;
    }

    /**
     * set the path of the card when is not played
     * @param pathWhenPlayed
     */
    public void setPathWhenPlayed(Image pathWhenPlayed) {
        this.pathWhenPlayed.set(pathWhenPlayed);
    }

    public void activate() {
        setPathWhenPlayed(new Image("img/CardLeader/leader_"+id+".jpg"));
        setPath(new Image("img/Card/transparentCard.png"));
        setVisible(true);
    }

    public void discard() {
        setPath(new Image("img/Card/transparentCard.png"));
    }

    public int getId() {
        return id;
    }
}
