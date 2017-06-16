package it.polimi.ingsw.gc_12.action;

import it.polimi.ingsw.gc_12.FamilyMember;
import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.MatchInstance;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.effect.Effect;
import it.polimi.ingsw.gc_12.event.Event;
import it.polimi.ingsw.gc_12.event.EventPlaceFamilyMember;
import it.polimi.ingsw.gc_12.exceptions.RequiredValueNotSatisfiedException;
import it.polimi.ingsw.gc_12.occupiables.SpaceMarket;
import it.polimi.ingsw.gc_12.resource.Servant;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.List;

public class ActionPlaceOnMarket extends ActionPlace {

    private SpaceMarket spaceMarket;

    public ActionPlaceOnMarket(Player player, FamilyMember familyMember, SpaceMarket spaceMarket, Servant servant) {
        super(player, familyMember, spaceMarket, servant);
        this.spaceMarket = spaceMarket;
    }

    public ActionPlaceOnMarket(Player player, FamilyMember familyMember, SpaceMarket spaceMarket) {
        this(player, familyMember, spaceMarket, new Servant(0));
    }

    @Override
    protected void setup(Match match) {}

    @Override
    protected void canBeExecuted(Match match) throws RequiredValueNotSatisfiedException {
        if(spaceMarket.isOccupied())
            throw new RuntimeException("This SpaceMarket is already taken!");
        if(!spaceMarket.isRequiredValueSatisfied(familyMember))
            throw new RuntimeException("Your FamilyMember does not satisfy the required value for this placement!");
    }

    @Override
    protected void execute(Match match) {
        match.placeFamilyMember(spaceMarket, familyMember);
    }
}
