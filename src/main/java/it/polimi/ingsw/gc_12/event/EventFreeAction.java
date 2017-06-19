package it.polimi.ingsw.gc_12.event;

import it.polimi.ingsw.gc_12.FamilyMember;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.action.Action;
import it.polimi.ingsw.gc_12.effect.EffectProvider;
import it.polimi.ingsw.gc_12.occupiables.Occupiable;

import java.nio.channels.AcceptPendingException;
import java.util.ArrayList;
import java.util.List;

public class EventFreeAction extends Event {

    private FamilyMember familyMember;
    private List<Occupiable> occupiables;

    public EventFreeAction(Player player, FamilyMember familyMember, List<Occupiable> occupiables, List<Action> actions) {
        super(player);
        this.familyMember = familyMember;
        this.occupiables = occupiables;
        this.actions = actions;
    }

    public EventFreeAction(Player player, FamilyMember familyMember, List<Occupiable> occupiables){
        this(player, familyMember, occupiables, new ArrayList<>());
    }

    public List<Occupiable> getOccupiables() {
        return occupiables;
    }

    public FamilyMember getFamilyMember() {
        return familyMember;
    }

    @Override
    public List<Object> getAttributes() {
        return new ArrayList<>();
    }

    @Override
    public List<EffectProvider> getEffectProviders() {
        return effectProviders;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(System.getProperty("line.separator"));
        sb.append(player.getName()+ " has received a free action!");
        return sb.toString();
    }
}
