package it.polimi.ingsw.gc12.model.action;

import it.polimi.ingsw.gc12.model.player.familymember.FamilyMember;
import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.misc.exception.ActionNotAllowedException;
import it.polimi.ingsw.gc12.misc.exception.RequiredValueNotSatisfiedException;
import it.polimi.ingsw.gc12.model.board.occupiable.SpaceMarket;
import it.polimi.ingsw.gc12.model.player.resource.Servant;

/**
 * The only check specific to a placement on the Council Palace is the familyMember's value
 * and the "SpaceMarket occupied" check
 * The placement itself happens int the ActionPlace
 */
public class ActionPlaceOnMarket extends ActionPlace {

    private SpaceMarket spaceMarket;

    public ActionPlaceOnMarket(Player player, FamilyMember familyMember, SpaceMarket spaceMarket, Servant servant, boolean complete) {
        super(player, familyMember, spaceMarket, servant, complete);
        this.spaceMarket = spaceMarket;
    }

    public ActionPlaceOnMarket(Player player, FamilyMember familyMember, SpaceMarket spaceMarket) {
        this(player, familyMember, spaceMarket, new Servant(0), false);
    }

    @Override
    protected void setup(Match match) {}

    @Override
    protected void canBeExecuted(Match match) throws RequiredValueNotSatisfiedException, ActionNotAllowedException {
        if(spaceMarket.isOccupied() && !familyMember.isFriendly())
            throw new ActionNotAllowedException("This SpaceMarket is already taken!");
        if(!spaceMarket.isRequiredValueSatisfied(familyMember))
            throw new ActionNotAllowedException("Your FamilyMember does not satisfy the required value for this placement!");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ActionPlaceOnMarket)) return false;

        ActionPlaceOnMarket that = (ActionPlaceOnMarket) o;

        return spaceMarket != null ? spaceMarket.equals(that.spaceMarket) : that.spaceMarket == null;
    }

    @Override
    public int hashCode() {
        return spaceMarket != null ? spaceMarket.hashCode() : 0;
    }
}
