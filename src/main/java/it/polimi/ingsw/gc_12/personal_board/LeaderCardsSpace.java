package it.polimi.ingsw.gc_12.personal_board;

import it.polimi.ingsw.gc_12.card.LeaderCard;

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
}
