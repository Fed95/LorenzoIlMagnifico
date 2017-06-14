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
        this.spaceWork = spaceWork;
    }

    public ActionPlaceOnSpaceWork(FamilyMember familyMember, SpaceWork spaceWork) {
        this(familyMember, new Servant(0), spaceWork);
    }

    @Override
    protected void setup(Match match) {
        familyMember = getRealFamilyMember(match);
        spaceWorkZone = getRealSpaceWorkZone(match);
        spaceWork = getRealSpaceWork(spaceWorkZone);
        occupiable = spaceWork;
    }

    @Override
    protected void canBeExecuted(Match match) throws RequiredValueNotSatisfiedException {
        if(spaceWork instanceof SpaceWorkSingle)
            if(spaceWork.isOccupied())
                throw new RuntimeException("This SpaceWork is already taken!");
        if(!spaceWork.isRequiredValueSatisfied(familyMember))
            throw new RequiredValueNotSatisfiedException();
        if(!spaceWorkZone.canBeOccupiedBy(familyMember))
            throw new RuntimeException("There is another member of your family working here already!");
    }

    @Override
    protected void execute(Match match) throws IOException {
        match.placeFamilyMember(spaceWork, familyMember);
    }

    private SpaceWorkZone getRealSpaceWorkZone(Match match){
        return match.getBoard().getSpaceWorkZones().get(spaceWork.getWorkType());
    }

    private SpaceWork getRealSpaceWork(SpaceWorkZone spaceWorkZone){
        List<SpaceWork> spaceWorks = spaceWorkZone.getSpaceWorks();
        return (spaceWork instanceof SpaceWorkSingle) ? spaceWorks.get(0) : spaceWorks.get(1);
    }
}
