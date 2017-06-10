package it.polimi.ingsw.gc_12.action;

import it.polimi.ingsw.gc_12.FamilyMember;
import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.event.Event;
import it.polimi.ingsw.gc_12.event.EventPlaceFamilyMember;
import it.polimi.ingsw.gc_12.occupiables.SpaceWork;
import it.polimi.ingsw.gc_12.occupiables.SpaceWorkSingle;
import it.polimi.ingsw.gc_12.occupiables.SpaceWorkZone;

import java.util.List;

public class ActionPlaceOnSpaceWork extends ActionPlace {

    private SpaceWorkZone spaceWorkZone;
    private SpaceWork spaceWork;

    public ActionPlaceOnSpaceWork(FamilyMember familyMember, SpaceWorkZone spaceWorkZone, SpaceWork spaceWork) {
        super(familyMember);
        this.spaceWorkZone = spaceWorkZone;
        this.spaceWork = spaceWork;
    }

    public boolean canBeExecuted(Player player, Event event) throws RuntimeException {
        player.getEffectHandler().executeEffects(event);

        if(spaceWork instanceof SpaceWorkSingle)
            if(spaceWork.isOccupied())
                throw new RuntimeException("This SpaceWork is already taken!");
        if(!spaceWork.isRequiredValueSatisfied(familyMember))
            throw new RuntimeException("Your FamilyMember does not satisfy the required value for this placement!");
        if(!spaceWorkZone.canBeOccupiedBy(familyMember))
            throw new RuntimeException("There is another member of your family working here already!");

        return true;
    }

    @Override
    public void start(Match match) throws RuntimeException {
    	Player player = match.getBoard().getTrackTurnOrder().getCurrentPlayer();
        familyMember = getRealFamilyMember(match);
        spaceWork = getRealSpaceWork(match);
        Event event = new EventPlaceFamilyMember(player, spaceWork, familyMember);

        if(canBeExecuted(player, event)) {
            match.placeFamilyMember(spaceWork, familyMember);
        }else
            player.getEffectHandler().discardEffects(event);
    }

    private SpaceWork getRealSpaceWork(Match match){
        List<SpaceWork> spaceWorks = match.getBoard().getSpaceWorkZones().get(spaceWorkZone.getType()).getSpaceWorks();
        return (spaceWork instanceof SpaceWorkSingle) ? spaceWorks.get(0) : spaceWorks.get(1);
    }
}
