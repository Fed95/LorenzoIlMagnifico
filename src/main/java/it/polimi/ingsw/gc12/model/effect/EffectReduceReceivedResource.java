package it.polimi.ingsw.gc12.model.effect;

import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.event.Event;
import it.polimi.ingsw.gc12.model.event.EventReceiveResource;
import it.polimi.ingsw.gc12.model.player.resource.ResourceType;

public class EffectReduceReceivedResource extends Effect {

    private int value;
    private ResourceType resourceType;

    public EffectReduceReceivedResource(Event event, int value) {
        super(event);
        this.value = value;
        if(!(event instanceof EventReceiveResource))
            throw new IllegalStateException("Expecting EventReceiveResource, received: " + event);
        this. resourceType = ((EventReceiveResource) event).getResource().getType();
    }

    @Override
    public void execute(Match match, Event event, boolean validation) {
        if(!validation) {
            if (!(event instanceof EventReceiveResource))
                throw new IllegalStateException("EffectReduceReceivedResource: received an unexpected event!");

            EventReceiveResource eventReceiveResource = (EventReceiveResource) event;
            ResourceType type = eventReceiveResource.getResource().getType();

            if (this.resourceType.equals(type)) {
                int newValue = eventReceiveResource.getResource().getValue() + value;
                eventReceiveResource.getResource().setValue(newValue);
            }
        }
    }

    public ResourceType getResourceType() {
        return resourceType;
    }

    @Override
    public void discard(Match match, Event event) {
    }

    @Override
    public String toString() {
        return "For every " + resourceType + " resource you receive, its value will be changed by " + value;
    }
}
