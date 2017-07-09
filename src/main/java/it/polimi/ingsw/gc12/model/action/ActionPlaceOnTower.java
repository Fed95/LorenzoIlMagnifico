package it.polimi.ingsw.gc12.model.action;

import it.polimi.ingsw.gc12.model.player.familymember.FamilyMember;
import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.model.card.CardDevelopment;
import it.polimi.ingsw.gc12.model.card.CardVenture;
import it.polimi.ingsw.gc12.model.effect.Effect;
import it.polimi.ingsw.gc12.model.effect.EffectFreeAction;
import it.polimi.ingsw.gc12.model.event.EventChooseExchange;
import it.polimi.ingsw.gc12.model.event.EventPickCard;
import it.polimi.ingsw.gc12.model.event.EventPlacementEnded;
import it.polimi.ingsw.gc12.misc.exception.ActionNotAllowedException;
import it.polimi.ingsw.gc12.misc.exception.ActionDeniedException;
import it.polimi.ingsw.gc12.misc.exception.RequiredValueNotSatisfiedException;
import it.polimi.ingsw.gc12.model.board.occupiable.Tower;
import it.polimi.ingsw.gc12.model.board.occupiable.TowerFloor;
import it.polimi.ingsw.gc12.model.player.resource.Resource;
import it.polimi.ingsw.gc12.model.player.resource.ResourceExchange;
import it.polimi.ingsw.gc12.model.player.resource.Servant;

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

    @Override
    public void canBeExecuted(Match match) throws RequiredValueNotSatisfiedException, ActionNotAllowedException{
        if(!tower.canBeOccupiedBy(familyMember))
            throw new ActionNotAllowedException("You already have another coloured FamilyMember on this tower");
        if(towerFloor.isOccupied() && !familyMember.isFriendly())
            throw new ActionNotAllowedException("This TowerFloor is already taken!");
        if(towerFloor.getCard() == null)
            throw new ActionNotAllowedException("There is no card on this floor!");
        if(!towerFloor.isRequiredValueSatisfied(familyMember))
            throw new RequiredValueNotSatisfiedException();
        if(!player.satisfiesPlacementOnTowerFloorRequirements(towerFloor, towerFloor.getCard().getDiscountedRequirements(discounts)))
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
        List<Resource> discountedRequirements = card.getDiscountedRequirements(discounts);

        if(card instanceof CardVenture && ((CardVenture) card).hasChoice()){
            handleRequirementChoice(match, discountedRequirements, (CardVenture) card);
        }else {
            player.removeResources(discountedRequirements);
        }

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

    private void handleRequirementChoice(Match match, List<Resource> requirements, CardVenture card) {
        List<ResourceExchange> exchanges = new ArrayList<>();
        if(requirements.size() > 0)
            exchanges.add(new ResourceExchange(requirements, null));
        exchanges.add(card.getMilitaryExchange());
        EventChooseExchange eventExchange = new EventChooseExchange(player, exchanges);
        match.getActionHandler().update(eventExchange, match);
        match.notifyObserver(eventExchange);
    }

    public void executeImmediateEffects(Match match, Player player, CardDevelopment card) {
        EventPickCard event = new EventPickCard(player, card);
        try {
            match.getEffectHandler().executeEffects(match, event);
        } catch (ActionDeniedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ActionPlaceOnTower)) return false;

        ActionPlaceOnTower that = (ActionPlaceOnTower) o;
        return towerFloor.equals(that.towerFloor) && servant.getValue() == that.getServant().getValue();
    }

    @Override
    public int hashCode() {
        return towerFloor.hashCode();
    }
}
