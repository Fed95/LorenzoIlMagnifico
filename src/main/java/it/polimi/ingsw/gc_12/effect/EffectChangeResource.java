package it.polimi.ingsw.gc_12.effect;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.event.Event;
import it.polimi.ingsw.gc_12.event.EventChooseExchange;
import it.polimi.ingsw.gc_12.resource.ResourceExchange;

public class EffectChangeResource extends Effect {

	private List<ResourceExchange> exchanges;
	private boolean choice;
	private ResourceExchange exchangeChosen;
	
	public EffectChangeResource(Event event, List<ResourceExchange> exchanges, boolean choice) {
		super(event);
		this.exchanges = exchanges;
		this.choice = choice;
	}
	
	public EffectChangeResource(Event event, ResourceExchange exchange, boolean choice) {
		this(event, new ArrayList<>(Arrays.asList(exchange)), choice);
	}
	
	public void execute(Match match, Event event) throws RuntimeException {
		Player player = event.getPlayer();
		if(!choice) {
			for(ResourceExchange exchange : exchanges) {
				player.removeResources(exchange.getCost());
				player.addResources(exchange.getBonus());
			}
		}
		else {
			List<ResourceExchange> possibleExchanges = new ArrayList<>();
			for (ResourceExchange exchange : exchanges) {
				if (player.hasResources(exchange.getCost()))
					possibleExchanges.add(exchange);
			}
			EventChooseExchange eventExchange = new EventChooseExchange(player, possibleExchanges);
			match.getActionHandler().update(eventExchange);
			match.notifyObserver(eventExchange);
		}

	}
	
	public void discard(Event event) throws RuntimeException {
		Player player = event.getPlayer();

		if(exchangeChosen == null) {
			for(ResourceExchange exchange : exchanges) {
				player.addResources(exchange.getCost());
				player.removeResources(exchange.getBonus());
			}
		}
		else {
			player.addResources(exchangeChosen.getCost());
			player.removeResources(exchangeChosen.getCost());
		}
	}

	@Override
	public String toString() {
		return event.getClass().getSimpleName() + ": " + this.getClass().getSimpleName() + ": " + String.valueOf(exchanges);
	}
}
