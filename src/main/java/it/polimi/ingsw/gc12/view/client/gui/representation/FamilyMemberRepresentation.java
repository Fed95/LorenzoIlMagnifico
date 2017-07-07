package it.polimi.ingsw.gc12.view.client.gui.representation;

import it.polimi.ingsw.gc12.model.player.PlayerColor;
import it.polimi.ingsw.gc12.model.player.familymember.FamilyMember;
import it.polimi.ingsw.gc12.model.player.familymember.FamilyMemberColor;
import javafx.application.Platform;
import javafx.beans.property.*;
import javafx.scene.image.Image;

import java.util.Observable;
import java.util.Observer;


public class FamilyMemberRepresentation implements Observer {
    private SimpleIntegerProperty valueProperty;
    private SimpleObjectProperty<FamilyMemberColor> colorsFamilyMemberProperty;
    private SimpleObjectProperty<PlayerColor> colorsPlayerProperty;
    private SimpleObjectProperty<Image> pathFamilyMemberImage;
    private boolean used;

    public FamilyMemberRepresentation(String pathFamily, int value, FamilyMemberColor colorFamilyMember, PlayerColor colorPlayer, boolean used){
        this.valueProperty = new SimpleIntegerProperty(value);
        this.colorsFamilyMemberProperty = new SimpleObjectProperty<>(colorFamilyMember);
        this.colorsPlayerProperty = new SimpleObjectProperty<>(colorPlayer);
        Image pathFam = new Image(pathFamily);
        this.pathFamilyMemberImage = new SimpleObjectProperty<>(pathFam);
        this.used = used;
    }
    public void setColorsPlayerProperty(PlayerColor colorPlayer) {
        this.colorsPlayerProperty.set(colorPlayer);
    }

    public void setColorsFamilyMemberProperty(FamilyMemberColor colorFamilyMember) {
        this.colorsFamilyMemberProperty.set(colorFamilyMember);
    }

    public void setValueProperty(int valueProperty) {
        this.valueProperty.set(valueProperty);
    }

    public int getValuePropertyInt() {
        return valueProperty.get();
    }

    public FamilyMemberColor getColor() {
        return colorsFamilyMemberProperty.get();
    }

    public PlayerColor getPlayerColor() {
        return colorsPlayerProperty.get();
    }
    
    public IntegerProperty getValueProperty() {
        return valueProperty;
    }

    public ObjectProperty<FamilyMemberColor> getColorsFamilyMemberProperty() {
        return colorsFamilyMemberProperty;
    }
    public ObjectProperty<PlayerColor> getColorsPlayerProperty() {
        return colorsPlayerProperty;
    }

    public Image getPathFamilyMemberImage() {
        return pathFamilyMemberImage.get();
    }
    public void setPathFamilyMemberImage(Image pathFamilyMemberImage) {
        this.pathFamilyMemberImage.set(pathFamilyMemberImage);
    }

    public SimpleObjectProperty<Image> getpathFamilyMemberImageProperty() {
        return pathFamilyMemberImage;
    }

    public boolean isUsed() {
        return used;
    }

    @Override
    public void update(Observable o, Object value) {
        if(value instanceof Integer) {
            Platform.runLater(() -> setValueProperty((int) value));
        }

    }
}
