package it.polimi.ingsw.gc_12.EffectsTests;

import it.polimi.ingsw.gc12.model.board.occupiable.Occupiable;
import it.polimi.ingsw.gc12.model.effect.EffectFreeAction;
import it.polimi.ingsw.gc12.model.event.Event;
import it.polimi.ingsw.gc12.model.event.EventFreeAction;
import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.model.player.familymember.FamilyMember;
import it.polimi.ingsw.gc12.model.player.familymember.FamilyMemberColor;
import it.polimi.ingsw.gc12.model.player.resource.Resource;
import it.polimi.ingsw.gc_12.InstanceCreator;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;
import static org.mockito.Mockito.mock;

public class FreeActionTest {

    Match match = InstanceCreator.createMatch(2);
    Player player = match.getPlayer("p0");
    FamilyMember familyMember = player.getFamilyMember(FamilyMemberColor.BLACK);
    String description = "test";
    int value = 7;
    Event event = mock(Event.class);
    List<Occupiable> occupiables = match.getBoard().getOccupiables();
    List<Resource> discounts = InstanceCreator.getResourceList();
    EffectFreeAction effect;

    @Test
    public void testConstructors(){
        try {
            effect = new EffectFreeAction(event, occupiables, value, description);
            assertEquals(event, effect.getEvent());
            assertEquals(occupiables, effect.getOccupiables());
            assertEquals(value, effect.getValue());
            assertEquals(description, effect.toString());

            effect = new EffectFreeAction(event, occupiables, value, discounts);
            assertEquals(event, effect.getEvent());
            assertEquals(occupiables, effect.getOccupiables());
            assertEquals(value, effect.getValue());
            assertEquals(discounts, discounts);

        }catch(Exception e){
            fail(e.getMessage());
        }
    }

    @Test
    public void testExecute(){
        match.start();
        effect = new EffectFreeAction(event, occupiables, value, discounts);
        effect.execute(match, event, false);
        effect.discard(match, event); // Should do nothing when discarded

        assertTrue(match.getActionHandler().getEvents().getLast() instanceof EventFreeAction);
        EventFreeAction eventFreeAction = (EventFreeAction) match.getActionHandler().getEvents().getLast();
        assertEquals(discounts, eventFreeAction.getDiscounts());
        assertEquals(22, eventFreeAction.getActions().size()); //21 Occupiables + discardAction
    }
}
