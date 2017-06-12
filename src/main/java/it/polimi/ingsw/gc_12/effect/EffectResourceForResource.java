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
    public void execute(Match match, Event event) throws RuntimeException {
        int numOfResources = player.getResourceValue(ownedResource.getType()) / ownedResource.getValue();
        resource.setValue(resource.getValue()*numOfResources);

        List<Resource> resources = new ArrayList<>();
        resources.add(resource);
        player.addResources(resources);
    }

    @Override
    public void discard(Event event) throws RuntimeException {
        List<Resource> resources = new ArrayList<>();
        resources.add(resource);
        player.removeResources(resources);
    }
}
