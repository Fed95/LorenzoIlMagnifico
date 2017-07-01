package it.polimi.ingsw.gc_12.personal_board;

import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.card.Card;
import it.polimi.ingsw.gc_12.card.CardDevelopment;
import it.polimi.ingsw.gc_12.card.CardType;
import it.polimi.ingsw.gc_12.card.LeaderCard;
import it.polimi.ingsw.gc_12.exceptions.ActionNotAllowedException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class PersonalBoard implements Serializable{
    private Map<CardType, CardsSpace> cardsSpaces;
    private LeaderCardsSpace leaderCardsSpace = new LeaderCardsSpace();
    private transient ResourcesContainer resourceContainer = new ResourcesContainer();
    private transient BonusTile bonusTile;

    public PersonalBoard(){ }

    public boolean canPlaceCard(Player owner, CardDevelopment card) {
        //Can throw an exception
        CardSlot cardSlot;
        try {
            cardSlot = cardsSpaces.get(card.getType()).getFirstFreeSlot();
        } catch (ActionNotAllowedException e) {
            return false;
        }
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
        try {
            cardsSpaces.get(card.getType()).getFirstFreeSlot().placeCard(card);
        } catch (ActionNotAllowedException e) {
            throw new IllegalStateException("Placing card in a full cardSpace");
        }
    }

    public List<LeaderCard> getLeaderCards(){
        return leaderCardsSpace.getCards();
    }

    public List<LeaderCard> getUsefulLeaderCards(){
        List<LeaderCard> cards = new ArrayList<>();
        for(LeaderCard card : leaderCardsSpace.getCards())
            if(card.isPlayed() && !card.isActivated())
                cards.add(card);
        return cards;
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

    public BonusTile getBonusTile() {
        return bonusTile;
    }

    public LeaderCardsSpace getLeaderCardsSpace() {
        return leaderCardsSpace;
    }
}
