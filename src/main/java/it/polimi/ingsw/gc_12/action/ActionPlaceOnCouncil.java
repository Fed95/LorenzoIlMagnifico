package it.polimi.ingsw.gc_12.action;

import it.polimi.ingsw.gc_12.FamilyMember;
import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.MatchInstance;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.effect.Effect;
import it.polimi.ingsw.gc_12.event.Event;
import it.polimi.ingsw.gc_12.event.EventPlaceFamilyMember;
import it.polimi.ingsw.gc_12.exceptions.RequiredValueNotSatisfiedException;
import it.polimi.ingsw.gc_12.occupiables.CouncilPalace;
import it.polimi.ingsw.gc_12.resource.Servant;

import java.rmi.RemoteException;
import java.util.List;
import java.io.IOException;
public class ActionPlaceOnCouncil extends ActionPlace {

    private CouncilPalace councilPalace;

    public ActionPlaceOnCouncil(FamilyMember familyMember, Servant servant, CouncilPalace councilPalace) {
        super(familyMember, servant);
        this.councilPalace = councilPalace;
    }

    public ActionPlaceOnCouncil(FamilyMember familyMember, CouncilPalace councilPalace) {
        this(familyMember, new Servant(0), councilPalace);
    }

    @Override
    protected void setup(Match match) {
        System.out.println("ActionPlaceOnCouncil: starting...");
        Player player = match.getBoard().getTrackTurnOrder().getCurrentPlayer();
        familyMember = getRealFamilyMember(match);
        councilPalace = getRealCouncilPalace(match);
    }

    @Override
    protected void canBeExecuted(Match match) throws RequiredValueNotSatisfiedException {
        if (!councilPalace.isRequiredValueSatisfied(familyMember))
            throw new RequiredValueNotSatisfiedException();
    }

    @Override
    protected void execute(Match match) throws IOException {
        match.placeFamilyMember(councilPalace, familyMember);
    }

    private CouncilPalace getRealCouncilPalace(Match match) {
        return match.getBoard().getCouncilPalace();
    }
}