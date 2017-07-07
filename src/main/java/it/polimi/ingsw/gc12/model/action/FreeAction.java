package it.polimi.ingsw.gc12.model.action;

import it.polimi.ingsw.gc12.model.player.familymember.FamilyMember;
import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.misc.exception.RequiredValueNotSatisfiedException;
import it.polimi.ingsw.gc12.model.board.occupiable.Occupiable;

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
