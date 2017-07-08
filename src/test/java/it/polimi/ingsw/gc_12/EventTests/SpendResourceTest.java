package it.polimi.ingsw.gc_12.EventTests;

import it.polimi.ingsw.gc12.model.event.EventSpendResource;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.model.player.resource.*;
import it.polimi.ingsw.gc_12.InstanceCreator;
import org.junit.Test;

import static junit.framework.TestCase.*;


public class SpendResourceTest {

    Player player = InstanceCreator.createMockPlayer();
    EventSpendResource event;
    EventSpendResource event1;

    @Test
    public void testConstructor(){
        try {
            for (int i = -1; i < 2; i++) {
                for (ResourceType type : ResourceType.values()) {
                    Resource resource = ResourceBuilder.create(type, i);

                    event = new EventSpendResource(player, resource);
                    assertEquals(player, event.getPlayer());
                    assertEquals(resource.getType(), event.getResourceType());
                    assertEquals(i, event.getValue());

                    event = new EventSpendResource(resource);
                    assertEquals(resource.getType(), event.getResourceType());
                    assertEquals(i, event.getValue());
                }
            }
        }catch(Exception e){
            fail(e.getMessage());
        }
    }

    @Test
    public void testEquals(){
        for (int i = -1; i < 2; i++) {
            for (ResourceType type : ResourceType.values()) {
                Resource resource = ResourceBuilder.create(type, i);
                event = new EventSpendResource(resource);

                for (int j = -1; j < 2; j++) {
                    for (ResourceType t : ResourceType.values()) {
                        Resource r = ResourceBuilder.create(t, j);
                        event1 = new EventSpendResource(r);

                        if(!event.equals(event1))
                            assertFalse(event.getResourceType().equals(event1.getResourceType()));
                    }
                }
            }
        }
    }

    @Test
    public void testToString(){
        try{
            for (ResourceType type : ResourceType.values()) {
                Resource resource = ResourceBuilder.create(type, 0);
                event = new EventSpendResource(player, resource);
                event1 = new EventSpendResource(resource);
                event.toString();
                event1.toString();
            }

        }catch(Exception e){
            e.getMessage();
        }
    }
}
