package it.polimi.ingsw.gc_12.event;

import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.action.ActionCopyLeaderCard;
import it.polimi.ingsw.gc_12.action.ActionHandler;
import it.polimi.ingsw.gc_12.card.LeaderCard;

import java.util.ArrayList;

public class EventCopyLeaderCard extends Event {

    private LeaderCard myCard;

    public EventCopyLeaderCard(Player player, LeaderCard myCard) {
        super(player);
        this.myCard = myCard;
    }

    @Override
    public void setActions(ActionHandler actionHandler, Match match) {
        actions = new ArrayList<>();
        for(Player player : match.getPlayers().values())
            for(LeaderCard otherCard : player.getPersonalBoard().getLeaderCards())
                actions.add(new ActionCopyLeaderCard(player, myCard, otherCard));
    }

    @Override
    public String toString() {
        return null;
    }
}
