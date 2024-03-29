package it.polimi.ingsw.gc12.model.effect;

import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.model.event.Event;
import it.polimi.ingsw.gc12.model.player.resource.Resource;

import java.util.ArrayList;
import java.util.List;

public class EffectResourceForResource extends Effect {

    private Player player;
    private Resource ownedResource;
    private Resource resource;

    public EffectResourceForResource(Event event, Resource ownedResource, Resource resource) {
        super(event);
        this.ownedResource = ownedResource;
        this.resource = resource;
    }

    @Override
    public void execute(Match match, Event event, boolean validation) {
        if(!validation) {
            player = event.getPlayer();
            int value = player.getResourceValue(ownedResource.getType()) / ownedResource.getValue();
            resource.setValue(resource.getValue() * value);

            int newValue = player.getResources().get(resource.getType()).getValue() + resource.getValue();

            if (newValue < 0)
                player.setResourceValue(resource.getType(), 0);
            else
                player.setResourceValue(resource.getType(), newValue);
        }
    }

    @Override
    public void discard(Match match, Event event) {
    }

    @Override
    public String toString() {
        return event.getClass().getSimpleName() + ": " + this.getClass().getSimpleName() + ": " + resource + " for every [" + resource + "] you own.";
    }
}
