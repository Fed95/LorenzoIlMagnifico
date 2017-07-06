package it.polimi.ingsw.gc12.model.effect;

import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.event.Event;
import it.polimi.ingsw.gc12.misc.exception.ActionDeniedException;

public class EffectPlacementDenied extends Effect {

    public EffectPlacementDenied(Event event) {
        super(event);
    }

    @Override
    public void execute(Match match, Event event, boolean validation) throws ActionDeniedException {
        if(!validation)
            throw new ActionDeniedException("EffectPlacementDenied");
    }

    @Override
    public void discard(Match match, Event event) {}

    @Override
    public String toString() {
        return event.getClass().getSimpleName() + ": " + this.getClass().getSimpleName();
    }
}
