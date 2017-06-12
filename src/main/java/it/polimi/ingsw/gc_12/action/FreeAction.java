package it.polimi.ingsw.gc_12.action;

import it.polimi.ingsw.gc_12.FamilyMember;
import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.effect.Effect;
import it.polimi.ingsw.gc_12.event.Event;
import it.polimi.ingsw.gc_12.event.EventPlaceFamilyMember;
import it.polimi.ingsw.gc_12.event.EventRequiredValueNotSatisfied;
import it.polimi.ingsw.gc_12.exceptions.RequiredValueNotSatisfiedException;
import it.polimi.ingsw.gc_12.occupiables.*;

import java.io.IOException;
import java.util.List;

public class FreeAction extends ActionPlace {

    private Occupiable occupiable;

    public FreeAction(FamilyMember familyMember, Occupiable occupiable){
        super(familyMember);
    }

    @Override

    public void start(Match match) throws RequiredValueNotSatisfiedException, IOException {
        Player player = match.getBoard().getTrackTurnOrder().getCurrentPlayer();
        familyMember = getRealFamilyMember(match);

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
