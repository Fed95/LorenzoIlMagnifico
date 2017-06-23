package it.polimi.ingsw.gc_12.effect;

import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.card.CardType;
import it.polimi.ingsw.gc_12.event.Event;
import it.polimi.ingsw.gc_12.resource.Resource;

import java.util.ArrayList;
import java.util.List;

public class EffectResourceForCards extends Effect{

    private Player player;
    private CardType cardType;
    private Resource resource;

    public EffectResourceForCards(Event event, CardType cardType, Resource resource) {
        super(event);
        this.player = event.getPlayer();
        this.cardType = cardType;
        this.resource = resource;
    }

    @Override
    public void execute(Match match, Event event, boolean validation) {
        int numOfCards = player.getPersonalBoard().getCards(cardType).size();
        resource.setValue(resource.getValue()*numOfCards);

        List<Resource> resources = new ArrayList<>();
        resources.add(resource);
        player.addResources(resources);
    }

    @Override
    public void discard(Event event) {
        List<Resource> resources = new ArrayList<>();
        resources.add(resource);
        player.removeResources(resources);
    }

    @Override
    public String toString() {
        return event.getClass().getSimpleName() + ": " + this.getClass().getSimpleName() + ": " + resource + " for every " + cardType + " card you own.";
    }
}
