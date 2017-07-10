package it.polimi.ingsw.gc_12.EffectsTests;

import it.polimi.ingsw.gc12.misc.exception.ActionDeniedException;
import it.polimi.ingsw.gc12.model.card.CardDevelopment;
import it.polimi.ingsw.gc12.model.effect.EffectCardDiscount;
import it.polimi.ingsw.gc12.model.effect.EffectChangeFamilyMemberValue;
import it.polimi.ingsw.gc12.model.event.Event;
import it.polimi.ingsw.gc12.model.event.EventPlaceFamilyMember;
import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.model.player.familymember.FamilyMember;
import it.polimi.ingsw.gc12.model.player.familymember.FamilyMemberColor;
import it.polimi.ingsw.gc12.model.player.resource.Money;
import it.polimi.ingsw.gc12.model.player.resource.Resource;
import it.polimi.ingsw.gc12.model.player.resource.ResourceType;
import it.polimi.ingsw.gc_12.InstanceCreator;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ChangeFamilyMemberValueTest {

    Match match = InstanceCreator.createMatch(2);
    Player player = match.getPlayer("p0");
    int amount = 3;
    List<FamilyMemberColor> colors = new ArrayList<>();
    Event event = mock(Event.class);
    EventPlaceFamilyMember eventPlaceFamilyMember = mock(EventPlaceFamilyMember.class);
    FamilyMember familyMember = player.getFamilyMember(FamilyMemberColor.BLACK);
    EffectChangeFamilyMemberValue effect;

    @Test
    public void testConstructor(){
        try {
            effect = new EffectChangeFamilyMemberValue(event, amount);
            assertEquals(event, effect.getEvent());
            assertEquals(amount, effect.getAmount());

            effect = new EffectChangeFamilyMemberValue(event, amount, colors, true);
            assertEquals(event, effect.getEvent());
            assertEquals(amount, effect.getAmount());
            assertEquals(colors, effect.getColors());
            assertTrue(effect.isSetValue());

        }catch(Exception e){
            fail(e.getMessage());
        }
    }

    @Test
    public void testExecute(){
        for(FamilyMemberColor color : FamilyMemberColor.values()) {
            player.getFamilyMember(color).setValue(1);
            colors.add(color);
        }
        when(eventPlaceFamilyMember.getFamilyMember()).thenReturn(familyMember);
        when(event.getPlayer()).thenReturn(player);

        effect = new EffectChangeFamilyMemberValue(eventPlaceFamilyMember, amount);
        effect.execute(match, eventPlaceFamilyMember, false);
        assertEquals(java.util.Optional.of(4), java.util.Optional.ofNullable(familyMember.getValue()));

        effect = new EffectChangeFamilyMemberValue(eventPlaceFamilyMember, amount, colors, true);
        effect.execute(match, eventPlaceFamilyMember, false);
        assertEquals(java.util.Optional.of(3), java.util.Optional.ofNullable(familyMember.getValue()));

        for(FamilyMemberColor color : FamilyMemberColor.values()) {
            player.getFamilyMember(color).setValue(1);
            colors.add(color);
        }
        effect = new EffectChangeFamilyMemberValue(event, amount, colors, false);
        effect.execute(match, event, false);
        for(FamilyMember familyMember : player.getFamilyMembers().values())
            assertEquals(java.util.Optional.of(4), java.util.Optional.ofNullable(familyMember.getValue()));
    }
}
