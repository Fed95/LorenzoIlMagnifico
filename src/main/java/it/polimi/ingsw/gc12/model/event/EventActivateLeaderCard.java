package it.polimi.ingsw.gc12.model.event;

import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.model.card.LeaderCard;
import it.polimi.ingsw.gc12.view.client.ClientHandler;

import java.util.ArrayList;
import java.util.Collections;

public class EventActivateLeaderCard extends Event {

    private int cardId;
    private LeaderCard card;

    //Used at runtime
    public EventActivateLeaderCard(Player player, LeaderCard card) {
        super(player);
        this.card = card;
    }

    //used in Json
    public EventActivateLeaderCard(int cardId) {
        super();
        this.cardId = cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    @Override
    public void executeClientSide(ClientHandler client) {
        client.getMatch().updateResources(new ArrayList<>(Collections.singletonList(player)));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EventActivateLeaderCard)) return false;
        if (!super.equals(o)) return false;

        //"this" is the event created at runtime (has card)
        //"that" is the event found in the card (has cardId)
        EventActivateLeaderCard that = (EventActivateLeaderCard) o;

        return(card.getId() == that.cardId);
    }

    @Override
    public int hashCode() {
        int result = cardId;
        result = 31 * result + (card != null ? card.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        if(player == null)
            return "Activate LeaderCard";

        StringBuilder sb = new StringBuilder();
        sb.append(System.getProperty("line.separator"));
        sb.append(player.getName() + " activated the following LeaderCard: ").append(System.getProperty("line.separator"));
        sb.append(card);
        return sb.toString();
    }
}
