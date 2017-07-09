package it.polimi.ingsw.gc12.view.client.gui.representation;

import it.polimi.ingsw.gc12.model.player.PlayerColor;
import it.polimi.ingsw.gc12.model.player.familymember.FamilyMember;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.Image;

/**
 * Created by rugge on 09/07/2017.
 */
public class MarketRepresentation {
    private SimpleObjectProperty<Image> path;//path to refresh with fam
    private SimpleStringProperty pathMarket;//saving the image of the market because are 4 different use this for reset

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

    public void setFamilyMember(FamilyMember familyMember, PlayerColor playerColor) {
        path.set(new Image("img/players/"+playerColor.toString()+"/"+playerColor.toString()+"_"+familyMember.getColor().toString()+".png"));
    }
}
