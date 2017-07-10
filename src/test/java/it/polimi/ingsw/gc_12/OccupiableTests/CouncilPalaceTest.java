package it.polimi.ingsw.gc_12.OccupiableTests;

import it.polimi.ingsw.gc12.model.board.occupiable.CouncilPalace;
import it.polimi.ingsw.gc12.model.player.familymember.FamilyMember;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class CouncilPalaceTest {

    CouncilPalace councilPalace = new CouncilPalace(1);

    @Test
    public void testMisc(){
        FamilyMember familyMember = mock(FamilyMember.class);
        assertTrue(councilPalace.canBeOccupiedBy(familyMember));
        assertEquals(Collections.singletonList(councilPalace), councilPalace.getOccupiables());
        String string = "Council Palace            			Effects: [EventPlaceFamilyMember: EffectChangeResource: [Cost: [], Bonus: [COUNCIL_PRIVILEGE: 1]]]";
        assertEquals(string, councilPalace.toString());
    }

}
