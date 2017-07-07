package it.polimi.ingsw.gc_12.ActionTests;
import it.polimi.ingsw.gc12.model.action.ActionChooseFamilyMember;
import it.polimi.ingsw.gc12.model.event.EventFamilyMemberChosen;
import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.model.player.familymember.FamilyMember;
import it.polimi.ingsw.gc12.model.player.familymember.FamilyMemberColor;
import it.polimi.ingsw.gc12.view.server.ServerRMIView;
import it.polimi.ingsw.gc12.view.server.ServerSocketView;
import it.polimi.ingsw.gc_12.*;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class ChooseFamilyMemberTest {

    Match match = InstanceCreator.createMatch(2);
    ServerRMIView rmiView = mock(ServerRMIView.class);
    ServerSocketView socketView = mock(ServerSocketView.class);
    Player player = match.getPlayer("p0");
    FamilyMember familyMember = player.getFamilyMember(FamilyMemberColor.BLACK);
    FamilyMember familyMember1 = player.getFamilyMember(FamilyMemberColor.WHITE);

    @Test
    public void testStart(){
        match.registerObserver(rmiView);
        match.registerObserver(socketView);
        ActionChooseFamilyMember action = new ActionChooseFamilyMember(player, familyMember);
        action.start(match);

        assertTrue(match.getActionHandler().getEvents().getFirst() instanceof EventFamilyMemberChosen);
        verify(rmiView, times(1)).update(match.getActionHandler().getEvents().getFirst());
        verify(socketView, times(1)).update(match.getActionHandler().getEvents().getFirst());

        familyMember.setBusy(true);
        ActionChooseFamilyMember action1 = new ActionChooseFamilyMember(player, familyMember);
        action.start(match);
        verify(rmiView, times(2)).update(match.getActionHandler().getEvents().getFirst());
        verify(socketView, times(2)).update(match.getActionHandler().getEvents().getFirst());

        assertTrue(action.equals(action));

        action1 = new ActionChooseFamilyMember(player, familyMember1);

        assertFalse(action.equals(action1));
    }

}
