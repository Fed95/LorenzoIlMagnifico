package it.polimi.ingsw.gc_12.action;

import it.polimi.ingsw.gc_12.FamilyMember;
import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.card.CardDevelopment;
import it.polimi.ingsw.gc_12.effect.Effect;
import it.polimi.ingsw.gc_12.event.Event;
import it.polimi.ingsw.gc_12.event.EventPickCard;
import it.polimi.ingsw.gc_12.event.EventPlaceFamilyMember;
import it.polimi.ingsw.gc_12.event.EventRequiredValueNotSatisfied;
import it.polimi.ingsw.gc_12.exceptions.RequiredValueNotSatisfiedException;
import it.polimi.ingsw.gc_12.occupiables.Tower;
import it.polimi.ingsw.gc_12.occupiables.TowerFloor;
import it.polimi.ingsw.gc_12.resource.Servant;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.List;

import static sun.audio.AudioPlayer.player;

public class ActionPlaceOnTower extends ActionPlace {

    protected Tower tower;
    protected TowerFloor towerFloor;

    public ActionPlaceOnTower(FamilyMember familyMember, Servant servant, TowerFloor towerFloor) {
        super(familyMember, servant);
        this.towerFloor = towerFloor;
    }

    public ActionPlaceOnTower(FamilyMember familyMember, TowerFloor towerFloor) {
        this(familyMember, new Servant(0), towerFloor);
    }

    public void canBeExecuted(Player player) throws RuntimeException, RequiredValueNotSatisfiedException{

        if(this.towerFloor.isOccupied())
            throw new RuntimeException("This TowerFloor is already taken!");
        if(!towerFloor.isRequiredValueSatisfied(familyMember))
            throw new RequiredValueNotSatisfiedException();
        if(!player.hasResources(towerFloor.getCard().getRequirements()))
            throw new RuntimeException("You don't have enough resources to take this card!");
        if(!player.getPersonalBoard().canPlaceCard(player, towerFloor.getCard()))
            throw new RuntimeException("You can't place this card on your board!");
    }

    @Override

    public void start(Match match) throws RuntimeException, RequiredValueNotSatisfiedException, IOException {
    	Player player = match.getBoard().getTrackTurnOrder().getCurrentPlayer();
    	familyMember = getRealFamilyMember(match);
    	tower = getRealTower(match);
    	towerFloor = tower.getFloor(towerFloor.getFloorNum());
    	familyMember.setValue(familyMember.getValue()+getServants(match));
    	Event event = new EventPlaceFamilyMember(player, towerFloor, familyMember);

    	//Can throw exceptions (in which case effects are discarded directly in EffectHandler)
        List<Effect> executedEffects = player.getEffectHandler().executeEffects(match, event);

        try{
            this.canBeExecuted(player);
            if (tower.getFloors().stream().anyMatch(floor -> floor.isOccupied())) { //If no floor of the tower has been occupied yet
                tower.activateMalus();
            }
            match.placeFamilyMember(towerFloor, familyMember);
            CardDevelopment card = towerFloor.getCard();
            player.getPersonalBoard().placeCard(card);
            towerFloor.removeCard();
            executeImmediateEffects(match, player, card);
        }
        catch (RequiredValueNotSatisfiedException e) {
            Event eventException = new EventRequiredValueNotSatisfied(player, towerFloor, familyMember);
            match.notifyObserver(eventException);
        }
        catch(Exception e) {
            player.getEffectHandler().discardEffects(executedEffects, event);
            System.out.println("Effects discarded due to " + e);
            throw e;
        }
    }


    public void executeImmediateEffects(Match match, Player player, CardDevelopment card) throws RuntimeException, IOException {
        EventPickCard event = new EventPickCard(player, card);
        player.getEffectHandler().executeEffects(match, event);
    }

    private Tower getRealTower(Match match) {
    	return match.getBoard().getTowerSet().getTower(towerFloor.getType());
    }
}
