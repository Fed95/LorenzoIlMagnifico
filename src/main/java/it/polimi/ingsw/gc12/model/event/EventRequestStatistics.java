package it.polimi.ingsw.gc12.model.event;

import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.controller.ActionHandler;
import it.polimi.ingsw.gc12.model.action.ActionViewStatistics;
import it.polimi.ingsw.gc12.model.action.DiscardAction;

import java.util.ArrayList;

public class EventRequestStatistics extends Event {

    public EventRequestStatistics(Player player) {
        super(player);
    }

    @Override
    public void setActions(ActionHandler actionHandler, Match match) {
        actions = new ArrayList<>();
        for(Player p : match.getPlayers().values())
            actions.add(new ActionViewStatistics(player, p));
        actions.add(new DiscardAction(player));
    }

    @Override
    public String toString() {
        return "EventRequestStatistics";
    }

}
