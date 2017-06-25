package it.polimi.ingsw.gc_12.action;

import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.event.Event;
import it.polimi.ingsw.gc_12.event.EventVaticanReport;
import it.polimi.ingsw.gc_12.resource.ResourceType;

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
        player.setResourceValue(ResourceType.FAITH_POINT, 0);
        //TODO: IMPLEMENT VICTORY POINTS
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
