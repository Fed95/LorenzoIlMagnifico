package it.polimi.ingsw.gc_12.effect;

import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.event.Event;
import it.polimi.ingsw.gc_12.event.EventPlaceFamilyMember;

import java.io.IOException;

//TODO: IMPROVE DISCARD
public class EffectDenyEffect extends Effect {

    private Match match;

    public EffectDenyEffect(Event event) {
        super(event);
    }

    @Override
    public void execute(Match match, Event e) throws RuntimeException, IOException {
        this.match = match;
        if(!(e instanceof EventPlaceFamilyMember))
            throw new RuntimeException("EffectDenyEffect: this effect was activated unexpectedly");
        EventPlaceFamilyMember event = (EventPlaceFamilyMember) e;
        for(Effect effect : event.getOccupiable().getEffects())
            effect.discard(event);
    }

    @Override
    public void discard(Event e) throws RuntimeException, IOException {
        if(match == null)
            throw new RuntimeException("EffectDenyEffect: trying to discard the effect when not executed");
        if(!(e instanceof EventPlaceFamilyMember))
            throw new RuntimeException("EffectDenyEffect: this effect was discarded unexpectedly");
        EventPlaceFamilyMember event = (EventPlaceFamilyMember) e;
        for(Effect effect : event.getOccupiable().getEffects())
            effect.execute(match, event);
    }
}
