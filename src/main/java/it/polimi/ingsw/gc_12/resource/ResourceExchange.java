package it.polimi.ingsw.gc_12.resource;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ResourceExchange implements Serializable {

	private List<Resource> cost = new ArrayList<>();
	private List<Resource> bonus = new ArrayList<>();

	public ResourceExchange(List<Resource> cost, List<Resource> bonus) {
		if(cost != null)
			this.cost = cost;
		if(bonus != null)
			this.bonus = bonus;
	}

	public ResourceExchange(Resource cost, Resource bonus) {
		if(cost != null)
			this.cost = new ArrayList<>(Arrays.asList(cost));
		if(bonus != null)
			this.bonus = new ArrayList<>(Arrays.asList(bonus));
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
