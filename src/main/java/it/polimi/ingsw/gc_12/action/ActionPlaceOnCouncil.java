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

    public ActionPlaceOnCouncil(Player player, FamilyMember familyMember, CouncilPalace councilPalace, Servant servant) {
        super(player, familyMember, councilPalace, servant);
        this.councilPalace = councilPalace;
    }

    public ActionPlaceOnCouncil(Player player, FamilyMember familyMember, CouncilPalace councilPalace) {
        this(player, familyMember, councilPalace, new Servant(0));
    }

    @Override
    public String toString() {
        return "ActionPlaceOnCouncil{" +
                "player=" + player +
                ", councilPalace=" + councilPalace +
                ", familyMember=" + familyMember +
                ", servant=" + servant +
                ", occupiable=" + occupiable +
                '}';
    }

    @Override
    protected void setup(Match match) {}

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