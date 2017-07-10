package it.polimi.ingsw.gc12.model.action;

import it.polimi.ingsw.gc12.model.player.familymember.FamilyMember;
import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.misc.exception.ActionNotAllowedException;
import it.polimi.ingsw.gc12.misc.exception.RequiredValueNotSatisfiedException;
import it.polimi.ingsw.gc12.model.board.occupiable.SpaceWork;
import it.polimi.ingsw.gc12.model.board.occupiable.SpaceWorkSingle;
import it.polimi.ingsw.gc12.model.board.occupiable.SpaceWorkZone;
import it.polimi.ingsw.gc12.model.player.resource.Servant;


/**
 * If the family member is coloured, checks if there is another family member with the same owner in the same workzone.
 * Checks if the family member satisfies the required value
 * If the spaceWork chosen is "Single", ith checks whether it is occupied
 * The placement itself happens int the ActionPlace
 */
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
    protected void canBeExecuted(Match match) throws RequiredValueNotSatisfiedException, ActionNotAllowedException {
        if(spaceWork instanceof SpaceWorkSingle)
            if(spaceWork.isOccupied() && !familyMember.isFriendly())
                throw new ActionNotAllowedException("This SpaceWork is already taken!");
        if(!spaceWork.isRequiredValueSatisfied(familyMember))
            throw new RequiredValueNotSatisfiedException();
        if(!spaceWorkZone.canBeOccupiedBy(familyMember))
            throw new ActionNotAllowedException("There is another member of your family working here already!");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ActionPlaceOnSpaceWork)) return false;

        ActionPlaceOnSpaceWork that = (ActionPlaceOnSpaceWork) o;

        return spaceWork != null ? spaceWork.equals(that.spaceWork) : that.spaceWork == null;
    }

    @Override
    public int hashCode() {
        return spaceWork != null ? spaceWork.hashCode() : 0;
    }
}
