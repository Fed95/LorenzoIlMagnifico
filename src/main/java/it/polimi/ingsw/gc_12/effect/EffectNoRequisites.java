package it.polimi.ingsw.gc_12.effect;

import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.card.CardType;
import it.polimi.ingsw.gc_12.event.Event;
import it.polimi.ingsw.gc_12.event.EventPickCard;
import it.polimi.ingsw.gc_12.exceptions.ActionDeniedException;

//TODO: NEEDS TESTING
//The player does not have to satisfy the requisites set by the personal board to take the card
public class EffectNoRequisites extends Effect{

    public EffectNoRequisites(Event event) {
        super(event);
    }

    @Override
    public void execute(Match match, Event event, boolean validation) throws ActionDeniedException {
        if(!(event instanceof EventPickCard))
            throw new IllegalStateException();
        ((EventPickCard) event).getCard().setNoRequisites(true);
    }

    @Override
    public void discard(Match match, Event event) {

    }
}
