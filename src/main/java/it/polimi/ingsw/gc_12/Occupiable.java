package it.polimi.ingsw.gc_12;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.gc_12.effect.Effect;

public abstract class Occupiable extends EffectProvider{
	private final int maxNumberOfPlayer;
	public final static int DEFAULT_MAXNUMBEROFPLAYER=1;
	public static final int DEFAULT_REQUIRED_VALUE = 1;
	private int requiredValue;
	private FamilyMember occupier;
	
	public Occupiable(int maxNumberOfPlayer,int requiredValue, List<Effect> effects) {
		super(effects);
		this.maxNumberOfPlayer=maxNumberOfPlayer;
		this.requiredValue = requiredValue;
	}
	public Occupiable(int requiredValue, List<Effect> effects) {
		this(DEFAULT_MAXNUMBEROFPLAYER, requiredValue, effects);
	}
	public Occupiable(List<Effect> effects) {
		this(DEFAULT_MAXNUMBEROFPLAYER, DEFAULT_REQUIRED_VALUE, effects);
	}
	
	public Occupiable() {
		this(DEFAULT_MAXNUMBEROFPLAYER, DEFAULT_REQUIRED_VALUE, new ArrayList<>());
	}
	
	public boolean isOccupied() {
		return occupier != null;
	}
	
	public FamilyMember getOccupier() {
		return occupier;
	}
	
	private boolean canBeOccupiedBy(FamilyMember occupier) {
		if(isOccupied())
			return false;
		
		return occupier.getValue() >= requiredValue;
	}
	
	public boolean placeFamilyMember(FamilyMember occupier) {
		if(!canBeOccupiedBy(occupier))
			return false;
		
		this.occupier = occupier;
		return true;
	}
}
