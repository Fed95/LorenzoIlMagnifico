package it.polimi.ingsw.gc_12.ActionTests;

import it.polimi.ingsw.gc12.model.action.ActionActivateLeaderCard;
import it.polimi.ingsw.gc12.model.card.LeaderCard;
import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc_12.InstanceCreator;
import org.junit.Test;

import static org.junit.Assert.*;

public class ActivateLeaderCardTest {

    Match match = InstanceCreator.createMatch(2);
    Player player = match.getPlayer("p0");

    @Test
    public void testStart(){
        //Lucrezia has an EffectSetFamilyMemberValue
        LeaderCard card = (LeaderCard) InstanceCreator.getCard("Lucrezia Borgia");
        test(card);

        //Rita doesn't have EffectSetFamilyMemberValue or EffectCopyLeaderCard
        card = (LeaderCard) InstanceCreator.getCard("Santa Rita");
        test(card);
    }

    private void test(LeaderCard card){
        ActionActivateLeaderCard action = new ActionActivateLeaderCard(player, card);
        action.start(match);

        assertTrue(card.isActivated());
    }
}
