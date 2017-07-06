package it.polimi.ingsw.gc12.view.client.gui.representation;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by rugge on 24/06/2017.
 */
public class ResourceRepresentation {
    private StringProperty playerColor;
    private IntegerProperty stone;
    private IntegerProperty servant;
    private IntegerProperty money;
    private IntegerProperty wood;
    private IntegerProperty victoryPoint;
    private IntegerProperty militaryPoint;
    private IntegerProperty faithPoint;
    private IntegerProperty councilPrivilege;
    public ResourceRepresentation(String playerColor, int stone, int money, int servant, int wood, int victory, int military, int faith, int councilPrivilege){
        this.playerColor = new SimpleStringProperty(playerColor);
        this.money = new SimpleIntegerProperty(money);
        this.wood = new SimpleIntegerProperty(wood);
        this.stone = new SimpleIntegerProperty(stone);
        this.servant = new SimpleIntegerProperty(servant);
        this.victoryPoint = new SimpleIntegerProperty(victory);
        this.militaryPoint = new SimpleIntegerProperty(military);
        this.faithPoint = new SimpleIntegerProperty(faith);
        this.councilPrivilege = new SimpleIntegerProperty(councilPrivilege);
    }

    public String getPlayerColor() {
        return playerColor.get();
    }

    public StringProperty getPlayerColorProperty() {
        return playerColor;
    }

    public void setPlayerColor(String playerColor) {
        this.playerColor.set(playerColor);
    }

    public int getStone() {
        return stone.get();
    }

    public IntegerProperty getStoneProperty() {
        return stone;
    }

    public void setStone(int stone) {
        this.stone.set(stone);
    }

    public int getServant() {
        return servant.get();
    }

    public IntegerProperty getServantProperty() {
        return servant;
    }

    public void setServant(int servant) {
        this.servant.set(servant);
    }

    public int getMoney() {
        return money.get();
    }

    public IntegerProperty getMoneyProperty() {
        return money;
    }

    public void setMoney(int money) {
        this.money.set(money);
    }

    public int getWood() {
        return wood.get();
    }

    public IntegerProperty getWoodProperty() {
        return wood;
    }

    public void setWood(int wood) {
        this.wood.set(wood);
    }

    public int getVictoryPoint() {
        return victoryPoint.get();
    }

    public IntegerProperty getVictoryPointProperty() {
        return victoryPoint;
    }

    public void setVictoryPoint(int victoryPoint) {
        this.victoryPoint.set(victoryPoint);
    }

    public int getMilitaryPoint() {
        return militaryPoint.get();
    }

    public IntegerProperty getMilitaryPointProperty() {
        return militaryPoint;
    }

    public void setMilitaryPoint(int militaryPoint) {
        this.militaryPoint.set(militaryPoint);
    }

    public int getFaithPoint() {
        return faithPoint.get();
    }

    public IntegerProperty getFaithPointProperty() {
        return faithPoint;
    }

    public void setFaithPoint(int faithPoint) {
        this.faithPoint.set(faithPoint);
    }

    public int getCouncilPrivilege() {
        return councilPrivilege.get();
    }

    public IntegerProperty getCouncilPrivilegeProperty() {
        return councilPrivilege;
    }

    public void setCouncilPrivilege(int councilPrivilege) {
        this.councilPrivilege.set(councilPrivilege);
    }
}
