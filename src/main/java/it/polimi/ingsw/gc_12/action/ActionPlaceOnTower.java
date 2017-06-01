package it.polimi.ingsw.gc_12.action;

import it.polimi.ingsw.gc_12.FamilyMember;

import it.polimi.ingsw.gc_12.event.Event;
import it.polimi.ingsw.gc_12.event.EventPlaceFamilyMember;
import it.polimi.ingsw.gc_12.occupiables.Tower;
import it.polimi.ingsw.gc_12.occupiables.TowerFloor;

public class ActionPlaceOnTower extends ActionPlace {

    private FamilyMember familyMember;
    private Tower tower;
    private TowerFloor towerFloor;

    public ActionPlaceOnTower(FamilyMember familyMember, Tower tower, TowerFloor towerFloor) {
        super(familyMember.getOwner(), familyMember);
        this.tower = tower;
        this.familyMember = familyMember;
        this.towerFloor = towerFloor;
    }

    public boolean canBeExecuted(Event event) throws RuntimeException {

        player.getEffectHandler().executeEffects(event);

        if(this.towerFloor.isOccupied())
            throw new RuntimeException("This TowerFloor is already taken!");
        if(!towerFloor.isRequiredValueSatisfied(familyMember))
            throw new RuntimeException("This FamilyMember does not satisfy the required value for this placement!");

        /*TODO: this section needs the cards to be loaded from Json first
        if(!towerFloor.getCard().equals(null)) {
            //Throws multiple exceptions
            player.hasResources(towerFloor.getCard().getRequirements());
            //Throws multiple exceptions
           player.getPersonalBoard().canPlaceCard(player, towerFloor.getCard());
        }
        */
        return true;
    }

    @Override
    public void start() throws RuntimeException {
        Event event = new EventPlaceFamilyMember(this.player, towerFloor, familyMember);

        if(canBeExecuted(event)) {
            if (tower.getFloors().stream().allMatch(floor -> !floor.isOccupied())) { //If no floor of the tower has been occupied yet
                tower.activateMalus();
            }
            familyMember.setBusy(true);
            towerFloor.placeFamilyMember(familyMember);
        }else
            player.getEffectHandler().discardEffects(event);
    }
}
