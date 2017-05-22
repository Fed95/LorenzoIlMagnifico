package it.polimi.ingsw.gc_12;

import java.util.ArrayList;
import java.util.List;

public class SpaceWorkZone {

    private List<SpaceWork> spaceWorks = new ArrayList<>();

    public SpaceWorkZone(){
    }

    public boolean placeFamilyMember(){
        return false;
    }

    //Checks whether the workspace is already taken by a member of the same family
    public boolean canBeOccupiedBy(FamilyMember occupier) {
        if(occupier.getColor().equals(FamilyMemberColor.NEUTRAL))
            return true;

        for(SpaceWork spaceWork : spaceWorks)
            for(FamilyMember i: spaceWork.getOccupiers())
                if(!occupier.getColor().equals(FamilyMemberColor.NEUTRAL) && occupier.getOwner().equals(i.getOwner()))
                    return false;
        return true; // Further value check must be performed by SpaceWorkSingle/SapaceWorkMultiple class
    }

    public void addSpaceWork(SpaceWork spaceWork){
        this.spaceWorks.add(spaceWork);
    }
}
