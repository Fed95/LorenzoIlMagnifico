package it.polimi.ingsw.gc_12.action;

import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.event.Event;
import it.polimi.ingsw.gc_12.event.EventSupportChurch;
import it.polimi.ingsw.gc_12.event.EventVaticanReport;
import it.polimi.ingsw.gc_12.resource.Resource;
import it.polimi.ingsw.gc_12.resource.ResourceType;
import it.polimi.ingsw.gc_12.resource.VictoryPoint;

import java.util.Collections;
import java.util.List;

public class ActionSupportChurch extends Action {

    public ActionSupportChurch(Player player) {
        super(player);
    }

    @Override
    public boolean isValid(Match match) {
        return false;
    }

    @Override
    public void start(Match match) {

        EventSupportChurch event = new EventSupportChurch(player);
        match.notifyObserver(event);

        player.setResourceValue(ResourceType.FAITH_POINT, 0);

        Resource victoryPoints = match.getBoard().getTrackFaithPoints().getFaithSlot(player.getResourceValue(ResourceType.FAITH_POINT)).getVictoryPoints();
        player.addResources(Collections.singletonList(victoryPoints));

        List<Player> players = match.getActionHandler().getPlayers();
        if(players.size() > 0) {
            match.vaticanReport(players);
        }
        else
            match.newTurn();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Show your support to the church").append(System.getProperty("line.separator"));
        sb.append("    You will loose all of your FaithPoints (" + player.getResourceValue(ResourceType.FAITH_POINT) + ")").append(System.getProperty("line.separator"));
        return sb.toString();
    }
}
