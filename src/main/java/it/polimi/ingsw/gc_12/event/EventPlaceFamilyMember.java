package it.polimi.ingsw.gc_12.event;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.gc_12.Occupiable;
import it.polimi.ingsw.gc_12.Player;

public class EventPlaceFamilyMember extends Event{
	
	private Occupiable occupiable;
	
	public EventPlaceFamilyMember(Player player, Occupiable occupiable) {
		super(player);
		this.occupiable = occupiable;
	}
	
	public Occupiable getOccupiable() {
		return occupiable;
	}
	
	@Override
	public List<Object> getAttributes() {
		List<Object> attributes = new ArrayList<>();
		attributes.add(occupiable);
		return attributes;
	}

}