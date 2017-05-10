package it.polimi.ingsw.gc_12;
import java.util.Observable;

public class Die extends Observable {
	private DieColor color;
	private int value;
	private static final int maxValue = 6;
	
	public Die(DieColor color){
		this.color = color;
	}
	
	public void roll(){
		value = (int)(Math.random()*maxValue) + 1;
		setChanged();
		notifyObservers(value);
	}
	
	public DieColor getColor(){
		return color;
	}
	
	public int getValue(){
		return value;
	}
	
	
	

	
}
