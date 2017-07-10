package it.polimi.ingsw.gc12.model.action;

import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.model.event.EventViewAvailableLeaderCards;

/**
 * When performed, the player will be displayed with a set of ActionActivateLeaderCard,
 * one for each LeaderCard he can activate
 */
public class ActionViewAvailableLeaderCards extends Action{

    public ActionViewAvailableLeaderCards(Player player) {
        super(player);
    }

    @Override
    public boolean isValid(Match match) {
        return false;
    }

    @Override
    public void start(Match match) {
        EventViewAvailableLeaderCards event = new EventViewAvailableLeaderCards(player);
        match.getActionHandler().update(event, match);
        //Notifies the ServerRMIView
        match.notifyObserver(event);
    }

    @Override
    public String toString() {
        return "Activate a LeaderCard";
    }
}
