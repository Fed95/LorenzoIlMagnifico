package it.polimi.ingsw.gc_12.ActionTests;

import it.polimi.ingsw.gc12.model.action.ActionSupportChurch;
import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.model.player.resource.ResourceType;
import it.polimi.ingsw.gc_12.InstanceCreator;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class SupportChurchTest {
    Match match = InstanceCreator.createMatch(2);
    Player player = match.getPlayer("p0");
    ActionSupportChurch action = new ActionSupportChurch(player);

    @Test
    public void test(){
        List<Player> players = new ArrayList<>(match.getPlayers().values());
        match.getActionHandler().setPlayers(players);
        int initialVictoryPoints = player.getResourceValue(ResourceType.VICTORY_POINT);
        player.setResourceValue(ResourceType.FAITH_POINT, 9);

        action.start(match);

        assertEquals(java.util.Optional.of(0), java.util.Optional.ofNullable(player.getResourceValue(ResourceType.FAITH_POINT)));
        assertEquals(java.util.Optional.of(initialVictoryPoints + 13), java.util.Optional.ofNullable(player.getResourceValue(ResourceType.VICTORY_POINT)));
    }
}
