package it.polimi.ingsw.gc_12.action;

import it.polimi.ingsw.gc_12.FamilyMember;
import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.exceptions.RequiredValueNotSatisfiedException;
import it.polimi.ingsw.gc_12.occupiables.*;

import java.io.IOException;

public class FreeAction extends ActionPlace {

    public FreeAction(Player player, FamilyMember familyMember, Occupiable occupiable){
        super(player, familyMember, occupiable);
    }

    @Override
    public void start(Match match) {
        setup(match);
        execute(match);
    }


    @Override
    protected void setup(Match match) {
        familyMember = getRealFamilyMember(match);
    }

    @Override
    protected void canBeExecuted(Match match) throws RequiredValueNotSatisfiedException { }

    @Override
    protected void execute(Match match) {
        ActionFactory.createActionPlace(player, familyMember,occupiable).start(match);
    }
}
