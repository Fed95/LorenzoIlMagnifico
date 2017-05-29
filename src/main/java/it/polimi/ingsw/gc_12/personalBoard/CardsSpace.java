package it.polimi.ingsw.gc_12.personalBoard;

import it.polimi.ingsw.gc_12.card.CardDevelopment;
import it.polimi.ingsw.gc_12.card.CardType;
import it.polimi.ingsw.gc_12.exceptions.CannotPlaceCardException;

import java.util.ArrayList;
import java.util.List;

public class CardsSpace {
    private CardType type;
    private List<CardSlot> slots = new ArrayList<>();
    private int spacesAvailable;

    public CardsSpace(CardType type){
        this.type = type;
        for(int i = 0; i < spacesAvailable; i++){
            //TODO: import from Json requisites and bonuses of card slots
            CardSlot cardSpace = new CardSlot();
            slots.add(cardSpace);
        }
    }

    public void placeCard(CardDevelopment card) throws CannotPlaceCardException {
        if(card.getType().equals(type))
            throw new CannotPlaceCardException("Incompatible CardType");
        //else
        for(CardSlot cardSpace : slots)
            if(cardSpace.isEmpty()) {
                //Can throw an exception
                cardSpace.placeCard(card);
                break;
            }
    }

    public CardSlot getFirstFreeSlot() throws CannotPlaceCardException {
        for(CardSlot slot : slots)
            if(slot.isEmpty())
                return slot;
        throw new CannotPlaceCardException("Your CardSpace for this CardType is already full.");
    }
}
