package it.polimi.ingsw.gc_12.resource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ResourceExchange {

	private List<Resource> cost;
	private List<Resource> bonus;

	public ResourceExchange(List<Resource> cost, List<Resource> bonus) {
		this.cost = cost;
		this.bonus = bonus;
	}

	public ResourceExchange(Resource cost, Resource resource) {
		this(new ArrayList<>(Arrays.asList(cost)), new ArrayList<>(Arrays.asList()));
	}

	public List<Resource> getCost() {
		return cost;
	}

	public List<Resource> getBonus() {
		return bonus;
	}

	@Override
	public String toString() {
		return "Cost: " + cost + ", Bonus: " + bonus;
	}
}
