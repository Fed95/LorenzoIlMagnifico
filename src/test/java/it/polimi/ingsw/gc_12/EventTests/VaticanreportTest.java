package it.polimi.ingsw.gc_12.EventTests;

import it.polimi.ingsw.gc12.model.board.excommunication.ExcommunicationTile;
import it.polimi.ingsw.gc12.model.event.EventVaticanReport;
import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc_12.InstanceCreator;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;

public class VaticanreportTest {

    Match match = InstanceCreator.createMatch(2);
    List<Player> players = new ArrayList<>(match.getPlayers().values());
    Player player = match.getPlayer("p0");
    ExcommunicationTile tile = match.getBoard().getExcommunicationSpace().getTiles().get(0);
    EventVaticanReport event = new EventVaticanReport(player, tile, players);

    @Test
    public void testConstructor(){
        try {
            assertEquals(player, event.getPlayer());
            assertEquals(players, event.getPlayers());
            assertEquals(tile, event.getTile());
        }catch(Exception e){
            fail(e.getMessage());
        }
    }

    @Test
    public void testSetActions(){
        event.setActions(match);
        assertEquals(2, event.getActions().size());
        List<Player> sublist = new ArrayList<>(players.subList(1, players.size()));
        assertEquals(sublist, event.getPlayers());
        assertEquals(sublist, match.getActionHandler().getPlayers());
    }

    @Test
    public void testSetPlayers(){
        event.setPlayers(players);
        assertEquals(players, event.getPlayers());
    }

    @Test
    public void testToString(){
        try{
            event.toString();
            event.toStringClient();
            event.toStringClientGUI();
        }catch(Exception e){
            fail(e.getMessage());
        }
    }
}
