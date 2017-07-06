package it.polimi.ingsw.gc_12.ActionTests;
import it.polimi.ingsw.gc12.model.action.ActionChooseExchange;
import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.model.player.resource.Money;
import it.polimi.ingsw.gc12.model.player.resource.Resource;
import it.polimi.ingsw.gc12.model.player.resource.ResourceExchange;
import it.polimi.ingsw.gc12.model.player.resource.ResourceType;
import it.polimi.ingsw.gc_12.InstanceCreator;
import org.junit.Test;

import java.util.Collection;
import java.util.Collections;

import static org.junit.Assert.*;

public class ChooseExchangeTest {

    Match match = InstanceCreator.createMatch(2);
    Player player = match.getPlayer("p0");

    @Test
    public void testStart(){
        int initailValue = player.getResourceValue(ResourceType.MONEY);
        Collection<Resource> initialResources = player.getResources().values();
        ResourceExchange exchange = new ResourceExchange(null, Collections.singletonList(new Money(1)));
        ResourceExchange exchange1 = new ResourceExchange(null, Collections.singletonList(new Money(2)));

        ActionChooseExchange action = new ActionChooseExchange(player, exchange);
        ActionChooseExchange action1 = new ActionChooseExchange(player, exchange1);

        action.start(match);
        assertEquals(java.util.Optional.of(initailValue + 1), java.util.Optional.ofNullable(player.getResourceValue(ResourceType.MONEY)));
        player.addResources(Collections.singletonList(new Money(-1)));
        assertEquals(initialResources, player.getResources().values());
        assertTrue(action.equals(action));

        assertFalse(action.equals(action1));
    }
}
