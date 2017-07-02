package it.polimi.ingsw.gc_12.event;

import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.card.Card;
import it.polimi.ingsw.gc_12.card.CardDevelopment;
import it.polimi.ingsw.gc_12.card.CardType;
import it.polimi.ingsw.gc_12.effect.EffectProvider;

import java.util.ArrayList;
import java.util.List;


/* this event is saved in the card with the card ID.
(In this case it is used to execute the immediate effects of the card)
or with card type (for EffectCardDiscount).
When created during the game it is created with a card so that the effects can be retrieved form the card*/
public class EventPickCard extends Event {

    private int cardID;
    private CardDevelopment card;
    private CardType cardType;
    private boolean anyCard = false;

    public EventPickCard(Player player, CardDevelopment card) {
        super(player);
        this.card = card;
        this.cardID = card.getId();
    }

    public EventPickCard(CardType cardType) {
        this.cardType = cardType;
    }

    public EventPickCard(int cardID) {
        this.cardID = cardID;
    }

    public EventPickCard(CardDevelopment card) {
        this.card = card;
    }

    public EventPickCard(){
        anyCard = true;
    }

    public boolean isAnyCard() {
        return anyCard;
    }

    public CardDevelopment getCard() {
        return card;
    }

    public CardType getCardType() {
        return cardType;
    }

    public int getCardID() {
        return cardID;
    }

    @Override
    public boolean equals(Object obj) {
        if(super.equals(obj)) {
            //"this" is the event created in the game
            //"that" is the event found in the card
            EventPickCard that = (EventPickCard) obj;

            if(that.isAnyCard())
                return true;

            if(that.getCardType() != null && ((CardDevelopment) card).getType().equals(that.getCardType()))
                return true;

            if ((cardID) == that.getCardID())
                return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = cardID;
        result = 31 * result + (card != null ? card.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(System.getProperty("line.separator"));
        sb.append(player.getName() + " picked the following card: ").append(System.getProperty("line.separator"));
        sb.append(card);
        return sb.toString();
    }
}
