package it.polimi.ingsw.gc_12.event;

import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.effect.EffectProvider;
import it.polimi.ingsw.gc_12.resource.Resource;

import java.util.List;


public class EventReceiveResource extends Event {

    private Resource resource;

    public EventReceiveResource(Player player, Resource resource) {
        super(player);
        this.resource = resource;
    }

    @Override
    public List<Object> getAttributes() {
        return null;
    }

    public Resource getResource() {
        return resource;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (!(o instanceof EventReceiveResource)) return false;
        if (!super.equals(o)) return false;

        EventReceiveResource that = (EventReceiveResource) o;

        return resource != null ? resource.getType().equals(that.resource.getType()) : true;
    }

    @Override
    public int hashCode() {
        return resource != null ? resource.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "EventReceiveResource: " + player.getName() + " received: " + resource;
    }
}
