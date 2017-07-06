package it.polimi.ingsw.gc12.view.client.gui.representation;

import javafx.application.Platform;
import javafx.beans.property.*;

import java.util.Observable;
import java.util.Observer;


public class FamilyMemberRepresentation implements Observer {
      private SimpleIntegerProperty valueProperty;
    private SimpleStringProperty colorsFamilyMemberProperty;
    private SimpleStringProperty colorsPlayerProperty;
    private SimpleBooleanProperty takenProperty;

    public FamilyMemberRepresentation(int value, String colorFamilyMember, String colorPlayer, Boolean taken){
        this.valueProperty = new SimpleIntegerProperty(value);
        this.colorsFamilyMemberProperty = new SimpleStringProperty(this, "colorsFamilyMemberPropertyInside",colorFamilyMember);
        this.colorsPlayerProperty =  new SimpleStringProperty(this, "colorePlayerPropertyInside",colorPlayer);
        this.takenProperty =  new SimpleBooleanProperty(taken);
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

    @Override
    public void update(Observable o, Object value) {
        if(value instanceof Integer) {
            Platform.runLater(() -> setValueProperty((int) value));
        }

    }
}
