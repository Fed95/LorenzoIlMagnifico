package it.polimi.ingsw.gc_12.action;

import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.event.EventDiscardAction;

import java.io.IOException;

public class DiscardAction extends Action {

    public DiscardAction(Player player) {
        super(player);
    }



    @Override
    public boolean isValid(Match match) {
        return false;
    }

    @Override
    public void start(Match match) throws IOException {
        EventDiscardAction event = new EventDiscardAction(player);
        match.getActionHandler().update(event);
        //Notifies the ServerRMIView
        match.notifyObserver(event);
    }

    @Override
    public String toString() {
        return "Back to the start";
    }
}
