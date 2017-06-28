package it.polimi.ingsw.gc_12.effect;

import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.card.Card;
import it.polimi.ingsw.gc_12.event.Event;
import it.polimi.ingsw.gc_12.event.EventPickCard;
import it.polimi.ingsw.gc_12.exceptions.ActionDeniedException;
import it.polimi.ingsw.gc_12.resource.Resource;
import it.polimi.ingsw.gc_12.resource.ResourceType;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EffectCardDiscount extends Effect {

    private Resource resource;

    public EffectCardDiscount(Event event, Resource resource) {
        super(event);
        this.resource = resource;
    }

    @Override
    public void execute(Match match, Event event, boolean validation) throws ActionDeniedException {
        
        if(!(event instanceof EventPickCard))
            throw new IllegalArgumentException("Expecting EventPickCard, received: " + event.getClass().getSimpleName());

        Player player = event.getPlayer();
        Card card = ((EventPickCard) event).getCard();
        ResourceType type = this.resource.getType();

        Map<ResourceType, Resource> cardRequirements = new HashMap<>();
        for(Resource resource : card.getRequirements())
            cardRequirements.put(resource.getType(), resource);

        if(cardRequirements.containsKey(type)){
            int newValue = cardRequirements.get(type).getValue() - this.resource.getValue();
            newValue = (newValue < 0 ? cardRequirements.get(type).getValue() : this.resource.getValue());

            int ownedResourceValue = player.getResources().get(type).getValue();
            player.getResources().get(type).setValue(ownedResourceValue + newValue);
        }
    }

    @Override
    public void discard(Match match, Event event) {

    }
}
