package it.polimi.ingsw.gc_12.effect;

import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.event.Event;
import it.polimi.ingsw.gc_12.exceptions.ActionDeniedException;

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
