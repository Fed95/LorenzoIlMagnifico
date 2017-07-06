package it.polimi.ingsw.gc_12.ActionTests;
import it.polimi.ingsw.gc12.model.action.ActionCopyLeaderCard;
import it.polimi.ingsw.gc12.model.card.LeaderCard;
import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc_12.InstanceCreator;
import org.junit.Test;

import static org.junit.Assert.*;

public class CopyLeaderCardTest {
    Match match = InstanceCreator.createMatch(2);
    Player player = match.getPlayer("p0");
    LeaderCard myCard = (LeaderCard) InstanceCreator.getCard("Michelangelo Buonarroti");
    LeaderCard otherCard = (LeaderCard) InstanceCreator.getCard("Ludovico III Gonzaga");

    @Test(expected = IllegalStateException.class)
    public void sameCard(){
        ActionCopyLeaderCard action = new ActionCopyLeaderCard(player, myCard, myCard);
    }
    @Test
    public void test(){
        int originalId = myCard.getId();
        ActionCopyLeaderCard action = new ActionCopyLeaderCard(player, myCard, otherCard);
        action.start(match);
        assertEquals(myCard.getRequirements(), otherCard.getRequirements());
        assertEquals(myCard.getCardRequirements(), otherCard.getCardRequirements());
        assertEquals(myCard.getEffects(), otherCard.getEffects());
        assertEquals(myCard.isPermanent(), otherCard.isPermanent());
        assertTrue(myCard.isPlayed());
        assertTrue(myCard.getId() == originalId);
    }

}
