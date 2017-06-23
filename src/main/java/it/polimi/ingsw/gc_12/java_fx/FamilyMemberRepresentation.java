package it.polimi.ingsw.gc_12.java_fx;

import javafx.beans.property.*;


/**
 * Created by rugge on 20/06/2017.
 */
public class FamilyMemberRepresentation {
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
}
