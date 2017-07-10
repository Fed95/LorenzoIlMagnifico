package it.polimi.ingsw.gc12.model.effect;

import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.model.event.Event;
import it.polimi.ingsw.gc12.model.event.EventChooseExchange;
import it.polimi.ingsw.gc12.misc.exception.ActionDeniedException;
import it.polimi.ingsw.gc12.model.player.resource.Resource;
import it.polimi.ingsw.gc12.model.player.resource.ResourceExchange;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * During validation this effect checks wheter the player has the speciied resources without actualli applying any change
 * During execution instead the bonus of the exchange will be added to the players' resources and the malus will be subtracted.
 * Exchanges can have a choice; in this case an EventChooseExchange is generated
 * and the player will be presented with a set of actions relative to the possible choices
 */
public class EffectChangeResource extends Effect {

	private List<ResourceExchange> exchanges = new ArrayList<>();
	private boolean choice;

	public EffectChangeResource(Event event, List<ResourceExchange> exchanges, boolean choice) {
		super(event);
		if(exchanges != null)
			this.exchanges = exchanges;
		this.choice = choice;
	}
	
	public EffectChangeResource(Event event, ResourceExchange exchange, boolean choice) {
		this(event, new ArrayList<>(Arrays.asList(exchange)), choice);
	}
	
	public void execute(Match match, Event event, boolean validation) throws ActionDeniedException {
		Player player = event.getPlayer();
		if(!validation) {
			if(!choice) {
				for(ResourceExchange exchange : exchanges) {
					player.removeResources(exchange.getCost());

					List<Resource> newBonus = applyResourceBonus(exchange, match, player);
					match.addResources(player, newBonus);
				}
			}
			else {
				List<ResourceExchange> possibleExchanges = new ArrayList<>();
				for (ResourceExchange exchange : exchanges) {
					if (player.hasResources(exchange.getCost()))
						possibleExchanges.add(exchange);
				}
				EventChooseExchange eventExchange = new EventChooseExchange(player, possibleExchanges);
				match.getActionHandler().update(eventExchange, match);
				match.notifyObserver(eventExchange);
			}
		}
		else {
			List<ResourceExchange> possibleExchanges = new ArrayList<>();
			for (ResourceExchange exchange : exchanges) {
				if (player.hasResources(exchange.getCost()))
					possibleExchanges.add(exchange);
			}
			if(possibleExchanges.size() <= 0)
				throw new ActionDeniedException("You don't have enough resources to perform an exchange.");
		}
	}

	public void discard(Match match, Event event) {
	}

	public List<ResourceExchange> getExchanges() {
		return exchanges;
	}

	public boolean hasChoice(){
		return choice;
	}

	@Override
	public String toString() {
		return event.getClass().getSimpleName() + ": " + this.getClass().getSimpleName() + ": " + String.valueOf(exchanges);
	}
}
