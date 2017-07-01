package it.polimi.ingsw.gc_12.event;

import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.action.ActionHandler;
import it.polimi.ingsw.gc_12.action.ActionViewStatistics;
import it.polimi.ingsw.gc_12.action.DiscardAction;
import it.polimi.ingsw.gc_12.effect.EffectProvider;

import java.util.ArrayList;
import java.util.List;

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
