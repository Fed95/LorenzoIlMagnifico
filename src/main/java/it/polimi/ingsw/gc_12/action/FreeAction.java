package it.polimi.ingsw.gc_12.action;

import it.polimi.ingsw.gc_12.FamilyMember;
import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.event.Event;
import it.polimi.ingsw.gc_12.event.EventPlaceFamilyMember;
import it.polimi.ingsw.gc_12.exceptions.RequiredValueNotSatisfiedException;
import it.polimi.ingsw.gc_12.occupiables.Tower;
import it.polimi.ingsw.gc_12.occupiables.TowerFloor;

public class FreeAction extends ActionPlaceOnTower {

    public FreeAction(FamilyMember familyMember, Tower tower, TowerFloor towerFloor) {
        super(familyMember, tower, towerFloor);
    }

    @Override
    public void start(Match match) throws RuntimeException, RequiredValueNotSatisfiedException {
    	Player player = match.getBoard().getTrackTurnOrder().getCurrentPlayer();
        Event event = new EventPlaceFamilyMember(player, towerFloor, familyMember);
        //System.out.println("actionplaceontower: event created with placement on " + towerFloor);
        try{
            this.canBeExecuted(player, event);
            if (tower.getFloors().stream().allMatch(floor -> !floor.isOccupied())) { //If no floor of the tower has been occupied yet
                tower.activateMalus();
            }
        }catch(Exception e) {
            player.getEffectHandler().discardEffects(event);
            System.out.println("effects discarded");
            throw e;
        }
    }
}
