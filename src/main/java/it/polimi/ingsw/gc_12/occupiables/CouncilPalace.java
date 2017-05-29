package it.polimi.ingsw.gc_12.occupiables;

import it.polimi.ingsw.gc_12.FamilyMember;
import it.polimi.ingsw.gc_12.Occupiable;
import it.polimi.ingsw.gc_12.effect.Effect;
import it.polimi.ingsw.gc_12.exceptions.CannotPlaceFamilyMemberException;

import java.util.ArrayList;
import java.util.List;


public class CouncilPalace extends Occupiable {
    private List<FamilyMember> occupiers = new ArrayList<>();

    public CouncilPalace(int requiredValue, List<Effect> effects){
        super(requiredValue, effects);
    }

    @Override
    public void canBeOccupiedBy(FamilyMember occupier) throws CannotPlaceFamilyMemberException{
    }
}
