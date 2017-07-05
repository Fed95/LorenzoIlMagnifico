package it.polimi.ingsw.gc_12;

import it.polimi.ingsw.gc_12.dice.SpaceDie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InstanceCreator {

    public static List<Player> cratePlayers(int num){
        List<Player> players = new ArrayList<>();
        SpaceDie spaceDie = new SpaceDie();
        int i = 0;
            for (PlayerColor color : PlayerColor.values()) {
                String name = "p" + i;
                Player player = new Player(name, color);
                player.init(spaceDie);
                players.add(player);
                i++;
                if(i == num)
                    break;
            }
        return players;
    }

    public static Match createMatch(List<Player> players){
        Match match = new Match();
        Map<PlayerColor, Player> playerMap = new HashMap<>();
        for(Player player : players)
            playerMap.put(player.getColor(), player);
        match.init(playerMap);
        return match;
    }

    public static Match createMatch(int numOfPlayers){
        List<Player> players = cratePlayers(numOfPlayers);
        return createMatch(players);
    }
}
