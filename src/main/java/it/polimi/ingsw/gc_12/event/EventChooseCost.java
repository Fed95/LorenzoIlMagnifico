package it.polimi.ingsw.gc_12.event;

import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.effect.EffectProvider;
import it.polimi.ingsw.gc_12.resource.Resource;
import it.polimi.ingsw.gc_12.resource.ResourceExchange;

import java.util.List;

/**
 * Created by feder on 2017-06-11.
 */
public class EventChooseCost extends Event {

    private ResourceExchange militaryExchange;
    private List<Resource> requirements;

    public EventChooseCost(Player player, ResourceExchange militaryExchange, List<Resource> requirements) {
        super(player);
        this.militaryExchange = militaryExchange;
        this.requirements = requirements;
    }

    public EventChooseCost(ResourceExchange militaryExchange, List<Resource> requirements) {
        this.militaryExchange = militaryExchange;
        this.requirements = requirements;
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
