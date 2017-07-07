package it.polimi.ingsw.gc_12;
import it.polimi.ingsw.gc12.model.card.Card;
import it.polimi.ingsw.gc12.model.board.dice.SpaceDie;
import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.model.player.PlayerColor;
import org.junit.*;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;


public class PlayerTest {

    Match match = new Match();

    private Player p1, p2;
    private SpaceDie spaceDie;

    Map<PlayerColor, Player> players = new HashMap<>();


    @Before
    public void createPlayers(){
        p1 = new Player("p1", PlayerColor.BLUE);
        p2 = new Player("p2",PlayerColor.RED);

        players.put(p1.getColor(), p1);
        players.put(p2.getColor(), p2);

        spaceDie  = new SpaceDie();

        p1.init(spaceDie);
        p2.init(spaceDie);

        match.init(players);
    }

    @Test
    public void checkColor(){

        assertEquals(PlayerColor.BLUE, p1.getColor());
        assertEquals(PlayerColor.RED, p2.getColor());

        assertEquals(p1.getPersonalBoard().getResourcesContainer().getResources(), p1.getResources());

        assertTrue(p1.getFamilyMembers().size() == 4);

        assertTrue(p1.getColor() != p2.getColor());

        assertTrue(differentCards(p1, p2));

    }

    private boolean differentCards(Player p1, Player p2){
        for(Card card : p1.getCards())
            if(p2.getCards().contains(card))
                return false;
        return true;
    }
}
