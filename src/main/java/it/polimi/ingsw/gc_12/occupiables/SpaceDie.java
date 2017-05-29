package it.polimi.ingsw.gc_12.occupiables;

import it.polimi.ingsw.gc_12.Die;
import it.polimi.ingsw.gc_12.DieColor;

import java.util.HashMap;
import java.util.Map;

public class SpaceDie {
	private static SpaceDie instance;
	private Map<DieColor, Die> dice = new HashMap<>();
	
	// EffectHandler is a Singleton
	public static SpaceDie instance() {
		if(instance == null) instance = new SpaceDie();
		return instance;
	}
    
	
    private SpaceDie(){
    	for(DieColor dieColor : DieColor.values()){
    		dice.put(dieColor, new Die(dieColor));
    	}
    	rollDice();
    }
	
    public void rollDice(){
		//call roll method for each die;
		for(DieColor dieColor : DieColor.values()){
    		dice.get(dieColor).roll();
    	}
	}
    
    public Die getDie(DieColor color) {
    	return dice.get(color);
    }

	public Map<DieColor, Die> getDice() {
		return dice;
	}

	public int getDiceNum() {
    	return dice.size();
	}
}
