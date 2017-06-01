package it.polimi.ingsw.gc_12.action;

import it.polimi.ingsw.gc_12.FamilyMember;
import it.polimi.ingsw.gc_12.event.Event;
import it.polimi.ingsw.gc_12.event.EventPlaceFamilyMember;
import it.polimi.ingsw.gc_12.occupiables.CouncilPalace;

public class ActionPlaceOnCouncil extends ActionPlace {

    private FamilyMember familyMember;
    private CouncilPalace councilPalace;

    public ActionPlaceOnCouncil(FamilyMember familyMember, CouncilPalace councilPalace) {
        super(familyMember.getOwner(), familyMember);
        this.familyMember = familyMember;
        this.councilPalace = councilPalace;
    }

    public boolean canBeExecuted(Event event) throws RuntimeException {
        player.getEffectHandler().executeEffects(event);

        if (!councilPalace.isRequiredValueSatisfied(familyMember))
            throw new RuntimeException("Your FamilyMember does not satisfy the required value for this placement!");
        return true;
    }

    @Override
    public void start() throws RuntimeException {
        Event event = new EventPlaceFamilyMember(this.player, councilPalace, familyMember);

        if (canBeExecuted(event)) {
            familyMember.setBusy(true);
            councilPalace.placeFamilyMember(familyMember);
        }else
            player.getEffectHandler().discardEffects(event);
    }
}