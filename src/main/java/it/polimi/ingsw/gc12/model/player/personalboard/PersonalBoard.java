package it.polimi.ingsw.gc12.model.player.personalboard;

import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.model.card.Card;
import it.polimi.ingsw.gc12.model.card.CardDevelopment;
import it.polimi.ingsw.gc12.model.card.CardType;
import it.polimi.ingsw.gc12.misc.exception.ActionNotAllowedException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class PersonalBoard implements Serializable{
    private Map<CardType, CardsSpace> cardsSpaces;
    private LeaderCardsSpace leaderCardsSpace = new LeaderCardsSpace();
    private transient ResourcesContainer resourcesContainer = new ResourcesContainer();
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
        if(!card.isNoRequisites() && cardSlot.getRequisites() != null)
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

    public List<Card> getCards(){
        List<Card> cards = new ArrayList<>();
        for(CardsSpace cardsSpace : cardsSpaces.values())
            cards.addAll(cardsSpace.getCards());
        return cards;
    }

    public List<Card> getCards(CardType type){
        return cardsSpaces.get(type).getCards();
    }

    public ResourcesContainer getResourcesContainer() {
        return resourcesContainer;
    }

    public BonusTile getBonusTile() {
        return bonusTile;
    }

    public LeaderCardsSpace getLeaderCardsSpace() {
        return leaderCardsSpace;
    }
}
