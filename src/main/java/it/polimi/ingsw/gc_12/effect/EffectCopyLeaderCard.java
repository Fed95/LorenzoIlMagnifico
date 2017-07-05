package it.polimi.ingsw.gc_12.effect;

import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.card.LeaderCard;
import it.polimi.ingsw.gc_12.event.Event;
import it.polimi.ingsw.gc_12.event.EventCopyLeaderCard;
import it.polimi.ingsw.gc_12.exceptions.ActionDeniedException;

public class EffectCopyLeaderCard extends Effect {

    private int cardId;

    public EffectCopyLeaderCard(Event event, int cardId) {
        super(event);
        this.cardId = cardId;
    }

    public EffectCopyLeaderCard(Event event) {
        super(event);
    }

    @Override
    public void execute(Match match, Event event, boolean validation) throws ActionDeniedException {
        if(!validation) {
            //Retrieves th original LeaderCard containing the CopyLeaderCard effect
            LeaderCard myCard = event.getPlayer().getPersonalBoard().getLeaderCard(cardId);

            EventCopyLeaderCard e = new EventCopyLeaderCard(event.getPlayer(), myCard);
            match.getActionHandler().update(e, match);
            match.notifyObserver(e);
        }
    }

    @Override
    public void discard(Match match, Event event) {

    }

    @Override
    public String toString() {
        return "Copy a Leader's card effect";
    }
}
