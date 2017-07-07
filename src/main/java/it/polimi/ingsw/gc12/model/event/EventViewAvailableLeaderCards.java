package it.polimi.ingsw.gc12.model.event;

import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.model.action.ActionActivateLeaderCard;
import it.polimi.ingsw.gc12.controller.ActionHandler;
import it.polimi.ingsw.gc12.model.action.DiscardAction;
import it.polimi.ingsw.gc12.model.card.LeaderCard;
import it.polimi.ingsw.gc12.view.client.ClientHandler;

import java.util.ArrayList;

public class EventViewAvailableLeaderCards extends Event {

    public EventViewAvailableLeaderCards(Player player) {
        super(player);
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
    public void setActions(Match match) {
        actions = new ArrayList<>();
        for(LeaderCard card : player.getAvailableLeaderCards())
            actions.add(new ActionActivateLeaderCard(player, card));
        actions.add(new DiscardAction(player));
    }

    @Override
    public String toString() {
        return "";
    }

}
