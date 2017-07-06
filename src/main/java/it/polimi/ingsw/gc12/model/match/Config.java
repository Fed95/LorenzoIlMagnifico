package it.polimi.ingsw.gc12.model.match;

import it.polimi.ingsw.gc12.model.player.resource.Resource;

import java.util.List;

public class Config {

	private List<List<Resource>> initialResources;
	private List<List<Resource>> councilPrivilegeResources;
	private List<Integer> requiredValues;
	private int timeoutAction;
	private int timeoutStart;
	private int minPlayers;
	private int maxPlayers;

	public List<List<Resource>> getInitialResources() {
		return initialResources;
	}

	public void setInitialResources(List<List<Resource>> initialResources) {
		this.initialResources = initialResources;
	}

	public List<List<Resource>> getCouncilPrivilegeResources() {
		return councilPrivilegeResources;
	}

	public void setCouncilPrivilegeResources(List<List<Resource>> councilPrivilegeResources) {
		this.councilPrivilegeResources = councilPrivilegeResources;
	}

	public List<Integer> getRequiredValues() {
		return requiredValues;
	}

	public void setRequiredValues(List<Integer> requiredValues) {
		this.requiredValues = requiredValues;
	}

	public int getTimeoutAction() {
		return timeoutAction;
	}

	public int getTimeoutStart() {
		return timeoutStart;
	}

	public int getMinPlayers() {
		return minPlayers;
	}

	public int getMaxPlayers() {
		return maxPlayers;
	}
}
