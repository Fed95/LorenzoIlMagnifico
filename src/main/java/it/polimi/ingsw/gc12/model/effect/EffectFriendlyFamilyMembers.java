package it.polimi.ingsw.gc12.model.effect;

import it.polimi.ingsw.gc12.model.player.familymember.FamilyMember;
import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.event.Event;
import it.polimi.ingsw.gc12.misc.exception.ActionDeniedException;


//This effect allows the specified family member to be placed on occupied Occupiables
//by setting its "friendly" boolean to true.
//When performing a placement the boolean is checked
public class EffectFriendlyFamilyMembers extends Effect {

    public EffectFriendlyFamilyMembers(Event event) {
        super(event);
    }

    @Override
    public void execute(Match match, Event event, boolean validation) throws ActionDeniedException {
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
