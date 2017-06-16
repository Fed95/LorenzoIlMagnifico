package it.polimi.ingsw.gc_12.event;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import it.polimi.ingsw.gc_12.FamilyMember;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.action.Action;
import it.polimi.ingsw.gc_12.action.ActionChooseFamilyMember;
import it.polimi.ingsw.gc_12.action.ActionPassTurn;
import it.polimi.ingsw.gc_12.action.ActionPlace;
import it.polimi.ingsw.gc_12.effect.EffectProvider;
import it.polimi.ingsw.gc_12.occupiables.Occupiable;

public class EventChooseFamilyMember extends Event {

	private FamilyMember familyMember;
	
	public EventChooseFamilyMember(Player player, FamilyMember familyMember) {
		super(player);
		this.familyMember = familyMember;

		effectProviders.addAll(player.getCards());
		effectProviders.addAll(player.getExcommunications());
	}
	
	public EventChooseFamilyMember(FamilyMember familyMember) {
		super();
		this.familyMember = familyMember;
	}

	public EventChooseFamilyMember() {
		super();
	}

	@Override
	public List<Object> getAttributes() {
		List<Object> attributes = new ArrayList<>();
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
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(System.getProperty("line.separator"));
		sb.append(player.getName() + " has chosen the " + familyMember.getColor() + " Family Member (value " + familyMember.getValue() + ")");
		return sb.toString();
	}
}
