package it.polimi.ingsw.gc_12.action;

import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.card.LeaderCard;
import it.polimi.ingsw.gc_12.event.EventDiscardAction;

public class DiscardAction extends Action {

    private LeaderCard leaderCard;

    public DiscardAction(Player player) {
        super(player);
    }

    public DiscardAction(Player player, LeaderCard leaderCard) {
        super(player);
        this.leaderCard = leaderCard;
    }

    @Override
    public boolean isValid(Match match) {
        return false;
    }

    @Override
    public void start(Match match) {
        if(leaderCard != null)
            leaderCard.setPlayed(false);

        EventDiscardAction event = new EventDiscardAction(player);
        match.getActionHandler().update(event, match);
        //Notifies the ServerRMIView
        match.notifyObserver(event);
    }

    @Override
    public String toString() {
        return "Back to the start";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DiscardAction)) return false;

        DiscardAction that = (DiscardAction) o;

        return leaderCard != null ? leaderCard.equals(that.leaderCard) : that.leaderCard == null;
    }

    @Override
    public int hashCode() {
        return leaderCard != null ? leaderCard.hashCode() : 0;
    }
}
