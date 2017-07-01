package it.polimi.ingsw.gc_12.event;

import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.action.ActionChooseExchange;
import it.polimi.ingsw.gc_12.action.ActionHandler;
import it.polimi.ingsw.gc_12.effect.EffectProvider;
import it.polimi.ingsw.gc_12.resource.ResourceExchange;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by feder on 2017-06-20.
 */
public class EventChooseExchange extends Event {

    private List<ResourceExchange> exchanges;

    public EventChooseExchange(Player player, List<ResourceExchange> exchanges) {
        super(player);
        this.exchanges = exchanges;
    }

    @Override
    public void setActions(ActionHandler actionHandler, Match match) {
        actions = new ArrayList<>();
        for (ResourceExchange exchange : exchanges)
            actions.add(new ActionChooseExchange(player, exchange));
    }

    public List<ResourceExchange> getExchanges() {
        return exchanges;
    }

    @Override
    public String toString() {
        return "Choose the exchange you would like to activate";
    }
}
