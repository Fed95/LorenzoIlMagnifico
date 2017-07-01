package it.polimi.ingsw.gc_12.event;

import it.polimi.ingsw.gc_12.FamilyMember;
import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.action.ActionChooseFamilyMember;
import it.polimi.ingsw.gc_12.action.ActionHandler;
import it.polimi.ingsw.gc_12.action.ActionPassTurn;
import it.polimi.ingsw.gc_12.action.ActionRequestStatistics;
import it.polimi.ingsw.gc_12.effect.EffectProvider;

import java.util.ArrayList;
import java.util.List;


public class EventPlacementEnded extends Event{

	public EventPlacementEnded(Player player) {
		super(player);
	}

	@Override
	public void setActions(ActionHandler actionHandler, Match match) {
		actions = new ArrayList<>();
		actionHandler.setHasPlaced(true);
		actions.add(new ActionPassTurn(player));
		actions.add(new ActionRequestStatistics(player));
	}

	@Override
	public String toString() {
		return null;
	}
}
