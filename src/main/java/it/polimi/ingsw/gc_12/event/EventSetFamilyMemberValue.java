package it.polimi.ingsw.gc_12.event;

import it.polimi.ingsw.gc_12.FamilyMember;
import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.action.ActionChooseExchange;
import it.polimi.ingsw.gc_12.action.ActionHandler;
import it.polimi.ingsw.gc_12.action.ActionSetFamilyMemberValue;

import java.util.ArrayList;

public class EventSetFamilyMemberValue extends Event {

    private int value;

    public EventSetFamilyMemberValue(Player player, int value) {
        super(player);
        this.value = value;
    }

    @Override
    public void setActions(ActionHandler actionHandler, Match match) {
        actions = new ArrayList<>();
        for (FamilyMember familyMember : player.getFamilyMembers().values())
            actions.add(new ActionSetFamilyMemberValue(player, familyMember, value));
    }

    @Override
    public String toString() {
        return player.getName() + " can set a family member's value to " + value;
    }
}
