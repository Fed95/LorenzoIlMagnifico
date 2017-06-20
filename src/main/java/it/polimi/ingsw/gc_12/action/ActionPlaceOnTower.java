package it.polimi.ingsw.gc_12.action;

import it.polimi.ingsw.gc_12.FamilyMember;
import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.card.CardDevelopment;
import it.polimi.ingsw.gc_12.effect.Effect;
import it.polimi.ingsw.gc_12.effect.EffectFreeAction;
import it.polimi.ingsw.gc_12.event.EventPlacementEnded;
import it.polimi.ingsw.gc_12.event.EventPickCard;
import it.polimi.ingsw.gc_12.exceptions.RequiredValueNotSatisfiedException;
import it.polimi.ingsw.gc_12.occupiables.Tower;
import it.polimi.ingsw.gc_12.occupiables.TowerFloor;
import it.polimi.ingsw.gc_12.resource.Servant;

import java.util.List;

public class ActionPlaceOnTower extends ActionPlace {

    protected Tower tower;
    protected TowerFloor towerFloor;

    public ActionPlaceOnTower(Player player, FamilyMember familyMember, TowerFloor towerFloor, Servant servant, boolean complete) {
        super(player, familyMember, towerFloor, servant, complete);
        this.towerFloor = towerFloor;
    }

    public ActionPlaceOnTower(Player player, FamilyMember familyMember, TowerFloor towerFloor) {
        this(player, familyMember, towerFloor, new Servant(0), false);
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
        if (!tower.isTaken())
            tower.activateMalus();
        CardDevelopment card = towerFloor.getCard();
        player.removeResources(card.getRequirements());
        player.getPersonalBoard().placeCard(card);
        match.placeFamilyMember(towerFloor, familyMember);
        executeImmediateEffects(match, player, card);
        towerFloor.removeCard();
        List<Effect> effects = card.getEffects();
        for(Effect effect: effects) {
            if(effect instanceof EffectFreeAction)
                return;
        }
        EventPlacementEnded event = new EventPlacementEnded(player);
        match.getActionHandler().update(event);
        match.notifyObserver(event);
    }

    public void executeImmediateEffects(Match match, Player player, CardDevelopment card) {
        EventPickCard event = new EventPickCard(player, card);
        match.getEffectHandler().executeEffects(match, event);
    }

}
