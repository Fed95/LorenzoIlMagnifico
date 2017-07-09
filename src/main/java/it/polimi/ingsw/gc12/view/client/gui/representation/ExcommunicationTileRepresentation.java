package it.polimi.ingsw.gc12.view.client.gui.representation;

import it.polimi.ingsw.gc12.model.player.PlayerColor;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;

import javafx.scene.paint.Paint;

import java.util.HashMap;
import java.util.Map;



/**
 * It represent the excomunication tiles both for excommunication image and for
 * excommunication pawns relative to the excommunication
 */
public class ExcommunicationTileRepresentation {
    private SimpleIntegerProperty period;
    private SimpleObjectProperty<Image> path;

    private SimpleObjectProperty<Paint> playerBlue = new SimpleObjectProperty<Paint>();
    private SimpleObjectProperty<Paint> playerGreen = new SimpleObjectProperty<Paint>();
    private SimpleObjectProperty<Paint> playerRed = new SimpleObjectProperty<Paint>();
    private SimpleObjectProperty<Paint> playerYellow = new SimpleObjectProperty<Paint>();
    private Map<PlayerColor, SimpleObjectProperty<Paint>> retriveColorProperty = new HashMap<>();

    /**
     * Constructor
     * @param period period of the excomuniction tile
     * @param path path of the excomunication tile to show
     */
    public ExcommunicationTileRepresentation(int period, String path){
        this.period = new SimpleIntegerProperty(period);
        Image image = new Image(path);
        this.path = new SimpleObjectProperty<>(image);
        setColor();
        setRetrive();
    }

    /**
     * Set the color to the pawns
     */
    private void setColor(){
        PlayerColorReal playerColorReal = new PlayerColorReal(0);
        playerBlue.set(playerColorReal.getBlue());
        playerGreen.set(playerColorReal.getGreen());
        playerRed.set(playerColorReal.getRed());
        playerYellow.set(playerColorReal.getYellow());
    }

    /**
     * create the list with the colors
     */
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

    /**
     * show the right pawn activated
     * @param playerColor color for activate the pawn
     */
    public void showExcommunication(PlayerColor playerColor) {
        PlayerColorReal playerColorReal = new PlayerColorReal(1);
        if(playerColor.equals(PlayerColor.BLUE))
            this.playerBlue.set(playerColorReal.getBlue());
        else if(playerColor.equals(PlayerColor.GREEN))
            this.playerGreen.set(playerColorReal.getGreen());
        else if(playerColor.equals(PlayerColor.RED))
            this.playerRed.set(playerColorReal.getRed());
        else if(playerColor.equals(PlayerColor.YELLOW))
            this.playerYellow.set(playerColorReal.getYellow());
    }
}
