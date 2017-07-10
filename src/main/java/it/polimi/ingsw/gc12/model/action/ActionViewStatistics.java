package it.polimi.ingsw.gc12.model.action;

import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.model.event.EventViewStatistics;

/**
 * When performed, the player will be displayed with the chosenPlayer's statistics
 */
public class ActionViewStatistics extends Action {

    private Player chosenPlayer;

    public ActionViewStatistics(Player player, Player chosenPlayer) {
        super(player);
        this.chosenPlayer = chosenPlayer;
    }

    @Override
    public boolean isValid(Match match) {
        return false;
    }

    @Override
    public void start(Match match) {
        EventViewStatistics event = new EventViewStatistics(player, chosenPlayer);
        match.getActionHandler().update(event, match);
        //Notifies the ServerRMIView
        match.notifyObserver(event);
    }

    @Override
    public String toString() {
        return "View statistics of: " + chosenPlayer.getName();

    }
}
