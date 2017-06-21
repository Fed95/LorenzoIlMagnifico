package it.polimi.ingsw.gc_12.event;

import it.polimi.ingsw.gc_12.FamilyMember;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.effect.EffectProvider;

import java.util.List;

/**
 * Created by feder on 2017-06-18.
 */
public class EventMarketChosen extends Event {

    private FamilyMember familyMember;

    public EventMarketChosen(Player player, FamilyMember familyMember) {
        super(player);
        this.familyMember = familyMember;
    }

    public FamilyMember getFamilyMember() {
        return familyMember;
    }

    @Override
    public List<Object> getAttributes() {
        return null;
    }

    @Override
    public String toString() {
        return player.getName() + " is viewing the Market";
    }
}
