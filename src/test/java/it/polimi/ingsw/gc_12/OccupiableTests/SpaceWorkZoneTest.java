package it.polimi.ingsw.gc_12.OccupiableTests;

import it.polimi.ingsw.gc12.model.board.occupiable.SpaceWorkZone;
import it.polimi.ingsw.gc12.model.board.occupiable.WorkType;
import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc_12.InstanceCreator;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class SpaceWorkZoneTest {

    Match match = InstanceCreator.createMatch(2);
    SpaceWorkZone spaceWorkZone = match.getBoard().getSpaceWorkZones().get(WorkType.HARVEST);

    @Test
    public void testMisc(){
        assertEquals(WorkType.HARVEST, spaceWorkZone.getType());
        String string = "Work Zones of type: " + WorkType.HARVEST;
        assertEquals(string, spaceWorkZone.toString());
        assertEquals(1, spaceWorkZone.getOccupiables().size());

        match = InstanceCreator.createMatch(4);
        spaceWorkZone = match.getBoard().getSpaceWorkZones().get(WorkType.HARVEST);
        assertEquals(2, spaceWorkZone.getOccupiables().size());
    }
}
