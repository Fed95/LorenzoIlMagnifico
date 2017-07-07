package it.polimi.ingsw.gc12.model.board.dice;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class SpaceDie implements Serializable{
	private Map<DieColor, Die> dice = new HashMap<>();

    public SpaceDie(){
    	for(DieColor dieColor : DieColor.values()){
    		dice.put(dieColor, new Die(dieColor));
    	}
    	rollDice();
    }
	
    public void rollDice(){
		for(Die die : dice.values()){
    		die.roll();
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