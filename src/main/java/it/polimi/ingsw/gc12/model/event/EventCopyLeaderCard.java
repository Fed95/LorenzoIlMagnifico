package it.polimi.ingsw.gc12.model.event;

import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.model.action.ActionCopyLeaderCard;
import it.polimi.ingsw.gc12.controller.ActionHandler;
import it.polimi.ingsw.gc12.model.action.DiscardAction;
import it.polimi.ingsw.gc12.model.card.LeaderCard;

import java.util.ArrayList;

public class EventCopyLeaderCard extends Event {

    private LeaderCard myCard;

    public EventCopyLeaderCard(Player player, LeaderCard myCard) {
        super(player);
        this.myCard = myCard;
    }

    @Override
    public void setActions(Match match) {
        actions = new ArrayList<>();
            for(LeaderCard otherCard : match.getPlayedLeaderCards())
                if(otherCard.getId() != myCard.getId())
                    actions.add(new ActionCopyLeaderCard(player, myCard, otherCard));
        actions.add(new DiscardAction(player, myCard));
    }

    @Override
    public String toString() {
        return "";
    }
}
