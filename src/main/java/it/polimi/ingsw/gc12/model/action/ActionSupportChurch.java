package it.polimi.ingsw.gc12.model.action;

import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.model.event.Event;
import it.polimi.ingsw.gc12.model.event.EventSupportChurch;
import it.polimi.ingsw.gc12.misc.exception.ActionDeniedException;
import it.polimi.ingsw.gc12.model.player.resource.Resource;
import it.polimi.ingsw.gc12.model.player.resource.ResourceType;

import java.util.Collections;
import java.util.List;
/**
 * Action that can be performed during the VaticanReport.
 * Sets to 0 the players' FaithPoints and adds to his resources the relative VictoryPoints
 * Notifies the match and the ActionHandler that the player has completed his vatican report
 * so that the report can pass on to the next player
 */
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

        Event event = new EventSupportChurch(player);
        try {
            match.getEffectHandler().executeEffects(match, event);
        } catch (ActionDeniedException e) {
            throw new IllegalStateException();
        }
        match.notifyObserver(event);

        int faithPoints = player.getResourceValue(ResourceType.FAITH_POINT) > 15 ? 15 : player.getResourceValue(ResourceType.FAITH_POINT);
        Resource victoryPoints = match.getBoard().getTrackFaithPoints().getFaithSlot(faithPoints).getVictoryPoints();
        player.setResourceValue(ResourceType.FAITH_POINT, 0);
        player.addResources(Collections.singletonList(victoryPoints));

        List<Player> players = match.getActionHandler().getPlayers();
        if(players.size() > 0) {
            match.vaticanReport(players);
        }
        else
            match.newTurn(true);
    }



    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Show your support to the church").append(System.getProperty("line.separator"));
        sb.append("    You will loose all of your FaithPoints (" + player.getResourceValue(ResourceType.FAITH_POINT) + ")").append(System.getProperty("line.separator"));
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        return this == o || o instanceof ActionSupportChurch;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
