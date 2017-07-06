package it.polimi.ingsw.gc12.view.client.gui.representation;

import javafx.application.Platform;
import javafx.beans.property.*;
import javafx.scene.image.Image;

import java.util.Observable;
import java.util.Observer;


public class FamilyMemberRepresentation implements Observer {
    private SimpleIntegerProperty valueProperty;
    private SimpleStringProperty colorsFamilyMemberProperty;
    private SimpleStringProperty colorsPlayerProperty;
    private SimpleBooleanProperty takenProperty;
    private SimpleObjectProperty<Image> pathFamilyMemberImage;

    public FamilyMemberRepresentation(String pathFamily, int value, String colorFamilyMember, String colorPlayer, Boolean taken){
        this.valueProperty = new SimpleIntegerProperty(value);
        this.colorsFamilyMemberProperty = new SimpleStringProperty(colorFamilyMember);
        this.colorsPlayerProperty =  new SimpleStringProperty(colorPlayer);
        this.takenProperty =  new SimpleBooleanProperty(taken);
        Image pathFam = new Image(pathFamily);
        this.pathFamilyMemberImage = new SimpleObjectProperty<Image>(pathFam);
    }
    public void setColorsPlayerProperty(String colorsPlayerProperty) {
        this.colorsPlayerProperty.set(colorsPlayerProperty);
    }

    public void setColorsFamilyMemberProperty(String colorsFamilyMemberProperty) {
        this.colorsFamilyMemberProperty.set(colorsFamilyMemberProperty);
    }

    public void setValueProperty(int valueProperty) {
        this.valueProperty.set(valueProperty);
    }

    public int getValuePropertyInt() {
        return valueProperty.get();
    }

    public String getColorsFamilyMemberPropertyString() {
        return colorsFamilyMemberProperty.get();
    }
    public String getColorsPlayerPropertyString() {
        return colorsPlayerProperty.get();
    }
    public IntegerProperty getValueProperty() {
        return valueProperty;
    }

    public StringProperty getColorsFamilyMemberProperty() {
        return colorsFamilyMemberProperty;
    }
    public StringProperty getColorsPlayerProperty() {
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


    @Override
    public void update(Observable o, Object value) {
        if(value instanceof Integer) {
            Platform.runLater(() -> setValueProperty((int) value));
        }

    }
}
