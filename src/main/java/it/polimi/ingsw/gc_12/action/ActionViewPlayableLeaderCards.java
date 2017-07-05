package it.polimi.ingsw.gc_12.action;

import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.event.EventViewPlayableLeaderCards;

public class ActionViewPlayableLeaderCards extends Action{


    public ActionViewPlayableLeaderCards(Player player) {
        super(player);
    }

    @Override
    public boolean isValid(Match match) {
        return false;
    }

    @Override
    public void start(Match match) {
        EventViewPlayableLeaderCards event = new EventViewPlayableLeaderCards(player);
        match.getActionHandler().update(event, match);
        //Notifies the ServerRMIView
        match.notifyObserver(event);
    }

    @Override
    public String toString() {
        return "Play a LeaderCard";
    }

    @Override
    public boolean equals(Object o) {
        return this == o || o instanceof ActionViewPlayableLeaderCards;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
