package it.polimi.ingsw.gc_12.action;

import it.polimi.ingsw.gc_12.FamilyMember;
import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.event.EventPlacementEnded;
import it.polimi.ingsw.gc_12.exceptions.RequiredValueNotSatisfiedException;
import it.polimi.ingsw.gc_12.occupiables.SpaceWork;
import it.polimi.ingsw.gc_12.occupiables.SpaceWorkSingle;
import it.polimi.ingsw.gc_12.occupiables.SpaceWorkZone;
import it.polimi.ingsw.gc_12.resource.Servant;

import java.io.IOException;

public class ActionPlaceOnSpaceWork extends ActionPlace {

    private SpaceWorkZone spaceWorkZone;
    private SpaceWork spaceWork;

    public ActionPlaceOnSpaceWork(Player player, FamilyMember familyMember, SpaceWork spaceWork, Servant servant, boolean complete) {
        super(player, familyMember, spaceWork, servant, complete);
        this.spaceWork = spaceWork;
    }

    public ActionPlaceOnSpaceWork(Player player, FamilyMember familyMember, SpaceWork spaceWork) {
        this(player, familyMember, spaceWork, new Servant(0), false);
    }

    @Override
    protected void setup(Match match) {
        spaceWorkZone = match.getBoard().getSpaceWorkZones().get(spaceWork.getWorkType());
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
        EventPlacementEnded event = new EventPlacementEnded(player);
        match.getActionHandler().update(event);
        match.notifyObserver(event);
    }
}
