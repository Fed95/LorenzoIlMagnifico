package it.polimi.ingsw.gc_12;

import it.polimi.ingsw.gc_12.action.*;
import it.polimi.ingsw.gc_12.event.Event;
import it.polimi.ingsw.gc_12.event.EventChooseFamilyMember;
import it.polimi.ingsw.gc_12.event.EventStartMatch;
import it.polimi.ingsw.gc_12.event.EventStartTurn;
import it.polimi.ingsw.gc_12.occupiables.Occupiable;
import it.polimi.ingsw.gc_12.resource.Servant;
import it.polimi.ingsw.gc_12.server.observer.Observer;

import java.util.ArrayList;
import java.util.List;

public class ActionHandler /*implements Observer<Event> */{
	private List<Action> actions = new ArrayList<>();
	private Match match;

	public ActionHandler(Match match) {
		this.match = match;
		//match.registerObserver(this);
	}

	/*public List<Action> updateAvailableActions(Match match, Player player, FamilyMember familyMember, Servant servant) {
		actions = new ArrayList<>();
		for(Occupiable occupiable: match.getBoard().getOccupiables()) {
			ActionPlace action = ActionFactory.createActionPlace(player, familyMember, occupiable, servant);
			if(action.isValid(match)) {
				actions.add(action);
			}
		}
		return actions;
	}

	public List<Action> updateAvailableActions(Match match, Player player, FamilyMember familyMember) {
		return updateAvailableActions(match, player, familyMember, new Servant(0));
	}*/

	public List<Action> getAvailableActions() {
		return actions;
	}


	public List<Action> update(Event event) {
		if(event instanceof EventStartTurn) {
			actions = getActionsStartTurn(event);
		}
		else if(event instanceof EventChooseFamilyMember) {
			actions = getActionsChooseFamilyMember((EventChooseFamilyMember) event);
		}
		return actions;
	}

	public List<Action> getActionsStartTurn(Event event) {
		Player player = event.getPlayer();
		List<Action> actions = new ArrayList<>();
		for(FamilyMember familyMember: player.getAvailableFamilyMembers()) {
			actions.add(new ActionChooseFamilyMember(player, familyMember));
		}
		actions.add(new ActionPassTurn(player));
		return actions;
	}

	public List<Action> getActionsChooseFamilyMember(EventChooseFamilyMember event) {
		Player player = event.getPlayer();
		List<Action> actions = new ArrayList<>();
		for(Occupiable occupiable: match.getBoard().getOccupiables()) {
			ActionPlace action = ActionFactory.createActionPlace(player, event.getFamilyMember(), occupiable);
			if(action.isValid(match)) {
				actions.add(action);
			}
		}
		return actions;
	}


}
