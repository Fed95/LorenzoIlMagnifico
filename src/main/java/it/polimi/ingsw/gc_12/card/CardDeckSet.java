package it.polimi.ingsw.gc_12.card;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CardDeckSet {

    //TODO: implement import of cards from JSON file
    private List<CardDevelopment> cards = new ArrayList<>();
    private Map<CardType, Map<Integer, CardDeck>> decks = new HashMap<>();

    public CardDeckSet(List<CardDevelopment> cards, int periods){

        this.cards = cards;

        //Creates DevelopmentDecks grouping them by type and period
        for(CardType type: CardType.values()){
            Map<Integer, CardDeck> innerMap = new HashMap<>();
            for(int period = 1; period <= (periods); period++){
                CardDeck cardDeck = new CardDeck(type, period);
                innerMap.put(period, cardDeck);
            }
            decks.put(type, innerMap);
        }

        //Sorts the development cards in the relative decks
        for(CardDevelopment card : cards) {
            decks.get(card.getType()).get(card.getPeriod()).putCard(card);
        }
    }

    public Map<CardType, Map<Integer, CardDeck>> getDecks(){
        return decks;
    }
}

