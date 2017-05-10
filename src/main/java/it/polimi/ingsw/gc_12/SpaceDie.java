package it.polimi.ingsw.gc_12;

import java.util.HashMap;

public class SpaceDie {
    private HashMap<DieColor, Die> DieColorHash;
	
    public SpaceDie(){
    	for(DieColor dieColor : DieColor.values()){
    		DieColorHash.put(dieColor,new Die(dieColor));
    	}
    }
	public void rollDice(){
		//call roll method for each die;
		for(DieColor dieColor : DieColor.values()){
    		DieColorHash.get(dieColor).roll();
    	}
	}
}
