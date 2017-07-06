package it.polimi.ingsw.gc_12.ActionTests;

import it.polimi.ingsw.gc12.model.action.ActionPlayLeaderCard;
import it.polimi.ingsw.gc12.model.card.LeaderCard;
import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc_12.*;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class PlayLeaderCardTest {

    Match match = InstanceCreator.createMatch(2);
    Player player = match.getPlayer("p0");
    //Michelangelo is not permanent
    LeaderCard card1 = (LeaderCard) InstanceCreator.getCard("Michelangelo Buonarroti");
    //Filippo is permanent
    LeaderCard card2 = (LeaderCard) InstanceCreator.getCard("Lorenzo de' Medici");

    @Test
    public void testStart(){
        List<LeaderCard> cards = new ArrayList<>();
        cards.add(card1);
        cards.add(card2);

        ActionPlayLeaderCard action = new ActionPlayLeaderCard(player, card1);
        player.getPersonalBoard().getLeaderCardsSpace().getCards().clear();
        player.getPersonalBoard().getLeaderCardsSpace().getCards().addAll(cards);
        action.start(match);
        assertTrue(card1.isPlayed());
        assertTrue(action.getExecutedEffects().size() == 0);

        action = new ActionPlayLeaderCard(player, card2);
        action.start(match);
        assertTrue(card2.isPlayed());
        assertTrue(action.getExecutedEffects().equals(card2.getEffects()));
    }
}
