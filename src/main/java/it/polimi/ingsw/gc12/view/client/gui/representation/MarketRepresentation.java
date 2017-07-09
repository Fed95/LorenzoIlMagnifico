package it.polimi.ingsw.gc12.view.client.gui.representation;

import it.polimi.ingsw.gc12.model.player.PlayerColor;
import it.polimi.ingsw.gc12.model.player.familymember.FamilyMember;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.Image;

/**
 * Represent the market on the GUI.
 */
public class MarketRepresentation {
    private SimpleObjectProperty<Image> path;//path to refresh with fam
    private SimpleStringProperty pathMarket;//saving the image of the market because are 4 different use this for reset

    /**
     * Contructor
     * @param path path of the image of the market
     */
    public MarketRepresentation(String path){
        Image market = new Image(path);
        this.path = new SimpleObjectProperty<>(market);
        this.pathMarket = new SimpleStringProperty(path);
    }

    public void setPath(Image path) {
        this.path.set(path);
    }

    public void resetMarket(){
        Image market = new Image(pathMarket.toString());
        path.set(market);
    }

    public ObjectProperty<Image> getPath(){
        return path;
    }

    /**
     * Method that put the family member on the market
     * @param familyMember family memer to put into
     * @param playerColor player color owning the family
     */
    public void setFamilyMember(FamilyMember familyMember, PlayerColor playerColor) {
        path.set(new Image("img/players/"+playerColor.toString()+"/"+playerColor.toString()+"_"+familyMember.getColor().toString()+".png"));
    }
}
