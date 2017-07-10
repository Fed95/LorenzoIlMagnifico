package it.polimi.ingsw.gc12.model.player.resource;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Resource that has a cost for gain it
 */
public class ResourceExchange implements Serializable {

	private List<Resource> cost = new ArrayList<>();
	private List<Resource> bonus = new ArrayList<>();

    /**
     * Constructor
     * @param cost cost in recurces
     * @param bonus bonus in resources
     */
	public ResourceExchange(List<Resource> cost, List<Resource> bonus) {
		if(cost != null)
			this.cost = cost;
		if(bonus != null)
			this.bonus = bonus;
	}

    /**
     * Constructor for a single choice
     * @param cost
     * @param bonus
     */
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof ResourceExchange)) return false;

		ResourceExchange that = (ResourceExchange) o;

		if (cost != null ? !cost.equals(that.cost) : that.cost != null) return false;
		return bonus != null ? bonus.equals(that.bonus) : that.bonus == null;
	}

	@Override
	public int hashCode() {
		int result = cost != null ? cost.hashCode() : 0;
		result = 31 * result + (bonus != null ? bonus.hashCode() : 0);
		return result;
	}
}
