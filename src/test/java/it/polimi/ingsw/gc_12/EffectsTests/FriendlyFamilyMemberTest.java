package it.polimi.ingsw.gc_12.EffectsTests;

import it.polimi.ingsw.gc12.misc.exception.ActionDeniedException;
import it.polimi.ingsw.gc12.model.effect.EffectFriendlyFamilyMembers;
import it.polimi.ingsw.gc12.model.event.Event;
import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.model.player.familymember.FamilyMember;
import it.polimi.ingsw.gc_12.InstanceCreator;
import org.junit.Test;

import static junit.framework.TestCase.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FriendlyFamilyMemberTest {

    Match match = InstanceCreator.createMatch(2);
    Player player = match.getPlayer("p0");
    Event event = mock(Event.class);
    EffectFriendlyFamilyMembers effect;

    @Test
    public void testConstructor(){
        try{
            effect = new EffectFriendlyFamilyMembers(event);
            assertEquals(event, effect.getEvent());
        }catch(Exception e){
            fail(e.getMessage());
        }
    }

    @Test
    public void testExecute(){
        when(event.getPlayer()).thenReturn(player);
        effect = new EffectFriendlyFamilyMembers(event);
        try {
            effect.execute(match, event, false);
        } catch (ActionDeniedException e) {
            fail(e.getMessage());
        }
        for(FamilyMember familyMember : player.getFamilyMembers().values())
            assertTrue(familyMember.isFriendly());

        effect.discard(match, event);
        for(FamilyMember familyMember : player.getFamilyMembers().values())
            assertFalse(familyMember.isFriendly());
    }
}
