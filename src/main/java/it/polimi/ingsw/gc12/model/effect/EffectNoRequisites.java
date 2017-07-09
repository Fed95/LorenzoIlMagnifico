package it.polimi.ingsw.gc12.model.effect;

import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.event.Event;
import it.polimi.ingsw.gc12.model.event.EventPickCard;
import it.polimi.ingsw.gc12.misc.exception.ActionDeniedException;

//The player does not have to satisfy the requisites set by the personal board to take the card
public class EffectNoRequisites extends Effect{

    public EffectNoRequisites(Event event) {
        super(event);
    }

    @Override
    public void execute(Match match, Event event, boolean validation) throws ActionDeniedException {
        if(!validation)
            applyChange(event, true);
    }

    @Override
    public void discard(Match match, Event event) {
        applyChange(event, false);
     }

    private void applyChange(Event event, boolean bool){
        if(!(event instanceof EventPickCard))
            throw new IllegalStateException();
        ((EventPickCard) event).getCard().setNoRequisites(bool);
    }
}
