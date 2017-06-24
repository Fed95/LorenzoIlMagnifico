package it.polimi.ingsw.gc_12.event;

import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.resource.CouncilPrivilege;

import java.util.ArrayList;
import java.util.List;

public class EventCouncilPrivilegeReceived extends Event {

	private CouncilPrivilege councilPrivilege;
	private List<Integer> choices;

	public EventCouncilPrivilegeReceived(Player player, CouncilPrivilege councilPrivilege, List<Integer> choices) {
		super(player);
		this.councilPrivilege = councilPrivilege;
		this.choices = choices;
	}

	public EventCouncilPrivilegeReceived(Player player, CouncilPrivilege councilPrivilege) {
		this(player, councilPrivilege, new ArrayList<>());
	}

	public CouncilPrivilege getCouncilPrivilege() {
		return councilPrivilege;
	}

	public List<Integer> getChoices() {
		return choices;
	}

	@Override
	public List<Object> getAttributes() {
		return null;
	}

	@Override
	public String toString() {
		return null;
	}
}
