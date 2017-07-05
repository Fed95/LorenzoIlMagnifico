package it.polimi.ingsw.gc_12.effect;

import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.card.CardDevelopment;
import it.polimi.ingsw.gc_12.event.Event;
import it.polimi.ingsw.gc_12.event.EventPickCard;
import it.polimi.ingsw.gc_12.exceptions.ActionDeniedException;
import it.polimi.ingsw.gc_12.resource.Resource;
import it.polimi.ingsw.gc_12.resource.ResourceExchange;
import it.polimi.ingsw.gc_12.resource.ResourceType;

import java.util.ArrayList;
import java.util.List;

public class EffectMultiplyCardResourceBonus extends Effect{

    List<ResourceType> types = new ArrayList<>();
    private int multiplier;

    public EffectMultiplyCardResourceBonus(Event event, List<ResourceType> types, int multiplier) {
        super(event);
        if(types != null)
            this.types = types;
        this.multiplier = multiplier;
    }

    @Override
    public void execute(Match match, Event event, boolean validation) throws ActionDeniedException {

        if (!(event instanceof EventPickCard))
            throw new IllegalStateException();

        if(!validation) {
            CardDevelopment card = ((EventPickCard) event).getCard();
            List<Resource> resources = new ArrayList<>();
            Player player = event.getPlayer();

            for (Effect effect : card.getImmediateEffects())
                if (effect instanceof EffectChangeResource)
                    handleEffect(resources, (EffectChangeResource) effect);
            //Starts from 1 because the first has been executed by the card effect
            for (int i = 1; i < multiplier; i++)
                player.addResources(resources);
        }
    }

    private void handleEffect(List<Resource> resources, EffectChangeResource effect){
        for(ResourceExchange exchange : effect.getExchanges())
            for(Resource resource : exchange.getBonus())
                if(types.contains(resource.getType()))
                    resources.add(resource);
    }

    @Override
    public void discard(Match match, Event event) {

    }
}
