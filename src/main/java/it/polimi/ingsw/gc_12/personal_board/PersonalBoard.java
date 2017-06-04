package it.polimi.ingsw.gc_12.personal_board;

import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.card.Card;
import it.polimi.ingsw.gc_12.json.loader.LoaderCardsSpace;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.card.CardDevelopment;
import it.polimi.ingsw.gc_12.card.CardType;
import it.polimi.ingsw.gc_12.resource.ResourceType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PersonalBoard {
    private Map<CardType, CardsSpace> cardsSpaces;
    private Map<ResourceType, ResourceContainer> resourceContainers = new HashMap<>();

    public PersonalBoard(){
        cardsSpaces = new LoaderCardsSpace().get(Match.instance());
        for(ResourceType resourceType : ResourceType.values())
            resourceContainers.put(resourceType, new ResourceContainer(resourceType));
    }

    public void canPlaceCard(Player owner, CardDevelopment card) {
        //Can throw an exception
        CardSlot cardSlot = cardsSpaces.get(card.getType()).getFirstFreeSlot();
        //Can throw exceptions
        owner.hasResources(cardSlot.getRequisites());

        cardSlot.placeCard(card);
    }

    public List<Card> getCards(){
        List<Card> cards = new ArrayList<>();
        for(CardsSpace cardsSpace : cardsSpaces.values())
            cards.addAll(cardsSpace.getCards());
        return cards;
    }
}
