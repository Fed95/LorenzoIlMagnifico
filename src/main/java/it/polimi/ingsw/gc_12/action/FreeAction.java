package it.polimi.ingsw.gc_12.action;

import it.polimi.ingsw.gc_12.FamilyMember;
import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.exceptions.RequiredValueNotSatisfiedException;
import it.polimi.ingsw.gc_12.occupiables.*;

import java.io.IOException;

public class FreeAction extends ActionPlace {

    private Occupiable occupiable;

    public FreeAction(FamilyMember familyMember, Occupiable occupiable){
        super(familyMember);
    }

    @Override
    public void start(Match match) throws IOException {
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
    protected void execute(Match match) throws IOException {
        if(occupiable instanceof TowerFloor)
            new ActionPlaceOnTower(familyMember, (TowerFloor) occupiable).start(match);
        else if(occupiable instanceof SpaceMarket)
            new ActionPlaceOnMarket(familyMember, (SpaceMarket) occupiable).start(match);
        else if(occupiable instanceof SpaceWork)
            new ActionPlaceOnSpaceWork(familyMember, (SpaceWork) occupiable).start(match);
        else if(occupiable instanceof CouncilPalace)
            new ActionPlaceOnCouncil(familyMember, (CouncilPalace) occupiable).start(match);
    }
}
