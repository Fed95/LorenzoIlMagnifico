package it.polimi.ingsw.gc_12;

import it.polimi.ingsw.gc_12.resource.Resource;

import java.util.List;

public class Config {

	private List<List<Resource>> initialResources;
	private List<List<Resource>> councilPrivilegeResources;
	private List<Integer> requiredValues;

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
}
