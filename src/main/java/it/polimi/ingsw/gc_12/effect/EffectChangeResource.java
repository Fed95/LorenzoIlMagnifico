package it.polimi.ingsw.gc_12.effect;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.action.Action;
import it.polimi.ingsw.gc_12.resource.Resource;

public class EffectChangeResource extends Effect implements EffectInterface{

	private List<Resource> costs = new ArrayList<>();
	private List<Resource> resources = new ArrayList<>();
	
	public EffectChangeResource(Action action, List<Resource> costs, List<Resource> resources) {
		super(action);
		this.costs = costs;
		this.resources = resources;
	}
	
	public EffectChangeResource(Action action, Resource cost, Resource resource) {
		super(action);
		List<Resource> costs =  new ArrayList<>();
		costs.add(cost);
		this.costs = costs;
		
		List<Resource> resources = new ArrayList<>();
		resources.add(resource);
		this.resources = resources;
	}
	
	public void execute() {
		Player player = action.getPlayer();
		player.removeResources(costs);
		player.addResources(resources);
	}
	
	public void discard() {
		Player player = action.getPlayer();
		player.addResources(costs);
		player.removeResources(resources);
	}

}
