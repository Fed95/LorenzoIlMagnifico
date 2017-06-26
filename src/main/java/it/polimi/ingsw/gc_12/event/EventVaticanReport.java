package it.polimi.ingsw.gc_12.event;

import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.MatchInstance;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.action.ActionHandler;
import it.polimi.ingsw.gc_12.action.ActionReceiveExcommunication;
import it.polimi.ingsw.gc_12.action.ActionSupportChurch;
import it.polimi.ingsw.gc_12.client.ClientHandler;
import it.polimi.ingsw.gc_12.excommunication.ExcommunicationTile;

import java.util.ArrayList;
import java.util.List;

public class EventVaticanReport extends Event {

    private ExcommunicationTile tile;
    private List<Player> players;

    public EventVaticanReport(Player player, ExcommunicationTile tile, List<Player> players) {
        super(player);
        this.tile = tile;
        this.players = players;
    }

    @Override
    public void setActions(ActionHandler actionHandler, Match match) {
        actions = new ArrayList<>();
        actions.add(new ActionSupportChurch(player));
        actions.add(new ActionReceiveExcommunication(player, tile));
        players = new ArrayList<>(players.subList(1, players.size()));
    }

    public ExcommunicationTile getTile() {
        return tile;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    @Override
    public void executeClientSide(ClientHandler client) {

        boolean myTurn = client.getColor().equals(player.getColor());
        client.setMyTurn(myTurn);
        if(myTurn)
            super.executeClientSide(client);
    }

    @Override
    public List<Object> getAttributes() {
        return null;
    }

    @Override
    public String toString() {
        return null;
    }

    @Override
    public String toStringClient() {
        StringBuilder sb = new StringBuilder();
        sb.append("----------------------------------------").append(System.getProperty("line.separator"));
        sb.append("----------\\\\ VATICAN REPORT //----------").append(System.getProperty("line.separator"));
        sb.append("----------------------------------------").append(System.getProperty("line.separator"));
        return sb.toString();
    }
}
