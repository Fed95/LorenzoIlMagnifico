package it.polimi.ingsw.gc12.model.card;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class represent a card deck of a period, contains all the card of a certain type of a certain period
 */
public class CardDeck implements Serializable {

    private CardType cardType;
    private int period;
    private List<CardDevelopment> cards = new ArrayList<>();

    /**
     * Constructor
     * @param cardType type of the card deck
     * @param period period of the card deck
     */
    public CardDeck(CardType cardType, int period){
        this.cardType = cardType;
        this.period = period;
    }

    /**
     * Shuffle the cards
     */
    public void shuffle(){
        Collections.shuffle(cards);
    }

    public void putCard(CardDevelopment card){
        cards.add(card);
    }

    public List<CardDevelopment> getCards(){
        return cards;
    }

    public CardDevelopment pickCard(){
        return cards.remove(0);
    }

    public CardType getCardDevelopmentType() {
        return cardType;
    }

    public int getPeriod() {
        return period;
    }
}
