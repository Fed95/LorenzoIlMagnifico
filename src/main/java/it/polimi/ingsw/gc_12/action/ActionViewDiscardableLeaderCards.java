package it.polimi.ingsw.gc_12.action;

import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.event.EventViewDiscardableLeaderCards;

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
}
