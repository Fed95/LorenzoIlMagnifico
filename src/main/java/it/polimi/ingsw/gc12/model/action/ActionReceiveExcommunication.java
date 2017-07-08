package it.polimi.ingsw.gc12.model.action;

import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.model.board.excommunication.ExcommunicationTile;

import java.util.List;

public class ActionReceiveExcommunication extends Action {

    private ExcommunicationTile tile;

    public ActionReceiveExcommunication(Player player, ExcommunicationTile tile) {
        super(player);
        this.tile = tile;
    }

    @Override
    public boolean isValid(Match match) {
        return false;
    }

    @Override
    public void start(Match match) {
        player.getExcommunications().add(tile);
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
        sb.append("Deny your support to the church").append(System.getProperty("line.separator"));
        sb.append("    you will receive the following Excommunication: ").append(System.getProperty("line.separator"));
        sb.append(tile).append(System.getProperty("line.separator")).append(System.getProperty("line.separator"));
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        return this == o || o instanceof ActionReceiveExcommunication;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
