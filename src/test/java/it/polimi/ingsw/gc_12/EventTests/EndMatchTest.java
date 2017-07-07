package it.polimi.ingsw.gc_12.EventTests;

import it.polimi.ingsw.gc12.model.event.EventEndMatch;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc_12.InstanceCreator;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class EndMatchTest {

    Player player = InstanceCreator.createMockPlayer();
    Player player1 = InstanceCreator.createMockPlayer();
    Player player2 = InstanceCreator.createMockPlayer();
    Player player3 = InstanceCreator.createMockPlayer();
    List<Player> players = new ArrayList<>();
    EventEndMatch event;

    @Test
    public void testConstructor(){
        try {
            players.add(player);
            players.add(player1);
            players.add(player2);
            players.add(player3);

            event = new EventEndMatch(player);
            assertEquals(player, event.getPlayer());

            event = new EventEndMatch(players);

        }catch(Exception e){
            fail(e.getMessage());
        }
    }

    @Test
    public void testToStringClient(){
        try{
            event = new EventEndMatch(player);
            event.toString();
            event.toStringClient();
            event.toStringClientGUI();

            event = new EventEndMatch(players);
            event.toString();
            event.toStringClient();
            event.toStringClientGUI();

        }catch(Exception e){
            e.getMessage();
        }
    }
}
