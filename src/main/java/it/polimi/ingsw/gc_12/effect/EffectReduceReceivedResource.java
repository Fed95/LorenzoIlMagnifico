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

    public EffectReduceReceivedResource(Event event, int value) {
        super(event);
        this.value = value;
    }

    @Override
    public void execute(Match match, Event event) throws RuntimeException, IOException {
       if(!(event instanceof EventReceiveResource))
           throw new IllegalArgumentException("EffectReduceReceivedResource: received an unexpected event!");

       Player player = event.getPlayer();
       EventReceiveResource eventReceiveResource = (EventReceiveResource) event;

       ResourceType type = ((EventReceiveResource) event).getResource().getType();
       int newValue = player.getResources().get(type).getValue() - ((EventReceiveResource) event).getResource().getValue();

       player.setResourceValue(type, newValue);
    }

    @Override
    public void discard(Event event) throws RuntimeException, IOException {

    }
}
