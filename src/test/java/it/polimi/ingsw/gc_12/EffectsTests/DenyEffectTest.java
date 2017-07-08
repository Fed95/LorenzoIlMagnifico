package it.polimi.ingsw.gc_12.EffectsTests;

import it.polimi.ingsw.gc12.model.card.CardDevelopment;
import it.polimi.ingsw.gc12.model.effect.Effect;
import it.polimi.ingsw.gc12.model.effect.EffectChangeFamilyMemberValue;
import it.polimi.ingsw.gc12.model.effect.EffectDenyEffect;
import it.polimi.ingsw.gc12.model.event.Event;
import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.model.player.familymember.FamilyMember;
import it.polimi.ingsw.gc12.model.player.familymember.FamilyMemberColor;
import it.polimi.ingsw.gc12.model.player.resource.ResourceType;
import it.polimi.ingsw.gc_12.InstanceCreator;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DenyEffectTest {

    Match match = InstanceCreator.createMatch(2);
    Player player = match.getPlayer("p0");
    String description = "test";
    Event event = mock(Event.class);
    List<FamilyMemberColor> colors = new ArrayList<>();
    CardDevelopment card = InstanceCreator.createEmptyDevelopmentCard();
    EffectDenyEffect effect;

    /*
    @Test
    public void testConstructor(){
        try {
            effect = new EffectDenyEffect(event, card, description);
            assertEquals(event, effect.getEvent());
            assertEquals(card, effect.getEffectProvider());
            assertEquals(description, effect.getDescription());

            effect = new EffectDenyEffect(event, description);
            assertEquals(event, effect.getEvent());
            assertEquals(description, effect.getDescription());
        }catch(Exception e){
            fail(e.getMessage());
        }
    }

    @Test
    public void testExecute(){
        when(event.getPlayer()).thenReturn(player);
        when(event.getEffectProviders()).thenReturn(Collections.singletonList(card));

        for(FamilyMemberColor color : FamilyMemberColor.values()) {
            colors.add(color);
            player.getFamilyMember(color).setValue(1);
        }
        EffectChangeFamilyMemberValue effect1 = new EffectChangeFamilyMemberValue(event, 1, colors, true);
        EffectChangeFamilyMemberValue effect2 = new EffectChangeFamilyMemberValue(event, 1, colors, false);

        card.getEffects().add(effect1);

        when(event.getPlayer()).thenReturn(player);

        effect = new EffectDenyEffect(event, card, description);
        effect.execute(match, event, false);
        for(FamilyMember familyMember : player.getFamilyMembers().values())
            assertEquals(0, familyMember.getValue());
    }
    */
}
