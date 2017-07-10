package it.polimi.ingsw.gc_12.OccupiableTests;

import it.polimi.ingsw.gc12.model.board.occupiable.Tower;
import it.polimi.ingsw.gc12.model.card.CardDevelopment;
import it.polimi.ingsw.gc12.model.card.CardType;
import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.model.player.familymember.FamilyMember;
import it.polimi.ingsw.gc12.model.player.familymember.FamilyMemberColor;
import it.polimi.ingsw.gc_12.InstanceCreator;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class TowerTest {

    Match match = InstanceCreator.createMatch(2);
    Player player = match.getPlayer("p0");
    FamilyMember familyMember = player.getFamilyMember(FamilyMemberColor.BLACK);
    FamilyMember familyMember1 = player.getFamilyMember(FamilyMemberColor.ORANGE);
    FamilyMember familyMember2 = player.getFamilyMember(FamilyMemberColor.NEUTRAL);
    Tower tower = match.getBoard().getTowerSet().getTower(CardType.TERRITORY);
    Tower tower1 = match.getBoard().getTowerSet().getTower(CardType.BUILDING);

    @Test
    public void testGetCards(){
        match.start();
        assertEquals(4, tower.getCards().size());
        for(CardDevelopment card : tower.getCards()){
            assertEquals(1, card.getPeriod());
            assertEquals(CardType.TERRITORY, card.getType());
        }
    }

    @Test
    public void testCanBeOccupiedBy(){
        match.start();
        tower.getFloors().get(0).placeFamilyMember(familyMember);
        assertFalse(tower.canBeOccupiedBy(familyMember1));
        assertTrue(tower.canBeOccupiedBy(familyMember2));
    }

    @Test
    public void testGetOccupiables(){
        match.start();
        assertEquals(4, tower.getOccupiables().size());
        tower.getFloors().get(0).placeFamilyMember(familyMember);
        assertEquals(3, tower.getOccupiables().size());
    }

    @Test
    public void testEquals(){
        assertTrue(tower.equals(tower));
        assertFalse(tower.equals(tower1));
    }
}
