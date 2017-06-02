package it.polimi.ingsw.gc_12.action;

import it.polimi.ingsw.gc_12.FamilyMember;
import it.polimi.ingsw.gc_12.event.Event;
import it.polimi.ingsw.gc_12.event.EventPlaceFamilyMember;
import it.polimi.ingsw.gc_12.occupiables.SpaceMarket;

public class ActionPlaceOnMarket extends ActionPlace {

    private SpaceMarket spaceMarket;

    public ActionPlaceOnMarket(FamilyMember familyMember, SpaceMarket spaceMarket) {
        super(familyMember.getOwner(), familyMember);
        this.spaceMarket = spaceMarket;
    }

    public boolean canBeExecuted(Event event) throws RuntimeException {
        player.getEffectHandler().executeEffects(event);

        if(spaceMarket.isOccupied())
                throw new RuntimeException("This SpaceMarket is already taken!");
        if(!spaceMarket.isRequiredValueSatisfied(familyMember))
            throw new RuntimeException("Your FamilyMember does not satisfy the required value for this placement!");
        return true;
    }

    @Override
    public void start() throws RuntimeException {
        Event event = new EventPlaceFamilyMember(this.player, spaceMarket, familyMember);

        if(canBeExecuted(event)) {
            familyMember.setBusy(true);
            spaceMarket.placeFamilyMember(familyMember);
        }else
            player.getEffectHandler().discardEffects(event);
    }

}
