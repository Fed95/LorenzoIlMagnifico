package it.polimi.ingsw.gc_12.event;

import it.polimi.ingsw.gc_12.FamilyMember;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.effect.EffectProvider;
import it.polimi.ingsw.gc_12.occupiables.Occupiable;

import java.util.List;

public class EventFreeAction extends Event {

    private FamilyMember familyMember;
    private List<Occupiable> occupiables;

    public EventFreeAction(Player player, FamilyMember familyMember, List<Occupiable> occupiables) {
        super(player);
        this.familyMember = familyMember;
        this.occupiables = occupiables;
    }

    public List<Occupiable> getOccupiables() {
        return occupiables;
    }

    public FamilyMember getFamilyMember() {
        return familyMember;
    }

    @Override
    public List<Object> getAttributes() {
        return null;
    }

    @Override
    public List<EffectProvider> getEffectProviders() {
        return null;
    }
}
