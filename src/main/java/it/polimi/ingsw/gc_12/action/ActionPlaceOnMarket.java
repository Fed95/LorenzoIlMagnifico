package it.polimi.ingsw.gc_12.action;

import it.polimi.ingsw.gc_12.FamilyMember;
import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.event.EventPlacementEnded;
import it.polimi.ingsw.gc_12.exceptions.ActionNotAllowedException;
import it.polimi.ingsw.gc_12.exceptions.RequiredValueNotSatisfiedException;
import it.polimi.ingsw.gc_12.occupiables.SpaceMarket;
import it.polimi.ingsw.gc_12.resource.Servant;

import java.util.Collections;

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
        if(spaceMarket.isOccupied())
            throw new ActionNotAllowedException("This SpaceMarket is already taken!");
        if(!spaceMarket.isRequiredValueSatisfied(familyMember))
            throw new ActionNotAllowedException("Your FamilyMember does not satisfy the required value for this placement!");
    }

}
