package it.polimi.ingsw.gc_12.occupiables;

import it.polimi.ingsw.gc_12.FamilyMember;
import it.polimi.ingsw.gc_12.Zone;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Market implements Zone, Serializable{
	
	private List<SpaceMarket> spaceMarkets = new ArrayList<>();
	public static final int DEFAULT_SET_NUM = 4;

	/*
	public Market(int setNumber){
		if(setNumber < 0){
			throw new IllegalArgumentException();
		for(int i = 0; i < setNumber; i++){
			spaceMarkets.add(new SpaceMarket(i, 2, null));
		}
	}

	
	public Market(){
		this(DEFAULT_SET_NUM);
	}
	*/

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
