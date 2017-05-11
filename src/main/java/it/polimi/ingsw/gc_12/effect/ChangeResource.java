package it.polimi.ingsw.gc_12.effect;

import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.event.Event;
import it.polimi.ingsw.gc_12.resource.Resource;

public class ChangeResource extends Effect implements EffectInterface{

	private Resource resource;
	
	public ChangeResource(Event event, Resource resource) {
		super(event);
		this.resource = resource;
	}
	
	public void execute() {
		Player player = event.getPlayer();
		player.addResource(resource);
	}
	
	public void discard() {
		Player player = event.getPlayer();
		player.removeResource(resource);
	}

}
