package it.polimi.ingsw.gc_12.EventTests;

import it.polimi.ingsw.gc12.model.board.occupiable.Occupiable;
import it.polimi.ingsw.gc12.model.event.EventServantsRequested;
import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.model.player.familymember.FamilyMember;
import it.polimi.ingsw.gc12.model.player.familymember.FamilyMemberColor;
import it.polimi.ingsw.gc12.model.player.resource.Resource;
import it.polimi.ingsw.gc12.model.player.resource.ResourceType;
import it.polimi.ingsw.gc_12.InstanceCreator;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ServantsRequestedTest {

    Match match = InstanceCreator.createMatch(2);
    Player player = match.getPlayer("p0");
    FamilyMember familyMember = player.getFamilyMember(FamilyMemberColor.BLACK);
    Occupiable occupiable = match.getBoard().getCouncilPalace();
    List<Resource> discounts = InstanceCreator.getResourceList();
    EventServantsRequested event;

    @Test
    public void testConstructor(){
        try {
            event = new EventServantsRequested(player, occupiable, familyMember);
            event = new EventServantsRequested(player, occupiable, familyMember, discounts);
        }catch(Exception e){
            fail(e.getMessage());
        }
    }

    @Test
    public void testSetActions(){
        event = new EventServantsRequested(player, occupiable, familyMember, discounts);
        familyMember.setValue(0);
        event.setMultiplier(2);
        event.setActions(match);

        int numOfActions = player.getResourceValue(ResourceType.SERVANT); //-1 for familyMemberValue, +1 for discardAction
        assertEquals(numOfActions + 1, event.getActions().size());
    }
}
