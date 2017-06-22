package it.polimi.ingsw.gc_12.event;

import it.polimi.ingsw.gc_12.FamilyMember;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.occupiables.Occupiable;

import java.util.ArrayList;
import java.util.Collections;

public class EventServantsRequested extends EventPlaceFamilyMember {

	private int multiplier;

	public EventServantsRequested(Player player, Occupiable occupiable, FamilyMember familyMember) {
		super(player, new ArrayList<>(Collections.singletonList(occupiable)), familyMember);
		multiplier = 2; //TODO: SET TO 1 AFTER TESTING
	}

	public void setMultiplier(int multiplier) {
		this.multiplier = multiplier;
	}

	public int getMultiplier() {
		return multiplier;
	}

	@Override
	public String toString() {
		return "EventServantsRequested";
	}

	@Override
	public String toStringClient() {
		return "";
	}
}
