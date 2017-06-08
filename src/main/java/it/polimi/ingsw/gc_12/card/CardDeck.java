package it.polimi.ingsw.gc_12.card;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class CardDeck implements Serializable {

    private CardType cardType;
    private int period;
    private List<CardDevelopment> cards = new ArrayList<>();

    public CardDeck(CardType cardType, int period){
        this.cardType = cardType;
        this.period = period;
    }

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
