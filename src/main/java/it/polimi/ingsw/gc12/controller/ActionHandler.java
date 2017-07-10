package it.polimi.ingsw.gc12.controller;

import it.polimi.ingsw.gc12.model.action.Action;
import it.polimi.ingsw.gc12.model.action.ActionReady;
import it.polimi.ingsw.gc12.model.event.Event;
import it.polimi.ingsw.gc12.model.player.resource.Resource;
import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.player.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Since the server only receives [int] values from the client, the main purpose of the ActionHandler is to retrieve the corresponding action.
 *
 * The ActionHandler receives Events from the Model and prompts them to initialize their actions.
 * Once the event has initialized its actions, the ActionHandler stores a copy of them and adds the Event to its Event list
 * When the server receives an input from the client, it can retrieve the correct action through ActionHandler.getAvailableAction.
 *
 * A specific rule is applied to EventReceiveCouncilPrivilege since, unlike other events, the player can perform more than one action for the single event.
 * When an EventReceiveCouncilPrivilege is initialised, the value of the CouncilPrivilege is stored in the action handler.
 * When the ActionHandler is asked for an action, if the amount of Privileges is still greater than 1, the event is not removed from the list.
 * The selected action instead is removed. This is to prevent the player from choosing twice the same ResourceExchange offered by the CouncilPrivilege
 */
public class ActionHandler {
	private List<Action> actions = new ArrayList<>();
	private LinkedList<Event> events = new LinkedList<>();
	private int offset;
	private boolean hasPlaced = false;
	private int councilPrivileges = 0;
	private List<List<Resource>> councilPrivilegeResources = new ArrayList<>();
	private List<Player> players;

	public ActionHandler() {
		this.actions = new ArrayList<>(Collections.singletonList(new ActionReady(null)));
	}

	public Action getAvailableAction(int input) {
		Action action = actions.get(input);

		if(events.size() > 0) {
			if(councilPrivileges <= 1) {
				events.removeFirst();
				if(events.size() > 0)
					actions = events.getFirst().getActions();
			}
			else{
				councilPrivileges--;
				actions.remove(input);
			}
		}
		offset = 0;
		return action;
	}


	public void update(Event event, Match match) {
		events.addLast(event);
		event.setActions(match);
		saveActions(event);
	}

	private void saveActions(Event event) {
		if(events.size() > 0 && events.getFirst() == event) {
			actions = event.getActions();
		}
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public LinkedList<Event> getEvents() {
		return events;
	}

	public void removeLastEvent() {
		events.removeLast();
	}

	public void flushEvents() {
		events = new LinkedList<>();
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getOffset() {
		return offset;
	}

	public void setHasPlaced(boolean hasPlaced) {
		this.hasPlaced = hasPlaced;
	}

	public boolean hasPlaced() {
		return this.hasPlaced;
	}

	public List<List<Resource>> getCouncilPrivilegeResources() {
		return councilPrivilegeResources;
	}

	public void setCouncilPrivilegeResources(List<List<Resource>> councilPrivilegeResources) {
		this.councilPrivilegeResources = councilPrivilegeResources;
	}

	public void setCouncilPrivileges(int councilPrivileges) {
		this.councilPrivileges = councilPrivileges;
	}
}
