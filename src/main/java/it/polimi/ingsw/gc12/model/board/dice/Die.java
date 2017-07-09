package it.polimi.ingsw.gc12.model.board.dice;

import java.io.Serializable;
import java.util.Observable;

/**
 * This class implements the single dice of the game with a value and a color
 */
public class Die extends Observable implements Serializable{
	private DieColor color;
	private Integer value;
	private static final int MAX_VALUE = 6;

    /**
     * Constructor
     * @param color color of the die
     */
	public Die(DieColor color){
		this.color = color;
	}

    /**
     * This method choose randomly the value of the die and notify it to the family member class throw an observer pattern
     */
	public void roll(){
		value = (int)(Math.random()*MAX_VALUE) + 1;
		setChanged();
		notifyObservers(value);
	}
	
	public DieColor getColor(){
		return color;
	}
	
	public Integer getValue(){
		return value;
	}

    /**
     * If the value of the die as to e changed this method is called
     * @param value new value of the die
     */
	public void setValue(Integer value) {
		this.value = value;
		setChanged();
		notifyObservers(value);
	}
}
