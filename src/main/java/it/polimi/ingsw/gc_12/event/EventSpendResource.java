package it.polimi.ingsw.gc_12.event;

import it.polimi.ingsw.gc_12.effect.EffectProvider;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.resource.Resource;
import it.polimi.ingsw.gc_12.resource.ResourceType;

import java.util.ArrayList;
import java.util.List;


public class EventSpendResource extends Event {

    private ResourceType resourceType;
    private int value;

    public EventSpendResource(Player player, Resource resource) {
        super(player);
        this.resourceType = resource.getType();
        this.value = resource.getValue();
    }
    public EventSpendResource(Resource resource) {
        super();
        this.resourceType = resource.getType();
        this.value = resource.getValue();
    }
    @Override
    public List<Object> getAttributes() {
        List<Object> attributes = new ArrayList<>();
        attributes.add(resourceType);
        attributes.add(value);
        return attributes;
    }

    @Override
    public List<EffectProvider> getEffectProviders() {
        List<EffectProvider> effectProviders = new ArrayList<>();
        effectProviders.addAll(player.getCards());
        effectProviders.addAll(player.getExcommunications());
        return effectProviders;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        EventSpendResource other = (EventSpendResource) obj;

        // Check if they have the same resource
        if(this.resourceType.equals(other.resourceType))
            return true;
        return false;
    }
}
