package it.polimi.ingsw.gc_12.OccupiableTests;

import it.polimi.ingsw.gc12.model.board.occupiable.SpaceMarket;
import it.polimi.ingsw.gc12.model.effect.Effect;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class SpaceMarketTest {

    List<Effect> effects = new ArrayList<>();
    SpaceMarket spaceMarket = new SpaceMarket(1, 2, effects);

    @Test
    public void testMisc(){
        assertEquals(1, spaceMarket.getMarketNum());
        String s = "Marketplace n.1        \t\t\tEffects: []";
        assertEquals(s, spaceMarket.toString());
    }
}
