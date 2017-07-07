package it.polimi.ingsw.gc12.model.event;

import it.polimi.ingsw.gc12.model.player.familymember.FamilyMember;
import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.controller.ActionHandler;
import it.polimi.ingsw.gc12.model.action.ActionSetFamilyMemberValue;

import java.util.ArrayList;

public class EventSetFamilyMemberValue extends Event {

    private int value;

    public EventSetFamilyMemberValue(Player player, int value) {
        super(player);
        this.value = value;
    }

    @Override
    public void setActions(Match match) {
        actions = new ArrayList<>();
        for (FamilyMember familyMember : player.getFamilyMembers().values())
            actions.add(new ActionSetFamilyMemberValue(player, familyMember, value));
    }

    @Override
    public String toString() {
        return player.getName() + " can set a family member's value to " + value;
    }
}
