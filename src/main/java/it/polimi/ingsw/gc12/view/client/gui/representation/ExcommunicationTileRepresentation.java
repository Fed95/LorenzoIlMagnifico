package it.polimi.ingsw.gc12.view.client.gui.representation;

import it.polimi.ingsw.gc12.model.player.PlayerColor;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;

import javafx.scene.paint.Paint;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by rugge on 29/06/2017.
 */
public class ExcommunicationTileRepresentation {
    private SimpleIntegerProperty period;
    private SimpleObjectProperty<Image> path;

    private SimpleObjectProperty<Paint> playerBlue = new SimpleObjectProperty<Paint>();
    private SimpleObjectProperty<Paint> playerGreen = new SimpleObjectProperty<Paint>();
    private SimpleObjectProperty<Paint> playerRed = new SimpleObjectProperty<Paint>();
    private SimpleObjectProperty<Paint> playerYellow = new SimpleObjectProperty<Paint>();

    private Map<PlayerColor, SimpleObjectProperty<Paint>> retriveColorProperty = new HashMap<>();
    public ExcommunicationTileRepresentation(int period, String path){
        this.period = new SimpleIntegerProperty(period);
        Image image = new Image(path);
        this.path = new SimpleObjectProperty<Image>(image);
        setColor();
        setRetrive();
    }
    private void setColor(){
        PlayerColorReal playerColorReal = new PlayerColorReal(1);
        playerBlue.set(playerColorReal.getBlue());
        playerGreen.set(playerColorReal.getGreen());
        playerRed.set(playerColorReal.getRed());
        playerYellow.set(playerColorReal.getYellow());
    }
    private void setRetrive(){
        retriveColorProperty.put(PlayerColor.BLUE, playerBlue);
        retriveColorProperty.put(PlayerColor.GREEN, playerGreen);
        retriveColorProperty.put(PlayerColor.RED, playerRed);
        retriveColorProperty.put(PlayerColor.YELLOW, playerYellow);
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

    public Map<PlayerColor, SimpleObjectProperty<Paint>> getRetriveColorProperty() {
        return retriveColorProperty;
    }

    public void setPlayerBlue(Paint playerBlue) {
        this.playerBlue.set(playerBlue);
    }

    public void setPlayerGreen(Paint playerGreen) {
        this.playerGreen.set(playerGreen);
    }

    public void setPlayerRed(Paint playerRed) {
        this.playerRed.set(playerRed);
    }

    public void setPlayerYellow(Paint playerYellow) {
        this.playerYellow.set(playerYellow);
    }
}
