package it.polimi.ingsw.gc_12;

public class SpaceWork{

	public SpaceWorkMultiple spaceWorkMultiple;
	public SpaceWorkSingle spaceWorkSingle;
	
	public SpaceWork(){
	}
	
	public boolean placeFamilyMember(){
		return false;
	}
	
	//Checks whether the workspace is already taken by a member of the same family
	public boolean canBeOccupiedBy(FamilyMember occupier) {
		if(occupier.getColor().equals(FamilyMemberColor.NEUTRAL))
			return true;
		
		// TODO: find a more elegant way to cycle through the two lists
		for(FamilyMember i: spaceWorkMultiple.getOccupiers())
			if(occupier.getColor().equals(i.getColor()))
				return false;
		for(FamilyMember i: spaceWorkSingle.getOccupiers())
			if(occupier.getColor().equals(i.getColor()))
				return false;
		return true; // Further value check must be performed by subclass
	}

	public void setSpaceWorkSingle(SpaceWorkSingle spaceWorkSingle){
		this.spaceWorkSingle = spaceWorkSingle;
	}
	
	public void setSpaceWorkMultiple(SpaceWorkMultiple spaceWorkMultiple){
		this.spaceWorkMultiple = spaceWorkMultiple;
	}

}
