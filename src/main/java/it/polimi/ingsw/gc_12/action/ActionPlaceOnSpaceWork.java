package it.polimi.ingsw.gc_12.action;

import it.polimi.ingsw.gc_12.FamilyMember;
import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.effect.Effect;
import it.polimi.ingsw.gc_12.event.Event;
import it.polimi.ingsw.gc_12.event.EventPlaceFamilyMember;
import it.polimi.ingsw.gc_12.exceptions.RequiredValueNotSatisfiedException;
import it.polimi.ingsw.gc_12.occupiables.SpaceWork;
import it.polimi.ingsw.gc_12.occupiables.SpaceWorkSingle;
import it.polimi.ingsw.gc_12.occupiables.SpaceWorkZone;

import java.io.IOException;
import java.util.List;

public class ActionPlaceOnSpaceWork extends ActionPlace {

    private SpaceWorkZone spaceWorkZone;
    private SpaceWork spaceWork;

    public ActionPlaceOnSpaceWork(FamilyMember familyMember, SpaceWorkZone spaceWorkZone, SpaceWork spaceWork) {
        super(familyMember);
        this.spaceWorkZone = spaceWorkZone;
        this.spaceWork = spaceWork;
    }

    private void canBeExecuted() throws RuntimeException, RequiredValueNotSatisfiedException {

        if(spaceWork instanceof SpaceWorkSingle)
            if(spaceWork.isOccupied())
                throw new RuntimeException("This SpaceWork is already taken!");
        if(!spaceWork.isRequiredValueSatisfied(familyMember))
            throw new RequiredValueNotSatisfiedException();
        if(!spaceWorkZone.canBeOccupiedBy(familyMember))
            throw new RuntimeException("There is another member of your family working here already!");

    }

    public void start(Match match) throws RuntimeException, IOException, RequiredValueNotSatisfiedException {
    	Player player = match.getBoard().getTrackTurnOrder().getCurrentPlayer();
        familyMember = getRealFamilyMember(match);
        spaceWork = getRealSpaceWork(match);
        Event event = new EventPlaceFamilyMember(player, spaceWork, familyMember);

        //Can throw exceptions (in which case effects are discarded directly in EffectHandler)
        List<Effect> executedEffects = player.getEffectHandler().executeEffects(event);
        try{
            canBeExecuted();
            match.placeFamilyMember(spaceWork, familyMember);
        }catch(Exception e) {
            player.getEffectHandler().discardEffects(executedEffects, event);
            throw e;
        }
    }

    private SpaceWork getRealSpaceWork(Match match){
        List<SpaceWork> spaceWorks = match.getBoard().getSpaceWorkZones().get(spaceWorkZone.getType()).getSpaceWorks();
        return (spaceWork instanceof SpaceWorkSingle) ? spaceWorks.get(0) : spaceWorks.get(1);
    }
}
