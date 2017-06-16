package it.polimi.ingsw.gc_12.action;

import it.polimi.ingsw.gc_12.FamilyMember;
import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.event.*;
import it.polimi.ingsw.gc_12.occupiables.Occupiable;
import it.polimi.ingsw.gc_12.resource.ResourceType;
import it.polimi.ingsw.gc_12.resource.Servant;

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
		if(event instanceof EventChooseFamilyMember) {
			actions = getActionsChooseFamilyMember((EventChooseFamilyMember) event);
		}
		else if(event instanceof EventRequiredValueNotSatisfied) {
			actions = getActionsRequiredValue((EventRequiredValueNotSatisfied) event);
		}
		else if(event instanceof EventStartTurn || event instanceof EventPlaceFamilyMember) {
			actions = getActionsStartTurn(event);
		}
		else if(event instanceof EventRequestStatistics){
			actions = getActionsRequestStatistics(event);
		}

		event.setActions(actions);
		return actions;
	}

	public List<Action> getActionsStartTurn(Event event) {
		Player player = event.getPlayer();
		List<Action> actions = new ArrayList<>();
		if(event instanceof EventStartTurn) {
			for(FamilyMember familyMember: player.getAvailableFamilyMembers()) {
				actions.add(new ActionChooseFamilyMember(player, familyMember));
			}
		}
		actions.add(new ActionPassTurn(player));
		actions.add(new ActionRequestStatistics(player));
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

	public List<Action> getActionsRequestStatistics(Event event) {
		List<Action> actions = new ArrayList<>();
		for(Player player : match.getPlayers())
			actions.add(new ActionViewStatistics(event.getPlayer(), player));
		return actions;
	}

	public List<Action> getActionsRequiredValue(EventRequiredValueNotSatisfied event) {
		Player player = event.getPlayer();
		List<Action> actions = new ArrayList<>();
		int i = event.getOccupiable().getRequiredValue()-event.getFamilyMember().getValue();
		for (; i <= player.getResourceValue(ResourceType.SERVANT); i++) {
			Action action = ActionFactory.createActionPlace(player, event.getFamilyMember(), event.getOccupiable(), new Servant(i));
			if(action.isValid(match))
				actions.add(action);
		}
		return actions;
	}


}
