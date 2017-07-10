package it.polimi.ingsw.gc12.model.card;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CardDeckSet {
    /**
     * Contains all the decks of the game 3 decks for each CardType
     */
    private List<CardDevelopment> cards = new ArrayList<>();
    private Map<CardType, Map<Integer, CardDeck>> decks = new HashMap<>();

    /**
     * Constructor that Creates DevelopmentDecks grouping them by type and period and
     * sorts the development cards in the relative decks
     * @param cards list of all the cards
     * @param periods periods
     */
    public CardDeckSet(List<CardDevelopment> cards, int periods){

        for(CardDevelopment card : cards)
            this.cards.add(card);

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
        for(CardDevelopment card : this.cards) {
            decks.get(card.getType()).get(card.getPeriod()).putCard(card);
        }
    }

    public void shuffle(){
        for(Map<Integer, CardDeck> map : decks.values())
            for (CardDeck deck : map.values())
                deck.shuffle();
    }

    public Map<CardType, Map<Integer, CardDeck>> getDecks(){
        return decks;
    }

    public Map<Integer, CardDeck> getDeck(CardType cardType){
        return getDecks().get(cardType);
    }
}

