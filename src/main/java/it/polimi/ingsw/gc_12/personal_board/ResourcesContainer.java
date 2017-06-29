package it.polimi.ingsw.gc_12.personal_board;

import it.polimi.ingsw.gc_12.effect.Effect;
import it.polimi.ingsw.gc_12.effect.EffectChangeResource;
import it.polimi.ingsw.gc_12.effect.EffectProvider;
import it.polimi.ingsw.gc_12.event.EventEndMatch;
import it.polimi.ingsw.gc_12.resource.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResourcesContainer implements EffectProvider {

    private Map<ResourceType, Resource> resources = new HashMap<>();
    List<Effect> effects = new ArrayList<>();


    public ResourcesContainer() {
    }

    public void syncronize(Map<ResourceType, Resource> resources){
        this.resources = resources;
    }

    @Override
    public List<Effect> getEffects() {
        int value = 0;
        for(Resource resource : resources.values()) {
            if(resource instanceof Money || resource instanceof Wood || resource instanceof Stone)
            value += resource.getValue();
        }
        ResourceExchange exchange = new ResourceExchange(null, new VictoryPoint(value/5));
        effects.add(new EffectChangeResource(new EventEndMatch(), exchange, false));
        return effects;
    }
}
