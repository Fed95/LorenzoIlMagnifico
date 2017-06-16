package it.polimi.ingsw.gc_12.event;

import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.effect.EffectProvider;

import java.util.List;

/**
 * Created by feder on 2017-06-16.
 */
public class EventRequestStatistics extends Event {

    public EventRequestStatistics(Player player) {
        super(player);
    }

    @Override
    public String toString() {
        return "EventRequestStatistics";
    }

    @Override
    public List<Object> getAttributes() {
        return null;
    }

    @Override
    public List<EffectProvider> getEffectProviders() {
        return null;
    }

}
