package it.polimi.ingsw.gc_12.occupiables;

import it.polimi.ingsw.gc_12.FamilyMember;
import it.polimi.ingsw.gc_12.Zone;
import it.polimi.ingsw.gc_12.effect.Effect;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CouncilPalace extends Occupiable implements Zone, Serializable {

    public CouncilPalace(int requiredValue, List<Effect> effects){
        super(requiredValue, effects);
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
    public String toString() {
        return "Council Palace";
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof CouncilPalace;
    }
}
