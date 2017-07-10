package it.polimi.ingsw.gc12.model.action;

import it.polimi.ingsw.gc12.model.player.familymember.FamilyMember;
import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.misc.exception.RequiredValueNotSatisfiedException;
import it.polimi.ingsw.gc12.model.board.occupiable.CouncilPalace;
import it.polimi.ingsw.gc12.model.player.resource.Servant;

/**
 * The only check specific to a placement on the Council Palace is the familyMember's value
 */
public class ActionPlaceOnCouncil extends ActionPlace {

    private CouncilPalace councilPalace;

    public ActionPlaceOnCouncil(Player player, FamilyMember familyMember, CouncilPalace councilPalace, Servant servant, boolean complete) {
        super(player, familyMember, councilPalace, servant, complete);
        this.councilPalace = councilPalace;
    }

    public ActionPlaceOnCouncil(Player player, FamilyMember familyMember, CouncilPalace councilPalace) {
        this(player, familyMember, councilPalace, new Servant(0), false);
    }

    @Override
    protected void setup(Match match) {}

    @Override
    protected void canBeExecuted(Match match) throws RequiredValueNotSatisfiedException {
        if (!councilPalace.isRequiredValueSatisfied(familyMember))
            throw new RequiredValueNotSatisfiedException();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ActionPlaceOnCouncil)) return false;

        ActionPlaceOnCouncil that = (ActionPlaceOnCouncil) o;

        return councilPalace != null ? councilPalace.equals(that.councilPalace) : that.councilPalace == null;
    }

    @Override
    public int hashCode() {
        return councilPalace != null ? councilPalace.hashCode() : 0;
    }
}