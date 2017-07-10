package it.polimi.ingsw.gc12.model.action;

import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.model.event.EventViewPlayableLeaderCards;

/**
 * When performed, the player will be displayed with a set of ActionPlayLeaderCard,
 * one for each LeaderCard he can play
 */
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
