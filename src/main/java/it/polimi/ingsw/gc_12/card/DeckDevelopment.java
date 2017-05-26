package it.polimi.ingsw.gc_12.card;

import it.polimi.ingsw.gc_12.JSON.AskCards;
import it.polimi.ingsw.gc_12.JSON.JsonCard;

import java.util.ArrayList;
import java.util.List;


public class DeckDevelopment {

    private CardDevelopmentType cardDevelopmentType;
    private int period;
    private List<CardDevelopment> cards = new ArrayList<>();

    public DeckDevelopment(CardDevelopmentType cardDevelopmentType, int period){
        this.cardDevelopmentType = cardDevelopmentType;
        this.period = period;
    }

    public void putCard(CardDevelopment card){
        cards.add(card);
    }

    public List<CardDevelopment> getCards(){
        return cards;
    }

    public CardDevelopment pickCard(){
        return cards.get(0);
    }

    public CardDevelopmentType getCardDevelopmentType() {
        return cardDevelopmentType;
    }

    public int getPeriod() {
        return period;
    }

    /*
    public void fillDeck() {
        AskCards askCards = new AskCards();
        List<Card> cards = new ArrayList<>(askCards.buildCards());
        JsonCard jsonobj = new JsonCard("card");
        jsonobj.createCards(cards);
        cards = jsonobj.getCards();
    }
    */
}
