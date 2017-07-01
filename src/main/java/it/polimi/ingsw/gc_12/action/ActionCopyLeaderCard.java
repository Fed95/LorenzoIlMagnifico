package it.polimi.ingsw.gc_12.action;

import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.card.LeaderCard;
import it.polimi.ingsw.gc_12.event.EventStartTurn;

public class ActionCopyLeaderCard extends Action {

    private LeaderCard myCard;
    private LeaderCard otherCard;

    public ActionCopyLeaderCard(Player player, LeaderCard myCard, LeaderCard otherCard) {
        super(player);
        this.myCard = myCard;
        this.otherCard = otherCard;
    }

    @Override
    public boolean isValid(Match match) {
        return false;
    }

    @Override
    public void start(Match match) {
        myCard = otherCard;

        EventStartTurn event = new EventStartTurn(player);
        match.getActionHandler().update(event, match);
        //Notifies the ServerRMIView
        match.notifyObserver(event);
    }
}
