package it.polimi.ingsw.gc_12.event;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Arrays;
import java.util.List;

import it.polimi.ingsw.gc_12.EffectProvider;
import it.polimi.ingsw.gc_12.FamilyMember;
import it.polimi.ingsw.gc_12.Occupiable;
import it.polimi.ingsw.gc_12.Player;

public class EventPlaceFamilyMember extends Event{
	
	private List<Occupiable> occupiables = new ArrayList<>();
	private FamilyMember familyMember;
	
	public EventPlaceFamilyMember(Player player, List<Occupiable> occupiables, FamilyMember familyMember) {
		super(player);
		this.occupiables = occupiables;
		this.familyMember = familyMember;

		effectProviders.addAll(player.getCards());
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
	}

	
	public EventPlaceFamilyMember(Player player, Occupiable occupiable, FamilyMember familyMember) {
		this(player, new ArrayList<Occupiable>(Arrays.asList(occupiable)), familyMember);
	}
	
	public List<Occupiable> getOccupiables() {
		return occupiables;
	}
	
	@Override
	public List<Object> getAttributes() {
		List<Object> attributes = new ArrayList<>();
		attributes.add(occupiables);
		attributes.add(familyMember);
		return attributes;
	}

	@Override
	public List<EffectProvider> getEffectProviders() {
		List<EffectProvider> effectProviders = new ArrayList<>();
		effectProviders.addAll(player.getCards());
		effectProviders.addAll((Collection<? extends EffectProvider>) occupiables);
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
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		EventPlaceFamilyMember other = (EventPlaceFamilyMember) obj;
		
		// Check if they have an occupiable in common
		List<Occupiable> common = new ArrayList<Occupiable>(occupiables);
		common.retainAll(other.occupiables);
		if (occupiables == null) {
			if (other.occupiables != null)
				return false;
		} else if (common.size() == 0)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EventPlaceFamilyMember{" +
				"player=" + player +
				", occupiables=" + occupiables +
				", familyMember=" + familyMember +
				", effectProviders=" + effectProviders +
				'}';
	}
}