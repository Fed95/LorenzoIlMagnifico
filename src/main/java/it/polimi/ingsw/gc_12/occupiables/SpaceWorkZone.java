package it.polimi.ingsw.gc_12.occupiables;

import it.polimi.ingsw.gc_12.FamilyMember;
import it.polimi.ingsw.gc_12.FamilyMemberColor;
import it.polimi.ingsw.gc_12.WorkType;
import it.polimi.ingsw.gc_12.Zone;
import it.polimi.ingsw.gc_12.resource.ResourceType;

import java.util.ArrayList;
import java.util.List;

public class SpaceWorkZone implements Zone {

    private List<SpaceWork> spaceWorks = new ArrayList<>();

    public SpaceWorkZone(){
    }

    //Checks whether the workspace is already taken by a member of the same family
    @Override
    public boolean canBeOccupiedBy(FamilyMember familyMember) {
        if(familyMember.getColor().equals(FamilyMemberColor.NEUTRAL))
            return true;

        for(SpaceWork spaceWork : spaceWorks)
            for(FamilyMember occupier: spaceWork.getOccupiers())
                if(!occupier.getColor().equals(FamilyMemberColor.NEUTRAL) && familyMember.getOwner().equals(occupier.getOwner()))
                    return false;
        return true;
    }

    public void addSpaceWork(SpaceWork spaceWork){
        this.spaceWorks.add(spaceWork);
    }

    public List<SpaceWork> getSpaceWorks() {
        return spaceWorks;
    }

    public void refresh() {
        for(SpaceWork spaceWork : spaceWorks)
            spaceWork.free();
    }

    @Override
    public List<Occupiable> getOccupiables() {
        List<Occupiable> occupiables = new ArrayList<>();
        for(SpaceWork spaceWork : spaceWorks) {
            occupiables.add(spaceWork);
        }
        return occupiables;
    }

    public WorkType getType(){
        return spaceWorks.get(0).getWorkType();
    }

    @Override
    public String toString() {
        return "Work Zone of type: " + this.getType();
    }
}
