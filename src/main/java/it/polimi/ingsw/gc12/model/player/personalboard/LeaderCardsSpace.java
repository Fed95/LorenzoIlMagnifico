package it.polimi.ingsw.gc12.model.player.personalboard;

import it.polimi.ingsw.gc12.model.card.LeaderCard;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This class representing the card leader container
 */
public class LeaderCardsSpace implements Serializable {

    private List<LeaderCard> cards = new ArrayList<>();

    public LeaderCardsSpace() {
    }

    public List<LeaderCard> getCards() {
        return cards;
    }

    public List<LeaderCard> getPlayedLeaderCards(){
        List<LeaderCard> cards = new ArrayList<>();
        for(LeaderCard card : this.cards)
            if(card.isPlayed())
                cards.add(card);
        return cards;
    }

    public LeaderCard getLeaderCard(int cardId){
        for(LeaderCard card : cards)
            if(card.getId() == cardId)
                return card;
        throw new IllegalStateException("Trying to retrieve a LeaderCard you don't have");
    }

    public List<LeaderCard> getUsefulLeaderCards(){
        List<LeaderCard> cards = new ArrayList<>();
        for(LeaderCard card : this.cards)
            if(card.isPlayed() && !card.isActivated())
                cards.add(card);
        return cards;
    }

    public void newTurn(){
        for(LeaderCard card : cards)
            if(card.isPlayed())
                card.setActivated(false);
    }
}