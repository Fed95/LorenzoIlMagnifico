package it.polimi.ingsw.gc_12.occupiables;

import it.polimi.ingsw.gc_12.FamilyMember;
import it.polimi.ingsw.gc_12.Zone;

import java.util.ArrayList;
import java.util.List;

public class Market implements Zone {
	
	private final int numberOfSpaces;
	private List<SpaceMarket> spaceMarkets;
	public static final int DEFAULT_SET_NUM = 4;
	
	public Market(int setNumber){
		if(setNumber < 0){
			throw new IllegalArgumentException();
		}else{
			this.numberOfSpaces = setNumber;
		}
		for(int i = 0; i < numberOfSpaces; i++){
			//TODO implement Json configuration with the right spacemarket constructor
			//spaceMarkets.add(new SpaceMarket());
		}
	}
	
	public Market(){
		this(DEFAULT_SET_NUM);
	}
	public int getNumberOfSpaces(){
		return numberOfSpaces;
	}

	public List<SpaceMarket> getSpaceMarkets() {
		return spaceMarkets;
	}

	public void setSpaceMarkets(List<SpaceMarket> spaceMarkets) {
		this.spaceMarkets = spaceMarkets;
	}

    public void refresh() {
		for(SpaceMarket spaceMarket : spaceMarkets)
			spaceMarket.free();
    }

	@Override
	public boolean canBeOccupiedBy(FamilyMember familyMember) {
		return true;
	}

	@Override
	public List<Occupiable> getOccupiables() {
		List<Occupiable> occupiables = new ArrayList<>();
		occupiables.addAll(spaceMarkets);
		return occupiables;
	}
}
