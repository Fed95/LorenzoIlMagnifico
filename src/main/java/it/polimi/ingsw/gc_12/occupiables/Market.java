package it.polimi.ingsw.gc_12.occupiables;

import java.util.ArrayList;
import java.util.List;

public class Market {
	
	private final int numberOfSpaces;
	private List<SpaceMarket> spaceMarkets;
	public static final int DEFAULT_SET_NUM = 4;
	
	public Market(int setNumber){
		spaceMarkets = new ArrayList<>();
		if(setNumber < 0){
			throw new IllegalArgumentException();
		}else{
			this.numberOfSpaces = setNumber;
		}
		for(int i = 0; i < numberOfSpaces; i++){
			//TODO implement Json configuration with the right spacemarket constructor
			//spaceMarket.add(new SpaceMarket());
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

	public void addSpaceMarket(SpaceMarket spaceMarket) {
		this.spaceMarkets.add(spaceMarket);
	}
}
