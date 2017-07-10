package it.polimi.ingsw.gc_12.OccupiableTests;

import it.polimi.ingsw.gc12.model.board.occupiable.SpaceWorkMultiple;
import it.polimi.ingsw.gc12.model.board.occupiable.SpaceWorkSingle;
import it.polimi.ingsw.gc12.model.board.occupiable.WorkType;
import it.polimi.ingsw.gc12.model.effect.Effect;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class SpaceWorksTest {

    List<Effect> effects = new ArrayList<>();
    int value = 1;
    SpaceWorkSingle spaceWorkSingle = new SpaceWorkSingle(WorkType.HARVEST, value, effects);
    SpaceWorkMultiple spaceWorkMultiple = new SpaceWorkMultiple(WorkType.HARVEST, value, effects);

    @Test
    public void testToString(){
        String s = "HARVEST SpaceWorkMultiple\t\t\tEffects: []";
        assertEquals(s, spaceWorkMultiple.toString());
        String s1 = "HARVEST SpaceWorkSingle\t\t\tEffects: []";
        assertEquals(s1, spaceWorkSingle.toString());
    }
}
