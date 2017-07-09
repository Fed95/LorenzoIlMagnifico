package it.polimi.ingsw.gc12.model.effect;

import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.model.card.Card;
import it.polimi.ingsw.gc12.model.event.Event;
import it.polimi.ingsw.gc12.model.event.EventPickCard;
import it.polimi.ingsw.gc12.misc.exception.ActionDeniedException;
import it.polimi.ingsw.gc12.model.player.resource.Resource;
import it.polimi.ingsw.gc12.model.player.resource.ResourceType;

import java.util.HashMap;
import java.util.Map;

public class EffectCardDiscount extends Effect {

    private Resource resource;
    private String description;

    public EffectCardDiscount(Event event, Resource resource, String description) {
        super(event);
        this.resource = resource;
        this.description = description;
    }

    @Override
    public void execute(Match match, Event event, boolean validation) throws ActionDeniedException {

        if(!validation) {
            if (!(event instanceof EventPickCard))
                throw new IllegalArgumentException("Expecting EventPickCard, received: " + event.getClass().getSimpleName());

            Player player = event.getPlayer();
            Card card = ((EventPickCard) event).getCard();
            ResourceType type = this.resource.getType();

            Map<ResourceType, Resource> cardRequirements = new HashMap<>();
            for (Resource resource : card.getRequirements())
                cardRequirements.put(resource.getType(), resource);

            if (cardRequirements.containsKey(type)) {
                int newValue = cardRequirements.get(type).getValue() - this.resource.getValue();
                newValue = (newValue < 0 ? cardRequirements.get(type).getValue() : this.resource.getValue());

                int ownedResourceValue = player.getResources().get(type).getValue();
                player.getResources().get(type).setValue(ownedResourceValue + newValue);
            }
        }
    }

    @Override
    public void discard(Match match, Event event) {

    }

    public Resource getResource() {
        return resource;
    }

    @Override
    public String toString() {
        return description;
    }
}
