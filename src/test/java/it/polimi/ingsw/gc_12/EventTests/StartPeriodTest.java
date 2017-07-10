package it.polimi.ingsw.gc_12.EventTests;

import it.polimi.ingsw.gc12.model.event.EventStartMatch;
import it.polimi.ingsw.gc12.model.event.EventStartPeriod;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class StartPeriodTest {

    @Test
    public void testToString(){
        EventStartPeriod event = new EventStartPeriod();
        assertEquals("", event.toString());
    }
}
