package it.polimi.ingsw.gc_12.action;

import it.polimi.ingsw.gc_12.FamilyMember;

import it.polimi.ingsw.gc_12.card.CardDevelopment;
import it.polimi.ingsw.gc_12.event.Event;
import it.polimi.ingsw.gc_12.event.EventPickCard;
import it.polimi.ingsw.gc_12.event.EventPlaceFamilyMember;
import it.polimi.ingsw.gc_12.exceptions.RequiredValueNotSatisfiedException;
import it.polimi.ingsw.gc_12.occupiables.Tower;
import it.polimi.ingsw.gc_12.occupiables.TowerFloor;

public class ActionPlaceOnTower extends ActionPlace {

    protected Tower tower;
    protected TowerFloor towerFloor;

    public ActionPlaceOnTower(FamilyMember familyMember, Tower tower, TowerFloor towerFloor) {
        super(familyMember.getOwner(), familyMember);
        this.tower = tower;
        this.towerFloor = towerFloor;
    }

    public void canBeExecuted(Event event) throws RuntimeException, RequiredValueNotSatisfiedException {

        player.getEffectHandler().executeEffects(event);

        System.out.println("effects executed");

        if(this.towerFloor.isOccupied())
            throw new RuntimeException("This TowerFloor is already taken!");
        if(!towerFloor.isRequiredValueSatisfied(familyMember))
            throw new RequiredValueNotSatisfiedException();
        try {
            //Throws multiple exceptions
            player.hasResources(towerFloor.getCard().getRequirements());
            //Throws multiple exceptions
            //actually places the card
            player.getPersonalBoard().canPlaceCard(player, towerFloor.getCard());
        }catch(NullPointerException e){
           //TODO: waiting for Json cards
           // throw new RuntimeException("There is no card on this floor!");
        }
    }

    @Override
    public void start() throws RuntimeException, RequiredValueNotSatisfiedException {

        Event event = new EventPlaceFamilyMember(this.player, towerFloor, familyMember);
        //System.out.println("actionplaceontower: event created with placement on " + towerFloor);
        try{
            this.canBeExecuted(event);
            if (tower.getFloors().stream().allMatch(floor -> !floor.isOccupied())) { //If no floor of the tower has been occupied yet
                tower.activateMalus();
            }
            towerFloor.placeFamilyMember(familyMember);
            familyMember.setBusy(true);
            CardDevelopment card = towerFloor.getCard();
            player.getPersonalBoard().placeCard(card);
            towerFloor.removeCard();
            executeImmediateEffects(card);
        }catch(Exception e) {
            player.getEffectHandler().discardEffects(event);
            System.out.println("effects discarded");
            throw e;
        }
    }


    public void executeImmediateEffects(CardDevelopment card) throws RuntimeException {
        EventPickCard event = new EventPickCard(player, card);
        player.getEffectHandler().executeEffects(event);
    }
}
