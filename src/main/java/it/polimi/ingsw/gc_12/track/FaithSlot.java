package it.polimi.ingsw.gc_12.track;

import it.polimi.ingsw.gc_12.EffectProvider;
import it.polimi.ingsw.gc_12.FamilyMember;
import it.polimi.ingsw.gc_12.effect.Effect;

import java.util.ArrayList;
import java.util.List;

public class FaithSlot implements EffectProvider {

    private List<Effect> effects = new ArrayList<>();

    protected transient List<FamilyMember> occupiers = new ArrayList<>();

    public FaithSlot(List<Effect> effects) {
        this.effects = effects;
    }

    @Override
    public List<Effect> getEffects() {
        return effects;
    }

}
