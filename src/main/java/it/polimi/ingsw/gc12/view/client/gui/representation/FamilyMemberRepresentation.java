package it.polimi.ingsw.gc12.view.client.gui.representation;

import it.polimi.ingsw.gc12.model.player.PlayerColor;
import it.polimi.ingsw.gc12.model.player.familymember.FamilyMemberColor;
import javafx.application.Platform;
import javafx.beans.property.*;
import javafx.scene.image.Image;

import java.util.Observable;
import java.util.Observer;

/**
 * Represent the family members of the player with all the characteristics like
 * value, color, player owner, image of the relative family
 */
public class FamilyMemberRepresentation implements Observer {
    private SimpleIntegerProperty value;
    private SimpleObjectProperty<FamilyMemberColor> familyMemberColor;
    private SimpleObjectProperty<PlayerColor> playerColor;
    private SimpleObjectProperty<Image> image;
    private SimpleBooleanProperty visible;

    /**
     * Constructor
     * @param pathFamily path of the Image of the familyMember
     * @param value value of the family member
     * @param colorFamilyMember color of the family member
     * @param colorPlayer color of the player owning the family
     */
    public FamilyMemberRepresentation(String pathFamily, int value, FamilyMemberColor colorFamilyMember, PlayerColor colorPlayer){
        this.value = new SimpleIntegerProperty(value);
        this.familyMemberColor = new SimpleObjectProperty<>(colorFamilyMember);
        this.playerColor = new SimpleObjectProperty<>(colorPlayer);
        Image pathFam = new Image(pathFamily);
        this.image = new SimpleObjectProperty<>(pathFam);
        this.visible =  new SimpleBooleanProperty(true);
    }

    public void setPlayerColor(PlayerColor colorPlayer) {
        this.playerColor.set(colorPlayer);
    }

    public void setFamilyMemberColor(FamilyMemberColor colorFamilyMember) {
        this.familyMemberColor.set(colorFamilyMember);
    }

    public void setValue(int value) {
        this.value.set(value);
    }

    public int getValue() {
        return value.get();
    }

    public FamilyMemberColor getColor() {
        return familyMemberColor.get();
    }

    public PlayerColor getPlayerColor() {
        return playerColor.get();
    }

    public IntegerProperty getValueProperty() {
        return value;
    }

    public Image getImage() {
        return image.get();
    }
    public void setImage(Image image) {
        this.image.set(image);
    }

    public SimpleObjectProperty<Image> getpathFamilyMemberImageProperty() {
        return image;
    }

    public BooleanProperty getVisibility(){
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible.set(visible);
    }

    /**
     * when the value of the dice is updated it will notify this observer that update the valu eof the family
     * @param o
     * @param value value to update
     */
    @Override
    public void update(Observable o, Object value) {
        if(value instanceof Integer) {
            Platform.runLater(() -> setValue((int) value));
        }

    }
}
