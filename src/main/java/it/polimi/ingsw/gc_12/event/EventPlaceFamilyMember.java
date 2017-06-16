package it.polimi.ingsw.gc_12.event;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import it.polimi.ingsw.gc_12.effect.EffectProvider;
import it.polimi.ingsw.gc_12.FamilyMember;
import it.polimi.ingsw.gc_12.occupiables.Occupiable;
import it.polimi.ingsw.gc_12.Player;

public class EventPlaceFamilyMember extends Event{
	
	private List<Occupiable> occupiables = new ArrayList<>();
	private FamilyMember familyMember;
	
	public EventPlaceFamilyMember(Player player, List<Occupiable> occupiables, FamilyMember familyMember) {
		super(player);
		this.occupiables = occupiables;
		this.familyMember = familyMember;

		effectProviders.addAll(occupiables);
	}
	public EventPlaceFamilyMember(List<Occupiable> occupiables, FamilyMember familyMember) {
		super();
		this.occupiables = occupiables;
		this.familyMember = familyMember;

		effectProviders.addAll(occupiables);
	}

	public EventPlaceFamilyMember(List<Occupiable> occupiables){
		super();
		this.occupiables = occupiables;

		effectProviders.addAll(occupiables);
	}

	
	public EventPlaceFamilyMember(Player player, Occupiable occupiable, FamilyMember familyMember) {
		this(player, new ArrayList<>(Arrays.asList(occupiable)), familyMember);
	}

	public EventPlaceFamilyMember(){super();}

	
	public List<Occupiable> getOccupiables() {
		return occupiables;
	}

	public Occupiable getOccupiable() {
		return occupiables.get(0);
	}
	
	@Override
	public List<Object> getAttributes() {
		List<Object> attributes = new ArrayList<>();
		attributes.addAll(occupiables);
		attributes.add(familyMember);
		return attributes;
	}

	@Override
	public List<EffectProvider> getEffectProviders() {
		return effectProviders;
	}
	
	public FamilyMember getFamilyMember() {
		return familyMember;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((occupiables == null) ? 0 : occupiables.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		//Compares class
		if (!super.equals(obj)) {
			System.out.println("classes don't match");
			return false;
		}
		EventPlaceFamilyMember other = (EventPlaceFamilyMember) obj;

		if(!this.familyMember.equals(other.getFamilyMember())){
			return false;
		}

		// Check if they have an occupiable in common
		List<Occupiable> common = new ArrayList<>(occupiables);
		common.retainAll(other.occupiables);
		if (occupiables == null) {
			if (other.occupiables != null)
				return false;
		} else if (common.size() == 0){
			System.out.println("no occupiables in common found");
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(System.getProperty("line.separator"));
		sb.append(player.getName() + " has placed the " + familyMember.getColor() + " Family Member on:").append(System.getProperty("line.separator"));
		sb.append(occupiables.get(0).toString());
		return sb.toString();
	}
}