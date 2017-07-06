package it.polimi.ingsw.gc12.model.action;

import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.model.event.EventViewDiscardableLeaderCards;

public class ActionViewDiscardableLeaderCards extends Action {

    public ActionViewDiscardableLeaderCards(Player player) {
        super(player);
    }

    @Override
    public boolean isValid(Match match) {
        return false;
    }

    @Override
    public void start(Match match) {
        EventViewDiscardableLeaderCards event = new EventViewDiscardableLeaderCards(player);
        match.getActionHandler().update(event, match);
        match.notifyObserver(event);
    }

    @Override
    public String toString() {
        return "Discard a LeaderCard in exchange for a CouncilPrivilege";
    }

    @Override
    public boolean equals(Object o) {
        return this == o || o instanceof ActionViewDiscardableLeaderCards;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
