package it.polimi.ingsw.gc_12.event;

import it.polimi.ingsw.gc_12.FamilyMember;
import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.action.*;
import it.polimi.ingsw.gc_12.effect.EffectProvider;
import it.polimi.ingsw.gc_12.java_fx.MainBoard;
import it.polimi.ingsw.gc_12.occupiables.Tower;
import it.polimi.ingsw.gc_12.occupiables.TowerFloor;
import javafx.application.Platform;
import javafx.scene.control.Alert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class EventTowerChosen extends Event implements EventView{

    private Tower tower;
    private FamilyMember familyMember;

    public EventTowerChosen(Player player, FamilyMember familyMember, Tower tower) {
        super(player);
        this.tower = tower;
        this.familyMember = familyMember;
    }

    @Override
    public void setActions(ActionHandler actionHandler, Match match) {
        actions = new ArrayList<>();
        for(TowerFloor towerFloor : tower.getFloors()){
            ActionPlace action = ActionFactory.createActionPlace(player, familyMember, towerFloor);
            if(action.isValid(match))
                actions.add(new ActionPlaceOnTower(player, familyMember, towerFloor));
        }
        Collections.reverse(actions);
        actions.add(new DiscardAction(player));
    }

    public Tower getTower() {
        return tower;
    }

    public FamilyMember getFamilyMember() {
        return familyMember;
    }

    @Override
    public String toString() {
        return player.getName() + " is viewing the " + tower.getType() + " tower.";
    }

    @Override
    public void executeViewSide(MainBoard view) {
        Platform.runLater(() -> {
            view.getControllerMainBoard().sendAction();
        });
    }
}
