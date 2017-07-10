package it.polimi.ingsw.gc_12.EventTests;

import it.polimi.ingsw.gc12.model.board.occupiable.Tower;
import it.polimi.ingsw.gc12.model.board.occupiable.TowerFloor;
import it.polimi.ingsw.gc12.model.card.CardDevelopment;
import it.polimi.ingsw.gc12.model.card.CardType;
import it.polimi.ingsw.gc12.model.event.EventTowerChosen;
import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.model.player.familymember.FamilyMember;
import it.polimi.ingsw.gc12.model.player.familymember.FamilyMemberColor;
import it.polimi.ingsw.gc_12.InstanceCreator;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class TowerChosenTest {

    Match match = InstanceCreator.createMatch(2);
    Tower tower = match.getBoard().getTowerSet().getTower(CardType.BUILDING);
    Player player = match.getPlayer("p0");
    FamilyMember familyMember = player.getFamilyMember(FamilyMemberColor.BLACK);
    EventTowerChosen event;

    @Test
    public void testConstructor(){
        try {
            event = new EventTowerChosen(player, familyMember, tower);
            assertEquals(player, event.getPlayer());
            assertEquals(familyMember, event.getFamilyMember());
            assertEquals(tower, event.getTower());

            event.toString();
        }catch(Exception e){
            fail(e.getMessage());
        }
    }

    @Test
    public void testSetActions(){
        CardDevelopment card = InstanceCreator.createEmptyDevelopmentCard();
        for(TowerFloor floor : tower.getFloors()){
            floor.setCard(card);
        }
        familyMember.setValue(7);
        event = new EventTowerChosen(player, familyMember, tower);
        event.setActions(match);
        assertEquals(5, event.getActions().size());
    }
}
