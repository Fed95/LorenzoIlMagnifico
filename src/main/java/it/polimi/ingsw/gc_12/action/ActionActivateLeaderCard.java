package it.polimi.ingsw.gc_12.action;

import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.card.LeaderCard;
import it.polimi.ingsw.gc_12.event.EventStartTurn;

public class ActionActivateLeaderCard extends Action {

    private LeaderCard card;

    public ActionActivateLeaderCard(Player player, LeaderCard card) {
        super(player);
        this.card = card;
    }

    @Override
    public boolean isValid(Match match) {
        return false;
    }

    @Override
    public void start(Match match) {
        player.getPersonalBoard().getLeaderCards().get(player.getPersonalBoard().getLeaderCards().indexOf(card)).setActive(true);

        EventStartTurn event = new EventStartTurn(player);
        match.getActionHandler().update(event, match);
        match.notifyObserver(event);
    }

    @Override
    public String toString() {
        return card.toString();
    }
}
