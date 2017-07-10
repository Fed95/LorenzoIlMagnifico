package it.polimi.ingsw.gc_12.OccupiableTests;

import it.polimi.ingsw.gc12.model.board.occupiable.Market;
import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.player.familymember.FamilyMember;
import it.polimi.ingsw.gc_12.InstanceCreator;
import org.junit.Test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class MarketTest {

    Match match;
    Market market = new Market();

    @Test
    public void testMisc(){
        FamilyMember familyMember = mock(FamilyMember.class);
        assertTrue(market.canBeOccupiedBy(familyMember));

        match = InstanceCreator.createMatch(2);
        market = match.getBoard().getMarket();
        assertEquals(2, market.getOccupiables().size());

        match = InstanceCreator.createMatch(3);
        market = match.getBoard().getMarket();
        assertEquals(2, market.getOccupiables().size());

        match = InstanceCreator.createMatch(4);
        market = match.getBoard().getMarket();
        assertEquals(4, market.getOccupiables().size());
    }
}
