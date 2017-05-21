package it.polimi.ingsw.gc_12;

import java.util.List;

public class Market {
	private final int numberOfSpaces;
	private List<SpaceMarket> spaceMarket;
	
	public Market(int setNumber){
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
	public int getNumberOfSpaces(){
		return numberOfSpaces;
	}
}
