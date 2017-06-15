package it.polimi.ingsw.gc_12.event;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.gc_12.FamilyMember;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.action.Action;
import it.polimi.ingsw.gc_12.action.ActionChooseFamilyMember;
import it.polimi.ingsw.gc_12.action.ActionPassTurn;
import it.polimi.ingsw.gc_12.effect.EffectProvider;

public class EventStartTurn extends Event{

	public EventStartTurn(Player player, List<Action> actions) {
		super(player);
		this.actions = actions;
	}

	public EventStartTurn(Player player) {
		this(player, new ArrayList<>());
	}

	@Override
	public List<Object> getAttributes() {
		return new ArrayList<>();
	}

	@Override
	public List<EffectProvider> getEffectProviders() {
		return effectProviders;
	}

	@Override
	public String toString() {
		return "It's" + player + "'s turn.";
	}
}
