package it.polimi.ingsw.gc12.model.action;

import it.polimi.ingsw.gc12.model.event.EventDiscardLeaderCard;
import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.model.card.LeaderCard;
import it.polimi.ingsw.gc12.model.event.EventCouncilPrivilegeReceived;
import it.polimi.ingsw.gc12.model.event.EventStartTurn;
import it.polimi.ingsw.gc12.model.player.resource.CouncilPrivilege;

/**
 * This Action allows the player to discard a LeaderCard in exchange for a CouncilPrivilege
 * When executed, an EventCouncilPrivilegeReceived is generated, allowing the player to choose from the relative bonuses
 * that the privilege offers.
 * An EventDiscardLeaderCard is then sent to the ActionHandler
 */
public class ActionDiscardLeaderCard extends Action {

    private LeaderCard card;

    public ActionDiscardLeaderCard(Player player, LeaderCard card) {
        super(player);
        this.card = card;
    }

    @Override
    public boolean isValid(Match match) {
        return false;
    }

    @Override
    public void start(Match match) {
        player.getPersonalBoard().getLeaderCardsSpace().getCards().remove(card);

        EventCouncilPrivilegeReceived event = new EventCouncilPrivilegeReceived(player, new CouncilPrivilege(1));
        match.getActionHandler().update(event, match);
        match.notifyObserver(event);

        EventDiscardLeaderCard eventCard = new EventDiscardLeaderCard(player, card.getId());
        match.getActionHandler().update(eventCard, match);
        match.notifyObserver(eventCard);
    }

    @Override
    public String toString() {
        return card.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ActionDiscardLeaderCard)) return false;

        ActionDiscardLeaderCard that = (ActionDiscardLeaderCard) o;

        return card.equals(that.card);
    }

    @Override
    public int hashCode() {
        return card.hashCode();
    }
}
