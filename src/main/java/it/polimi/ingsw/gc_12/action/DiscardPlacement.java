package it.polimi.ingsw.gc_12.action;

import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.event.EventDiscardPlacement;

/**
 * Created by feder on 2017-06-17.
 */
public class DiscardPlacement extends Action {

    public DiscardPlacement(Player player) {
        super(player);
    }

    @Override
    public boolean isValid(Match match) {
        return false;
    }

    @Override
    public void start(Match match) {
        EventDiscardPlacement event = new EventDiscardPlacement(player);
        match.getActionHandler().update(event);
        //Notifies the ServerRMIView
        match.notifyObserver(event);
    }

    @Override
    public String toString() {
        return "Back to start";
    }
}
