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

    public ActionPlaceOnSpaceWork(Player player, FamilyMember familyMember, SpaceWork spaceWork, Servant servant) {
        super(player, familyMember, spaceWork, servant);
        this.spaceWork = spaceWork;
    }

    public ActionPlaceOnSpaceWork(Player player, FamilyMember familyMember, SpaceWork spaceWork) {
        this(player, familyMember, spaceWork, new Servant(0));
    }

    @Override
    public String toString() {
        return "ActionPlaceOnSpaceWork{" +
                "player=" + player +
                ", spaceWorkZone=" + spaceWorkZone +
                ", spaceWork=" + spaceWork +
                ", familyMember=" + familyMember +
                ", servant=" + servant +
                '}';
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
    }
}
