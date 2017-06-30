package it.polimi.ingsw.gc_12.action;

import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.event.EventViewAvaliableLeaderCards;

public class ActionViewAvalilableLeaderCards extends Action{


    public ActionViewAvalilableLeaderCards(Player player) {
        super(player);
    }

    @Override
    public boolean isValid(Match match) {
        return false;
    }

    @Override
    public void start(Match match) {
        EventViewAvaliableLeaderCards event = new EventViewAvaliableLeaderCards(player);
        match.getActionHandler().update(event, match);
        //Notifies the ServerRMIView
        match.notifyObserver(event);
    }

    @Override
    public String toString() {
        return "Activate a LeaderCard";
    }
}
