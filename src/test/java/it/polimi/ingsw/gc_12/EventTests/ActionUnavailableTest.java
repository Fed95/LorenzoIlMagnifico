package it.polimi.ingsw.gc_12.EventTests;

import it.polimi.ingsw.gc12.model.event.EventActionUnavailable;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class ActionUnavailableTest {

    boolean isPlaced = false;
    EventActionUnavailable event;

    @Test
    public void testToString(){
        event = new EventActionUnavailable(isPlaced);
        StringBuilder sb = new StringBuilder();
        sb.append(System.getProperty("line.separator"));
        sb.append("EventActionUnavailable");

        assertEquals(sb.toString(), event.toString());
    }
}
