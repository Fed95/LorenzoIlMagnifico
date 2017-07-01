package it.polimi.ingsw.gc_12.effect;

import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.event.Event;
import it.polimi.ingsw.gc_12.exceptions.ActionDeniedException;

/**
 * Created by feder on 2017-06-30.
 */
public class EffectSetFamilyMemberValue extends Effect {

    private int value;

    public EffectSetFamilyMemberValue(Event event, int value) {
        super(event);
        this.value = value;
    }

    @Override
    public void execute(Match match, Event event, boolean validation) throws ActionDeniedException {

    }

    @Override
    public void discard(Match match, Event event) {

    }
}
