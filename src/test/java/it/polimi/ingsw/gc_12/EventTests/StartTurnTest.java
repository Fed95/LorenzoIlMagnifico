package it.polimi.ingsw.gc_12.EventTests;

import it.polimi.ingsw.gc12.model.action.Action;
import it.polimi.ingsw.gc12.model.card.LeaderCard;
import it.polimi.ingsw.gc12.model.event.EventStartTurn;
import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.model.player.familymember.FamilyMember;
import it.polimi.ingsw.gc12.model.player.familymember.FamilyMemberColor;
import it.polimi.ingsw.gc_12.InstanceCreator;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class StartTurnTest {

    Match match = InstanceCreator.createMatch(2);
    Player player = match.getPlayer("p0");
    LeaderCard permanentLeaderCard = InstanceCreator.createEmptyLeaderCard(true);
    LeaderCard leaderCard = InstanceCreator.createEmptyLeaderCard(false);
    FamilyMember familyMember = player.getFamilyMember(FamilyMemberColor.BLACK);
    List<Action> actions = new ArrayList<>();
    EventStartTurn event;

    @Test
    public void testConstructor(){
        try {
            event = new EventStartTurn(player, actions, 1);
            event = new EventStartTurn(player, 1);
            event = new EventStartTurn(player);
        }catch(Exception e){
            fail(e.getMessage());
        }
    }

    @Test
    public void testSetActions(){

        leaderCard.setPlayed(true);
        player.getPersonalBoard().getLeaderCardsSpace().getCards().clear();
        player.getPersonalBoard().getLeaderCardsSpace().getCards().add(leaderCard);
        player.getPersonalBoard().getLeaderCardsSpace().getCards().add(permanentLeaderCard);

        event = new EventStartTurn(player);

        event.setActions(match);
        assertEquals(8, event.getActions().size());

        match.getActionHandler().setHasPlaced(true);
        event.setActions(match);
        assertEquals(5, event.getActions().size());
    }
}
