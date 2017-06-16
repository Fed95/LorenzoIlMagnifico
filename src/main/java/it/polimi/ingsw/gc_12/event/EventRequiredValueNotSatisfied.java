package it.polimi.ingsw.gc_12.event;

import it.polimi.ingsw.gc_12.FamilyMember;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.effect.EffectProvider;
import it.polimi.ingsw.gc_12.occupiables.Occupiable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class EventRequiredValueNotSatisfied extends EventPlaceFamilyMember {

	public EventRequiredValueNotSatisfied(Player player, Occupiable occupiable, FamilyMember familyMember) {
		super(player, new ArrayList<>(Collections.singletonList(occupiable)), familyMember);
	}

	@Override
	public String toString() {
		return "EventRequiredValueNotSatisfied";
	}
}
