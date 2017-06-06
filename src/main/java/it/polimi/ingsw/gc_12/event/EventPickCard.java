package it.polimi.ingsw.gc_12.event;

import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.card.Card;
import it.polimi.ingsw.gc_12.effect.EffectProvider;

import java.util.ArrayList;
import java.util.List;

public class EventPickCard extends Event {

    private Card card;

    public EventPickCard(Player player, Card card) {
        super(player);
        this.card = card;
    }

    public EventPickCard(Card card) {
        this.card = card;
        effectProviders.add(card);
    }

    @Override
    public List<Object> getAttributes() {
        List<Object> attributes = new ArrayList<>();
        attributes.add(card);
        return attributes;
    }

    @Override
    public List<EffectProvider> getEffectProviders() {
        return effectProviders;
    }

    public Card getCard() {
        return card;
    }

    @Override
    public boolean equals(Object obj) {
        EventPickCard other = (EventPickCard) obj;
        if(other.getCard().equals(card))
            return true;
        return false;
    }
}
