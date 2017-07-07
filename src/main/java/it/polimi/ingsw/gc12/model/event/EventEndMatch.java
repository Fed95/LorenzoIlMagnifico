package it.polimi.ingsw.gc12.model.event;

import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.model.player.resource.ResourceType;

import java.util.List;

public class EventEndMatch extends Event {

    private List<Player> players;

    public EventEndMatch(Player player) {
        super(player);
        effectProviders.add(player.getPersonalBoard().getResourcesContainer());
    }

    public EventEndMatch(List<Player> players) {
        this.players = players;
    }

    public EventEndMatch(){
    }

    @Override
    public String toString() {
        return "";
    }

    @Override
    public String toStringClient() {
        StringBuilder sb = new StringBuilder();
        sb.append("-----------///////////-\\\\\\\\\\\\\\\\\\\\\\-----------").append(System.getProperty("line.separator"));
        sb.append("----------\\\\ THE MATCH HAS ENDED //----------").append(System.getProperty("line.separator"));
        sb.append("------------\\\\\\\\\\\\\\\\\\\\-//////////------------").append(System.getProperty("line.separator"));


        sb.append("Results:").append(System.getProperty("line.separator")).append(System.getProperty("line.separator"));
        int i = 1;
        for(Player player : players){
            sb.append(i + " - " + player.getName() + "(" + player.getResourceValue(ResourceType.VICTORY_POINT) + " points)").append(System.getProperty("line.separator"));
            i++;
        }

        return sb.toString();
    }
    @Override
    public  String toStringClientGUI(){
        StringBuilder sb = new StringBuilder();
        sb.append("THE MATCH HAS ENDED").append(System.getProperty("line.separator"));
        sb.append("Results:").append(System.getProperty("line.separator")).append(System.getProperty("line.separator"));
        int i = 1;
        for(Player player : players){
            sb.append(i + " - " + player.getName() + "(" + player.getResourceValue(ResourceType.VICTORY_POINT) + " points)").append(System.getProperty("line.separator"));
            i++;
        }

        return sb.toString();
    }
}
