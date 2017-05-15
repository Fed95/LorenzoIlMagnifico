package it.polimi.ingsw.gc_12.event;

import java.util.ArrayList;
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
	}
	public EventPlaceFamilyMember(Player player, Occupiable occupiable, FamilyMember familyMember) {
		super(player);
		List<Occupiable> occupiables = new ArrayList<>();
		occupiables.add(occupiable);
		this.occupiables = occupiables;
		this.familyMember = familyMember;
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
		effectProviders.addAll(occupiables);
		return effectProviders;
	}
}