package it.polimi.ingsw.gc_12.personalBoard;

import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.card.CardDevelopment;
import it.polimi.ingsw.gc_12.card.CardType;
import it.polimi.ingsw.gc_12.exceptions.CannotPlaceCardException;
import it.polimi.ingsw.gc_12.exceptions.NotEnoughResourcesException;
import it.polimi.ingsw.gc_12.resource.ResourceType;

import java.util.HashMap;
import java.util.Map;


public class PersonalBoard {
    private Map<CardType, CardsSpace> cardsSpaces = new HashMap<>();
    private Map<ResourceType, ResourceContainer> resourceContainers = new HashMap<>();

    public PersonalBoard(){
        for(CardType type : CardType.values())
            cardsSpaces.put(type, new CardsSpace(type));
        for(ResourceType resourceType : ResourceType.values())
            resourceContainers.put(resourceType, new ResourceContainer(resourceType));
    }

    public void canPlaceCard(Player owner, CardDevelopment card) throws CannotPlaceCardException, NotEnoughResourcesException {
        CardSlot cardSlot = cardsSpaces.get(card.getType()).getFirstFreeSlot();
        if(!owner.hasResources(cardSlot.getRequisites()))
            throw new NotEnoughResourcesException("You do not have the required resources for this card placement");

        //Throws exception if all slots are taken
        cardSlot.placeCard(card);
    }
}
