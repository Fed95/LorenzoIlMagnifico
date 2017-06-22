package it.polimi.ingsw.gc_12.effect;

import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.event.Event;
import it.polimi.ingsw.gc_12.event.EventReceiveResource;
import it.polimi.ingsw.gc_12.resource.Resource;
import it.polimi.ingsw.gc_12.resource.ResourceType;

import java.io.IOException;

public class EffectReduceReceivedResource extends Effect {

    private int value;
    private ResourceType resourceType;

    public EffectReduceReceivedResource(Event event, int value) {
        super(event);
        this.value = value;
        this. resourceType = ((EventReceiveResource) event).getResource().getType();
    }

    @Override
    public void execute(Match match, Event event) {
       if(!(event instanceof EventReceiveResource))
           throw new IllegalStateException("EffectReduceReceivedResource: received an unexpected event!");

       ResourceType type = ((EventReceiveResource) event).getResource().getType();

       //TODO: this is a redundant check
       if(this.resourceType.equals(type)) {
           int newValue = ((EventReceiveResource) event).getResource().getValue() + value;
           ((EventReceiveResource) event).getResource().setValue(newValue);
       }
    }

    @Override
    public void discard(Event event) {

    }
}
