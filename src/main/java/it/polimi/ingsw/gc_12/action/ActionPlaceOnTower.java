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
import java.util.ArrayList;
import java.util.List;

import static sun.audio.AudioPlayer.player;

public class ActionPlaceOnTower extends ActionPlace {

    protected Tower tower;
    protected TowerFloor towerFloor;

    public ActionPlaceOnTower(Player player, FamilyMember familyMember, TowerFloor towerFloor, Servant servant) {
        super(player, familyMember, towerFloor, servant);
        this.towerFloor = towerFloor;
    }

    public ActionPlaceOnTower(Player player, FamilyMember familyMember, TowerFloor towerFloor) {
        this(player, familyMember, towerFloor, new Servant(0));
    }

    @Override
    protected void setup(Match match) {
        tower = match.getBoard().getTowerSet().getTower(towerFloor.getType());
    }

    @Override
    public void canBeExecuted(Match match) throws RequiredValueNotSatisfiedException{
        if(towerFloor.isOccupied())
            throw new RuntimeException("This TowerFloor is already taken!");
        if(towerFloor.getCard() == null)
            throw new RuntimeException("There is no card on this floor!");
        if(!towerFloor.isRequiredValueSatisfied(familyMember))
            throw new RequiredValueNotSatisfiedException();
        if(!player.hasResources(towerFloor.getCard().getRequirements()))
            throw new RuntimeException("You don't have enough resources to take this card!");
        if(!player.getPersonalBoard().canPlaceCard(player, towerFloor.getCard()))
            throw new RuntimeException("You can't place this card on your board!");
    }

    @Override
    protected void execute(Match match) {
        if (tower.getFloors().stream().anyMatch(TowerFloor::isOccupied)) { //If no floor of the tower has been occupied yet
            tower.activateMalus();
        }
        match.placeFamilyMember(towerFloor, familyMember);
        CardDevelopment card = towerFloor.getCard();
        player.getPersonalBoard().placeCard(card);
        towerFloor.removeCard();
        executeImmediateEffects(match, player, card);
    }

    public void executeImmediateEffects(Match match, Player player, CardDevelopment card) {
        EventPickCard event = new EventPickCard(player, card);
        match.getEffectHandler().executeEffects(match, event);
    }

    @Override
    public String toString() {
        return "ActionPlaceOnTower{" +
                "player=" + player +
                ", tower=" + tower +
                ", familyMember=" + familyMember +
                ", towerFloor=" + towerFloor +
                ", servant=" + servant +
                '}';
    }
}
