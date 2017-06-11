package it.polimi.ingsw.gc_12.action;

import it.polimi.ingsw.gc_12.FamilyMember;
import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.card.CardDevelopment;
import it.polimi.ingsw.gc_12.event.Event;
import it.polimi.ingsw.gc_12.event.EventPickCard;
import it.polimi.ingsw.gc_12.event.EventPlaceFamilyMember;
import it.polimi.ingsw.gc_12.exceptions.RequiredValueNotSatisfiedException;
import it.polimi.ingsw.gc_12.occupiables.Occupiable;
import it.polimi.ingsw.gc_12.occupiables.Tower;
import it.polimi.ingsw.gc_12.occupiables.TowerFloor;

public class ActionPlaceOnTower extends ActionPlace {

    protected Tower tower;
    protected TowerFloor towerFloor;

    public ActionPlaceOnTower(FamilyMember familyMember, Tower tower, TowerFloor towerFloor) {
        super(familyMember);
        this.tower = tower;
        this.towerFloor = towerFloor;
    }

    public void canBeExecuted(Player player, Event event) throws RuntimeException, RequiredValueNotSatisfiedException {

        player.getEffectHandler().executeEffects(event);

        System.out.println("effects executed");

        if(this.towerFloor.isOccupied())
            throw new RuntimeException("This TowerFloor is already taken!");
        if(!towerFloor.isRequiredValueSatisfied(familyMember))
            throw new RequiredValueNotSatisfiedException();
        /*TODO: WAITING FOR JSON
        if(!player.hasResources(towerFloor.getCard().getRequirements()))
            throw new RuntimeException("You don't have enough resources to take this card!");
        if(!player.getPersonalBoard().canPlaceCard(player, towerFloor.getCard()))
            throw new RuntimeException("You can't place this card on your board!");
        */
    }

    @Override
    public void start(Match match) throws RuntimeException, RequiredValueNotSatisfiedException {
    	Player player = match.getBoard().getTrackTurnOrder().getCurrentPlayer();
    	familyMember = getRealFamilyMember(match);
    	tower = getRealTower(match);
    	towerFloor = tower.getFloor(towerFloor.getFloorNum());
    	Event event = new EventPlaceFamilyMember(player, towerFloor, familyMember);
        System.out.println("actionplaceontower: event created with placement on " + towerFloor);
        try{
            this.canBeExecuted(player, event);
             if (tower.getFloors().stream().allMatch(floor -> !floor.isOccupied())) { //If no floor of the tower has been occupied yet
                tower.activateMalus();
            }
            
            match.placeFamilyMember(towerFloor, familyMember);

            /*TODO: WAITING FOR JSON
            CardDevelopment card = towerFloor.getCard();
            player.getPersonalBoard().placeCard(card);
            towerFloor.removeCard();
            executeImmediateEffects(player, card);
            */
        }catch(Exception e) {
            player.getEffectHandler().discardEffects(event);
            System.out.println("effects discarded");
            throw e;
        }
    }


    public void executeImmediateEffects(Player player, CardDevelopment card) throws RuntimeException {
        EventPickCard event = new EventPickCard(player, card);
        player.getEffectHandler().executeEffects(event);
    }

    private Tower getRealTower(Match match) {
    	return match.getBoard().getTowerSet().getTower(towerFloor.getType());
    }
}
