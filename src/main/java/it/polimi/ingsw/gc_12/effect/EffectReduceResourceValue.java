package it.polimi.ingsw.gc_12.effect;

import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.event.Event;
import it.polimi.ingsw.gc_12.exceptions.ActionDeniedException;

/**
 * Created by marco on 21/06/2017.
 */
public class EffectReduceResourceValue extends Effect{

	private int value;

	public EffectReduceResourceValue(Event event, int value) {
		super(event);
		this.value = value;
	}

	@Override
	public void execute(Match match, Event event) throws ActionDeniedException {
		/*if(event instanceof EventServantsRequested) {

		}*/
	}

	@Override
	public void discard(Event event) {

	}
}
