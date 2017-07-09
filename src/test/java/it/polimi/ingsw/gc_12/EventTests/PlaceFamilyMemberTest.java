package it.polimi.ingsw.gc_12.EventTests;

import it.polimi.ingsw.gc12.model.board.occupiable.Occupiable;
import it.polimi.ingsw.gc12.model.board.occupiable.TowerFloor;
import it.polimi.ingsw.gc12.model.event.EventPlaceFamilyMember;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.model.player.familymember.FamilyMember;
import it.polimi.ingsw.gc12.view.client.ClientHandler;
import it.polimi.ingsw.gc_12.InstanceCreator;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class PlaceFamilyMemberTest {

    Player player = InstanceCreator.createMockPlayer();
    List<Occupiable> occupiables = new ArrayList<>();
    List<Occupiable> occupiables1 = new ArrayList<>();
    Occupiable occupiable = mock(TowerFloor.class);
    Occupiable occupiable1 = mock(TowerFloor.class);
    FamilyMember familyMember = mock(FamilyMember.class);
    FamilyMember familyMember1 = mock(FamilyMember.class);
    String description = "";
    EventPlaceFamilyMember event;

    @Test
    public void testConstructors(){
        try{
            event = new EventPlaceFamilyMember(player, occupiables, familyMember);
            event = new EventPlaceFamilyMember(player, occupiable, familyMember);
            event = new EventPlaceFamilyMember(player, occupiables, familyMember, description);
            event = new EventPlaceFamilyMember(occupiables, familyMember, description);
            event = new EventPlaceFamilyMember(occupiables, familyMember);
            event = new EventPlaceFamilyMember(occupiables);
            event = new EventPlaceFamilyMember();

            event.setMultiplier(1);
            assertEquals(1, event.getMultiplier());
            event.toString();
            event.toStringClient();
        }catch(Exception e){
            fail(e.getMessage());
        }
    }

    @Test
    public void testMisc(){
        try{
            event = new EventPlaceFamilyMember();

            event.setMultiplier(1);
            assertEquals(1, event.getMultiplier());
            event.toString();
            event.toStringClient();

        }catch(Exception e){
            fail(e.getMessage());
        }
    }

    @Test
    public void testEquals(){
        occupiables.add(occupiable);
        occupiables1.add(occupiable1);
        EventPlaceFamilyMember event = new EventPlaceFamilyMember(player, occupiables, familyMember);
        EventPlaceFamilyMember event1 = new EventPlaceFamilyMember(player, occupiables1, familyMember1);
        EventPlaceFamilyMember event2 = new EventPlaceFamilyMember();

        assertTrue(event.equals(event));
        assertTrue(event2.equals(event));
        assertTrue(event2.equals(event1));

        assertFalse(event.equals(event1));
    }
}
