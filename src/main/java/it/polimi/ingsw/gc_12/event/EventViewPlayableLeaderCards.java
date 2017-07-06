package it.polimi.ingsw.gc_12.event;

import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.action.ActionPlayLeaderCard;
import it.polimi.ingsw.gc_12.action.ActionHandler;
import it.polimi.ingsw.gc_12.action.DiscardAction;
import it.polimi.ingsw.gc_12.card.LeaderCard;
import it.polimi.ingsw.gc_12.client.ClientHandler;
import it.polimi.ingsw.gc_12.java_fx.MainBoard;
import javafx.application.Platform;

import java.util.ArrayList;
import java.util.List;

public class EventViewPlayableLeaderCards extends Event implements EventView{

    public EventViewPlayableLeaderCards(Player player) {
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
    public void setActions(ActionHandler actionHandler, Match match) {
        actions = new ArrayList<>();
        for(LeaderCard card : player.getPlayableLeaderCards())
            actions.add(new ActionPlayLeaderCard(player, card));
        actions.add(new DiscardAction(player));
    }

    @Override
    public String toString() {
        return "";
    }


    @Override
    public void executeViewSide(MainBoard view) {
        Platform.runLater(() -> view.getControllerMainBoard().sendAction());
    }
}
