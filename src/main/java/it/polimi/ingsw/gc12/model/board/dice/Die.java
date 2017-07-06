package it.polimi.ingsw.gc12.model.board.dice;

import java.io.Serializable;
import java.util.Observable;

public class Die extends Observable implements Serializable{
	private DieColor color;
	private Integer value;
	private static final int MAX_VALUE = 6;
	
	public Die(DieColor color){
		this.color = color;
	}
	
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

	public void setValue(Integer value) {
		this.value = value;
		setChanged();
		notifyObservers(value);
	}
}
