package it.polimi.ingsw.gc_12.effect;

import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.event.Event;
import it.polimi.ingsw.gc_12.resource.Resource;

import java.util.ArrayList;
import java.util.List;

public class EffectResourceForResource extends Effect {

    private Player player;
    private Resource ownedResource;
    private Resource resource;

    public EffectResourceForResource(Event event, Resource ownedResource, Resource resource) {
        super(event);
        this.player = event.getPlayer();
        this.ownedResource = ownedResource;
        this.resource = resource;
    }

    @Override
    public void execute(Match match, Event event, boolean validation) {

        int value = player.getResourceValue(ownedResource.getType()) / ownedResource.getValue();
        resource.setValue(resource.getValue() * value);

        int newValue = player.getResources().get(resource.getType()).getValue() + value;

        if(newValue < 0)
            player.setResourceValue(resource.getType(), 0);
        else
            player.setResourceValue(resource.getType(), newValue);
    }

    @Override
    public void discard(Match match, Event event) {
        List<Resource> resources = new ArrayList<>();
        resources.add(resource);
        player.removeResources(resources);
    }

    @Override
    public String toString() {
        return event.getClass().getSimpleName() + ": " + this.getClass().getSimpleName() + ": " + resource + " for every [" + resource + "] you own.";
    }
}
