package it.polimi.ingsw.gc_12.occupiables;

import it.polimi.ingsw.gc_12.FamilyMember;
import it.polimi.ingsw.gc_12.Zone;
import it.polimi.ingsw.gc_12.effect.Effect;
import it.polimi.ingsw.gc_12.effect.EffectChangeResource;
import it.polimi.ingsw.gc_12.event.Event;
import it.polimi.ingsw.gc_12.event.EventPlaceFamilyMember;
import it.polimi.ingsw.gc_12.resource.CouncilPrivilege;
import it.polimi.ingsw.gc_12.resource.ResourceExchange;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CouncilPalace extends Occupiable implements Zone, Serializable {

    public CouncilPalace(int requiredValue, List<Effect> effects){
        super(requiredValue, effects);
        Event event = new EventPlaceFamilyMember(new ArrayList<>(Collections.singletonList(this)));
        ResourceExchange resourceExchange = new ResourceExchange(new ArrayList<>(), new ArrayList<>(Collections.singletonList(new CouncilPrivilege(1))));
        this.effects.add(new EffectChangeResource(event, resourceExchange, false));
    }

    public void placeFamilyMember(FamilyMember familyMember){
        this.occupiers.add(familyMember);
    }

    @Override
    public boolean canBeOccupiedBy(FamilyMember familyMember) {
        return true;
    }

    @Override
    public List<Occupiable> getOccupiables() {
        return new ArrayList<>(Collections.singletonList(this));
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Council Palace            ");//.append(System.getProperty("line.separator"));
        sb.append(super.toString());
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof CouncilPalace;
    }
}
