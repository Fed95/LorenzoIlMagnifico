package it.polimi.ingsw.gc_12.event;

import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.action.ActionActivateLeaderCard;
import it.polimi.ingsw.gc_12.action.ActionHandler;
import it.polimi.ingsw.gc_12.action.DiscardAction;
import it.polimi.ingsw.gc_12.card.LeaderCard;
import it.polimi.ingsw.gc_12.client.ClientHandler;

import java.util.ArrayList;
import java.util.List;

public class EventViewAvaliableLeaderCards extends Event {

    public EventViewAvaliableLeaderCards(Player player) {
        super(player);
    }

    @Override
    public List<Object> getAttributes() {
        return null;
    }

    @Override
    public void executeClientSide(ClientHandler client) {
        if(client.isMyTurn()) {
            StringBuilder sb = new StringBuilder();
            sb.append(System.getProperty("line.separator"));
            sb.append("Viewing " + player.getName() + "'s Available LeaderCards:").append(System.getProperty("line.separator"));
            sb.append(System.getProperty("line.separator"));
            System.out.println(sb.toString());
            super.executeClientSide(client);
        }
    }

    @Override
    public void setActions(ActionHandler actionHandler, Match match) {
        actions = new ArrayList<>();
        for(LeaderCard card : player.getAvailableLeaderCards())
            actions.add(new ActionActivateLeaderCard(player, card));
        actions.add(new DiscardAction(player));
    }

    @Override
    public String toString() {
        return null;
    }


}
