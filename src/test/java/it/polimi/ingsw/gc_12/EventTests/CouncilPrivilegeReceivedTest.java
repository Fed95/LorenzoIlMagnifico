package it.polimi.ingsw.gc_12.EventTests;

import it.polimi.ingsw.gc12.model.board.occupiable.CouncilPalace;
import it.polimi.ingsw.gc12.model.event.EventCouncilPrivilegeReceived;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.model.player.resource.CouncilPrivilege;
import it.polimi.ingsw.gc_12.InstanceCreator;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class CouncilPrivilegeReceivedTest {

    Player player = InstanceCreator.createMockPlayer();
    CouncilPrivilege councilPrivilege = new CouncilPrivilege(1);
    List<Integer> choices = new ArrayList<>();
    EventCouncilPrivilegeReceived event;

    @Test
    public void testMisc(){
        event = new EventCouncilPrivilegeReceived(player, councilPrivilege, choices);
        assertEquals(councilPrivilege, event.getCouncilPrivilege());

        assertEquals("", event.toString());
    }
}
