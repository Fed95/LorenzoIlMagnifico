package it.polimi.ingsw.gc_12.java_fx;

import it.polimi.ingsw.gc_12.server.observer.Observable;
import javafx.beans.property.*;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

/**
 * Created by rugge on 20/06/2017.
 */
public class FamilyMemberRepresentation {
      private SimpleIntegerProperty valueProperty;
    private SimpleStringProperty colorsFamilyMemberProperty;
    private SimpleStringProperty colorsPlayerProperty;
    private SimpleBooleanProperty takenProperty;
    //l'obseravlbelist si trova in matchistance

    public FamilyMemberRepresentation(int value, String colorFamilyMember, String colorPlayer, Boolean taken){
        this.valueProperty = new SimpleIntegerProperty(value);
        this.colorsFamilyMemberProperty = new SimpleStringProperty(this, "colorsFamilyMemberPropertyInside",colorFamilyMember);
        this.colorsPlayerProperty =  new SimpleStringProperty(this, "colorePlayerPropertyInside",colorPlayer);
        this.takenProperty =  new SimpleBooleanProperty(taken);
    }


    public SimpleStringProperty getColorsPlayerProperty() {
        return colorsPlayerProperty;
    }

    public SimpleIntegerProperty getValueProperty() {
        return valueProperty;
    }

    public SimpleStringProperty getColorsFamilyMemberProperty() {
        return colorsFamilyMemberProperty;
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

    public int valuePropertyProperty() {
        return valueProperty.get();
    }

    public String colorsFamilyMemberPropertyProperty() {
        return colorsFamilyMemberProperty.get();
    }

    public String colorsPlayerPropertyProperty() {
        return colorsPlayerProperty.get();
    }
}
