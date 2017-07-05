package it.polimi.ingsw.gc_12.effect;

import it.polimi.ingsw.gc_12.FamilyMember;
import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.event.Event;
import it.polimi.ingsw.gc_12.exceptions.ActionDeniedException;

public class EffectFriendlyFamilyMembers extends Effect {

    public EffectFriendlyFamilyMembers(Event event) {
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
        for (FamilyMember familyMember : event.getPlayer().getFamilyMembers().values())
            familyMember.setFriendly(bool);
    }

    @Override
    public String toString() {
        return event.getClass().getSimpleName() + ": You can place your FamilyMembers on occupied actionSpaces";
    }
}
