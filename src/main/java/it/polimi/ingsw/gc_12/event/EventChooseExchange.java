package it.polimi.ingsw.gc_12.event;

import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.effect.EffectProvider;
import it.polimi.ingsw.gc_12.resource.ResourceExchange;

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

    public List<ResourceExchange> getExchanges() {
        return exchanges;
    }

    @Override
    public List<Object> getAttributes() {
        return null;
    }

    @Override
    public List<EffectProvider> getEffectProviders() {
        return null;
    }

    @Override
    public String toString() {
        return "Choose the exchange you would like to activate";
    }
}
