package it.polimi.ingsw.gc_12.java_fx;

import it.polimi.ingsw.gc_12.excommunication.ExcommunicationTile;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;


/**
 * Created by rugge on 29/06/2017.
 */
public class ExcommunicationTileRepresentation {
    private SimpleIntegerProperty period;
    private SimpleObjectProperty<Image> path;

    public ExcommunicationTileRepresentation(int period, String path){
        this.period = new SimpleIntegerProperty(period);
        Image image = new Image(path);
        this.path = new SimpleObjectProperty<Image>(image);
    }

    public int getPeriod() {
        return period.get();
    }

    public SimpleIntegerProperty periodProperty() {
        return period;
    }

    public void setPeriod(int period) {
        this.period.set(period);
    }

    public Image getPath() {
        return path.get();
    }

    public SimpleObjectProperty<Image> getpathProperty() {
        return path;
    }

    public void setPath(Image path) {
        this.path.set(path);
    }
}
