package it.polimi.ingsw.gc_12.event;

import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.card.Card;
import it.polimi.ingsw.gc_12.effect.EffectProvider;

import java.util.ArrayList;
import java.util.List;


/* this event is saved in the card with the card ID.
It is used to execute the immediate effects of the card.
When created during the game it is created with a card so that the effects can me retrieved form the card*/
public class EventPickCard extends Event {

    private int cardID;
    private Card card;

    public EventPickCard(Player player, Card card) {
        super(player);
        this.card = card;
        this.cardID = card.getId();
        effectProviders.add(card);
    }

    public EventPickCard(int cardID) {
        this.cardID = cardID;
    }

    @Override
    public List<Object> getAttributes() {
        List<Object> attributes = new ArrayList<>();
        attributes.add(cardID);
        attributes.add(card);
        return attributes;
    }

    @Override
    public List<EffectProvider> getEffectProviders() {
        return effectProviders;
    }

    public int getCardID() {
        return cardID;
    }

    @Override
    public boolean equals(Object obj) {
        EventPickCard other = (EventPickCard) obj;
        if(other.getCardID() == (cardID))
            return true;
        return false;
    }
}
