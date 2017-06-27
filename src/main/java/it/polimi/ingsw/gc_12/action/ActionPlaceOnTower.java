package it.polimi.ingsw.gc_12.action;

import com.sun.org.apache.regexp.internal.RE;
import it.polimi.ingsw.gc_12.FamilyMember;
import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.card.Card;
import it.polimi.ingsw.gc_12.card.CardDevelopment;
import it.polimi.ingsw.gc_12.effect.Effect;
import it.polimi.ingsw.gc_12.effect.EffectFreeAction;
import it.polimi.ingsw.gc_12.event.EventPickCard;
import it.polimi.ingsw.gc_12.event.EventPlacementEnded;
import it.polimi.ingsw.gc_12.exceptions.ActionNotAllowedException;
import it.polimi.ingsw.gc_12.exceptions.ActionDeniedException;
import it.polimi.ingsw.gc_12.exceptions.RequiredValueNotSatisfiedException;
import it.polimi.ingsw.gc_12.occupiables.Tower;
import it.polimi.ingsw.gc_12.occupiables.TowerFloor;
import it.polimi.ingsw.gc_12.resource.Resource;
import it.polimi.ingsw.gc_12.resource.ResourceType;
import it.polimi.ingsw.gc_12.resource.Servant;

import java.awt.image.RescaleOp;
import java.util.*;

public class ActionPlaceOnTower extends ActionPlace {

    protected Tower tower;
    protected TowerFloor towerFloor;

    public ActionPlaceOnTower(Player player, FamilyMember familyMember, TowerFloor towerFloor, Servant servant, boolean complete) {
        super(player, familyMember, towerFloor, servant, complete);
        this.towerFloor = towerFloor;
    }

    public ActionPlaceOnTower(Player player, FamilyMember familyMember, TowerFloor towerFloor, Servant servant, boolean complete, List<Resource> discounts) {
        this(player, familyMember, towerFloor, servant, complete);
        if(discounts != null)
            this.discounts = discounts;
    }

    public ActionPlaceOnTower(Player player, FamilyMember familyMember, TowerFloor towerFloor, boolean complete) {
        this(player, familyMember, towerFloor, new Servant(0), complete, null);
    }

    public ActionPlaceOnTower(Player player, FamilyMember familyMember, TowerFloor towerFloor) {
        this(player, familyMember, towerFloor, new Servant(0), false, null);
    }


    @Override
    protected void setup(Match match) {
        tower = match.getBoard().getTowerSet().getTower(towerFloor.getType());
    }

    public void setDiscounts(List<Resource> discounts) {
        this.discounts = discounts;
    }

    @Override
    public void canBeExecuted(Match match) throws RequiredValueNotSatisfiedException, ActionNotAllowedException{
        if(towerFloor.isOccupied())
            throw new ActionNotAllowedException("This TowerFloor is already taken!");
        if(towerFloor.getCard() == null)
            throw new ActionNotAllowedException("There is no card on this floor!");
        if(!towerFloor.isRequiredValueSatisfied(familyMember))
            throw new RequiredValueNotSatisfiedException();
        if(!player.hasResources(towerFloor.getCard().getRequirements()))
            throw new ActionNotAllowedException("You don't have enough resources to take this card!");
        if(!player.getPersonalBoard().canPlaceCard(player, towerFloor.getCard()))
            throw new ActionNotAllowedException("You can't place this card on your board!");
    }

    @Override
    protected void execute(Match match) {
        if (!tower.isTaken())
            tower.activateMalus();
        CardDevelopment card = towerFloor.getCard();

        player.removeResources(Collections.singletonList(servant));
        List<Resource> requirements = card.getRequirements();
        if(discounts.size() > 0)
            applyDiscounts(requirements);
        player.removeResources(requirements);
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
        match.getActionHandler().update(event, match);
        match.notifyObserver(event);
    }

    public void executeImmediateEffects(Match match, Player player, CardDevelopment card) {
        EventPickCard event = new EventPickCard(player, card);
        List<Effect> executedEffects = new ArrayList<>();
        try {
            match.getEffectHandler().executeEffects(match, event);
        } catch (ActionDeniedException e) {
            e.printStackTrace();
        }
    }

    private void applyDiscounts(List<Resource> requirements){

        Map<ResourceType, Resource> cardRequirements = new HashMap<>();
        for (Resource requirement : requirements)
            cardRequirements.put(requirement.getType(), requirement);

        for(Resource resource : discounts) {

            ResourceType type = resource.getType();

            if (cardRequirements.containsKey(type)) {
                int currentValue = cardRequirements.get(type).getValue();
                int newValue = (currentValue - resource.getValue() < 0 ? 0 : currentValue - resource.getValue());
                cardRequirements.get(type).setValue(newValue);
            }
        }
    }

}
