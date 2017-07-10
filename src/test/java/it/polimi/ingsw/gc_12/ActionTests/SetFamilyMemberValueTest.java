package it.polimi.ingsw.gc_12.ActionTests;

import it.polimi.ingsw.gc12.controller.ActionHandler;
import it.polimi.ingsw.gc12.model.action.ActionSetFamilyMemberValue;
import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.model.player.familymember.FamilyMember;
import it.polimi.ingsw.gc12.model.player.familymember.FamilyMemberColor;
import it.polimi.ingsw.gc_12.InstanceCreator;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SetFamilyMemberValueTest {

    Match match = mock(Match.class);
    ActionHandler actionHandler = mock(ActionHandler.class);
    Player player = InstanceCreator.createMockPlayer();
    FamilyMember familyMember =new FamilyMember(FamilyMemberColor.BLACK);
    int value = 8;
    ActionSetFamilyMemberValue action;

    @Test
    public void testStart(){
        when(match.getActionHandler()).thenReturn(actionHandler);
        action = new ActionSetFamilyMemberValue(player, familyMember, value);
        assertFalse(action.isValid(match));
        action.start(match);
        assertEquals(value, familyMember.getValue());
    }
}
