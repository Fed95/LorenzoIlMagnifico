package it.polimi.ingsw.gc12.model.player.personalboard;

import it.polimi.ingsw.gc12.model.card.Card;
import it.polimi.ingsw.gc12.model.card.CardDevelopment;
import it.polimi.ingsw.gc12.model.card.CardType;
import it.polimi.ingsw.gc12.misc.exception.ActionNotAllowedException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CardsSpace implements Serializable{
    private CardType type;
    private List<CardSlot> slots = new ArrayList<>();
    private int spacesAvailable;

    public CardsSpace(CardType type){
        this.type = type;
    }

    public void placeCard(CardDevelopment card) {
        if(card.getType().equals(type))
            throw new IllegalArgumentException("Incompatible CardType");
        //else
        for(CardSlot cardSpace : slots)
            if(cardSpace.isEmpty()) {
                //Can throw an exception
                cardSpace.placeCard(card);
                break;
            }
    }

    public CardSlot getFirstFreeSlot() throws ActionNotAllowedException {
        for(CardSlot slot : slots)
            if(slot.isEmpty())
                return slot;
        throw new ActionNotAllowedException("Your CardSpace for this CardType is already full.");
    }

    public List<Card> getCards(){
        List<Card> cards = new ArrayList<>();
        for(CardSlot cardSlot : slots)
            if(cardSlot.getCard() != null)
                cards.add(cardSlot.getCard());
        return cards;
    }

    public void addCardSlot(CardSlot cardSlot) {
        this.slots.add(cardSlot);
    }

    public void setSpacesAvailable(int spacesAvailable) {
        this.spacesAvailable = spacesAvailable;
    }

    public CardType getType() {
        return type;
    }
}
