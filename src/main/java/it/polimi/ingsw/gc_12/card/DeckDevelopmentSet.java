package it.polimi.ingsw.gc_12.card;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeckDevelopmentSet {

    //TODO: implement import of cards from JSON file
    private List<CardDevelopment> cards = new ArrayList<>();
    private Map<CardDevelopmentType, Map<Integer, DeckDevelopment>> developmentDecks = new HashMap<>();

    public DeckDevelopmentSet(List<CardDevelopment> cards, int rounds){

        this.cards = cards;

        //Creates DevelopmentDecks grouping them by type and period
        for(CardDevelopmentType type: CardDevelopmentType.values()){
            Map<Integer, DeckDevelopment> innerMap = new HashMap<>();
            for(int period = 1; period <= (rounds / 2); period++){
                DeckDevelopment deck = new DeckDevelopment(type, period);
                innerMap.put(period, deck);
            }
            developmentDecks.put(type, innerMap);
        }

        //Sorts the development cards in the relative decks
        for(CardDevelopment card : cards) {
            developmentDecks.get(card.getType()).get(card.getPeriod()).putCard(card);
        }
    }
}
