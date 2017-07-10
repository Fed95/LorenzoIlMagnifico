package it.polimi.ingsw.gc12.model.effect;

import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.event.Event;
import it.polimi.ingsw.gc12.model.event.EventPlaceFamilyMember;
import it.polimi.ingsw.gc12.model.event.EventServantsRequested;

/**
 * Sets the multiplier inside the specified event.
 * The Servants amount that will then be selected by the user will be divided by the multiplier.
 */

public class EffectServantsMultiplier extends Effect {

    private int value;

    public EffectServantsMultiplier(Event event, int value) {
        super(event);
        this.value = value;
    }

    @Override
    public void execute(Match match, Event event, boolean validation) {
        if(!validation) {
            if (event instanceof EventServantsRequested)
                ((EventServantsRequested) event).setMultiplier(value);
            else if (event instanceof EventPlaceFamilyMember)
                ((EventPlaceFamilyMember) event).setMultiplier(value);
            else
                throw new IllegalStateException("EffectServantsMultiplier: did not expect received event. Received: " + event.getClass().getSimpleName());
        }
    }

    @Override
    public void discard(Match match, Event event) {

    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return event.getClass().getSimpleName() + ": You need " + value + " servants to increase your FamilyMember's value by 1";
    }
}
