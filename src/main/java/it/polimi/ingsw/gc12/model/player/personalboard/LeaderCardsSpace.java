package it.polimi.ingsw.gc12.model.player.personalboard;

import it.polimi.ingsw.gc12.model.card.LeaderCard;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LeaderCardsSpace implements Serializable {

    private List<LeaderCard> cards = new ArrayList<>();

    public LeaderCardsSpace() {
    }

    public List<LeaderCard> getCards() {
        return cards;
    }

    public void newTurn(){
        for(LeaderCard card : cards)
            if(card.isPlayed())
                card.setActivated(false);
    }
}