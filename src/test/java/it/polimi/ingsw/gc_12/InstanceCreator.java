package it.polimi.ingsw.gc_12;


import it.polimi.ingsw.gc12.model.board.dice.SpaceDie;
import it.polimi.ingsw.gc12.model.card.Card;
import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.model.player.PlayerColor;
import it.polimi.ingsw.gc12.model.player.resource.Money;
import it.polimi.ingsw.gc12.model.player.resource.Resource;
import it.polimi.ingsw.gc12.model.player.resource.Stone;
import it.polimi.ingsw.gc12.model.player.resource.Wood;


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

    public static List<Resource> getResourceList(){
        List<Resource> requirements = new ArrayList<>();
        requirements.add(new Money(1));
        requirements.add(new Wood(1));
        requirements.add(new Stone(1));
        return requirements;
    }

    public static Card getCard(String name){
        Match match = createMatch(0);
        List<Card> cards = match.getCards();
        for(Card card : cards)
            if(card.getName().equals(name))
                return card;
        throw new IllegalStateException();
    }
}
