package it.polimi.ingsw.gc_12.card;

import java.util.ArrayList;
import java.util.List;


public class CardDeck {

    private CardType cardType;
    private int period;
    private List<CardDevelopment> cards = new ArrayList<>();

    public CardDeck(CardType cardType, int period){
        this.cardType = cardType;
        this.period = period;
    }

    public void putCard(CardDevelopment card){
        cards.add(card);
    }

    public List<CardDevelopment> getCards(){
        return cards;
    }

    public CardDevelopment pickCard(){
        CardDevelopment card =  cards.get(0);
        cards.remove(0);
        return card;
    }

    public CardType getCardDevelopmentType() {
        return cardType;
    }

    public int getPeriod() {
        return period;
    }
}
