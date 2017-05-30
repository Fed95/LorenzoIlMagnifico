package it.polimi.ingsw.gc_12.dice;

import java.util.Observable;

public class Die extends Observable {
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
	
	
	

	
}
