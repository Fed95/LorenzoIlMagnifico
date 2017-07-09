package it.polimi.ingsw.gc12.model.effect;

import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.card.LeaderCard;
import it.polimi.ingsw.gc12.model.event.Event;
import it.polimi.ingsw.gc12.model.event.EventCopyLeaderCard;
import it.polimi.ingsw.gc12.misc.exception.ActionDeniedException;

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
            LeaderCard myCard = event.getPlayer().getPersonalBoard().getLeaderCardsSpace().getLeaderCard(cardId);

            EventCopyLeaderCard e = new EventCopyLeaderCard(event.getPlayer(), myCard);
            match.getActionHandler().update(e, match);
            match.notifyObserver(e);
        }
    }

    @Override
    public void discard(Match match, Event event) {
    }

    public int getCardId() {
        return cardId;
    }

    @Override
    public String toString() {
        return "Copy a Leader's card effect";
    }
}
