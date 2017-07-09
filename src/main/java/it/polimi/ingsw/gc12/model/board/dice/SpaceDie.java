package it.polimi.ingsw.gc12.model.board.dice;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * This class contains all the die in a map ColorDie-Die, it also responsible to roll the dice
 */
public class SpaceDie implements Serializable{
	private Map<DieColor, Die> dice = new HashMap<>();

    /**
     * Constructor
     * creates the die and put it into the list ColorDie-Die
     * Roll the dice the first time
     */
    public SpaceDie(){
    	for(DieColor dieColor : DieColor.values()){
    		dice.put(dieColor, new Die(dieColor));
    	}
    	rollDice();
    }

    /**
     * When the dice need to be rolled this method is called
     */
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
