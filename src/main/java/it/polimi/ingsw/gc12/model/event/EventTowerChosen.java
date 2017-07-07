package it.polimi.ingsw.gc12.model.event;

import it.polimi.ingsw.gc12.controller.ActionHandler;
import it.polimi.ingsw.gc12.model.action.*;
import it.polimi.ingsw.gc12.model.player.familymember.FamilyMember;
import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.view.client.gui.MainBoard;
import it.polimi.ingsw.gc12.model.board.occupiable.Tower;
import it.polimi.ingsw.gc12.model.board.occupiable.TowerFloor;
import javafx.application.Platform;

import java.util.ArrayList;
import java.util.Collections;


public class EventTowerChosen extends Event implements EventView{

    private Tower tower;
    private FamilyMember familyMember;

    public EventTowerChosen(Player player, FamilyMember familyMember, Tower tower) {
        super(player);
        this.tower = tower;
        this.familyMember = familyMember;
    }

    @Override
    public void setActions(Match match) {
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
