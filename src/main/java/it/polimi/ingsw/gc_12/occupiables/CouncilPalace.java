package it.polimi.ingsw.gc_12.occupiables;

import it.polimi.ingsw.gc_12.FamilyMember;
import it.polimi.ingsw.gc_12.effect.Effect;
import java.util.List;


public class CouncilPalace extends Occupiable {

    public CouncilPalace(int requiredValue, List<Effect> effects){
        super(requiredValue, effects);
    }

    public void placeFamilyMember(FamilyMember familyMember){
        this.occupiers.add(familyMember);
    }
}
