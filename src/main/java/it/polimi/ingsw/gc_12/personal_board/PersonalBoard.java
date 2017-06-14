package it.polimi.ingsw.gc_12.personal_board;

import it.polimi.ingsw.gc_12.card.Card;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.card.CardDevelopment;
import it.polimi.ingsw.gc_12.card.CardType;
import it.polimi.ingsw.gc_12.resource.ResourceType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PersonalBoard implements Serializable{
    private Map<CardType, CardsSpace> cardsSpaces;
    private ResourcesContainer resourceContainer = new ResourcesContainer();
    private BonusTile bonusTile;

    public PersonalBoard(){
    }

    public boolean canPlaceCard(Player owner, CardDevelopment card) {
        //Can throw an exception
        CardSlot cardSlot = cardsSpaces.get(card.getType()).getFirstFreeSlot();

        if(cardSlot.getRequisites() != null)
            if(!owner.hasResources(cardSlot.getRequisites()))
                return false;
        return true;
    }

    public void setBonusTile(BonusTile bonusTile) {
        this.bonusTile = bonusTile;
    }

    public void setCardsSpaces(Map<CardType, CardsSpace> cardsSpaces) {
        this.cardsSpaces = cardsSpaces;
    }

    public void placeCard(CardDevelopment card){
        cardsSpaces.get(card.getType()).getFirstFreeSlot().placeCard(card);
    }

    public List<Card> getCards(){
        List<Card> cards = new ArrayList<>();
        for(CardsSpace cardsSpace : cardsSpaces.values())
            cards.addAll(cardsSpace.getCards());
        return cards;
    }

    public List<Card> getCards(CardType type){
        return cardsSpaces.get(type).getCards();
    }

    public ResourcesContainer getResourceContainer() {
        return resourceContainer;
    }
}
