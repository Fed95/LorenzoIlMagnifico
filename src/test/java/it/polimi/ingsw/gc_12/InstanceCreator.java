package it.polimi.ingsw.gc_12;


import it.polimi.ingsw.gc12.model.board.dice.SpaceDie;
import it.polimi.ingsw.gc12.model.card.*;
import it.polimi.ingsw.gc12.model.effect.Effect;
import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.model.player.PlayerColor;
import it.polimi.ingsw.gc12.model.player.personalboard.LeaderCardsSpace;
import it.polimi.ingsw.gc12.model.player.personalboard.PersonalBoard;
import it.polimi.ingsw.gc12.model.player.personalboard.ResourcesContainer;
import it.polimi.ingsw.gc12.model.player.resource.*;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
        for(ResourceType type : ResourceType.values()){
            requirements.add(ResourceBuilder.create(type, 1));
        }
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

    public static Player createMockPlayer(){
        Player player = mock(Player.class);
        PersonalBoard personalBoard = mock(PersonalBoard.class);
        LeaderCardsSpace leaderCardsSpace = mock(LeaderCardsSpace.class);
        ResourcesContainer resourcesContainer = mock(ResourcesContainer.class);
        when(player.getPersonalBoard()).thenReturn(personalBoard);
        when(personalBoard.getLeaderCardsSpace()).thenReturn(leaderCardsSpace);
        when(personalBoard.getResourcesContainer()).thenReturn(resourcesContainer);
        return player;
    }

    public static LeaderCard createEmptyLeaderCard(boolean permanent){
        LeaderCard card = new LeaderCard(0, "testCard", new ArrayList<Resource>(), new HashMap<CardType, Integer>(), new ArrayList<Effect>(), permanent);
        return card;
    }

    public static CardDevelopment createEmptyDevelopmentCard(){
        CardDevelopment card = new CardBuilding(0, "test", 0, new ArrayList<Resource>(), new ArrayList<Effect>());
        return card;
    }
}
