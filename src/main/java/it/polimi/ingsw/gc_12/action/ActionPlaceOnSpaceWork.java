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
import it.polimi.ingsw.gc_12.resource.Servant;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.List;

public class ActionPlaceOnSpaceWork extends ActionPlace {

    private SpaceWorkZone spaceWorkZone;
    private SpaceWork spaceWork;

    public ActionPlaceOnSpaceWork(FamilyMember familyMember, Servant servant, SpaceWork spaceWork) {
        super(familyMember, servant);
        this.spaceWorkZone = spaceWorkZone;
        this.spaceWork = spaceWork;
    }

    public ActionPlaceOnSpaceWork(FamilyMember familyMember, SpaceWork spaceWork) {
        this(familyMember, new Servant(0), spaceWork);
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

    @Override
    public void start(Match match) throws RuntimeException, IOException, RequiredValueNotSatisfiedException, RemoteException {

    	Player player = match.getBoard().getTrackTurnOrder().getCurrentPlayer();
        familyMember = getRealFamilyMember(match);
        spaceWorkZone = getRealSpaceWorkZone(match);
        spaceWork = getRealSpaceWork(spaceWorkZone);
        Event event = new EventPlaceFamilyMember(player, spaceWork, familyMember);

        //Can throw exceptions (in which case effects are discarded directly in EffectHandler)
        List<Effect> executedEffects = player.getEffectHandler().executeEffects(match, event);
        try{
            canBeExecuted();
            match.placeFamilyMember(spaceWork, familyMember);
        }catch(Exception e) {
            player.getEffectHandler().discardEffects(executedEffects, event);
            throw e;
        }
    }

    private SpaceWorkZone getRealSpaceWorkZone(Match match){
        return match.getBoard().getSpaceWorkZones().get(spaceWork.getWorkType());
    }
    private SpaceWork getRealSpaceWork(SpaceWorkZone spaceWorkZone){
        List<SpaceWork> spaceWorks = spaceWorkZone.getSpaceWorks();
        return (spaceWork instanceof SpaceWorkSingle) ? spaceWorks.get(0) : spaceWorks.get(1);
    }
}
