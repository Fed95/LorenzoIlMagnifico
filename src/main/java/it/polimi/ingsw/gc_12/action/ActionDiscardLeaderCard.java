package it.polimi.ingsw.gc_12.action;

import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.card.LeaderCard;
import it.polimi.ingsw.gc_12.event.EventCouncilPrivilegeReceived;
import it.polimi.ingsw.gc_12.event.EventStartTurn;
import it.polimi.ingsw.gc_12.resource.CouncilPrivilege;

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
        player.getPersonalBoard().getLeaderCards().remove(card);

        EventCouncilPrivilegeReceived event = new EventCouncilPrivilegeReceived(player, new CouncilPrivilege(1));
        //TODO: are there any effects that could be executed?
        match.getActionHandler().update(event, match);
        match.notifyObserver(event);

        EventStartTurn eventStartTurn = new EventStartTurn(player);
        match.getActionHandler().update(eventStartTurn, match);
        match.notifyObserver(eventStartTurn);
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
