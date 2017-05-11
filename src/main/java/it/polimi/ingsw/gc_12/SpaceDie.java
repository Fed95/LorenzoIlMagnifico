package it.polimi.ingsw.gc_12;

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
}
