package it.polimi.ingsw.gc_12.effect;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.event.Event;
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
	
	public void execute(Event event) throws RuntimeException {
		Player player = event.getPlayer();
		//System.out.println("effectchangeresource: executing change resource effect on player " + player.getName());
		if(!choice) {
			//System.out.println("effectchangeresource: 'non-choice' effect detected");
			for(ResourceExchange exchange : exchanges) {
				player.removeResources(exchange.getCost());
				//System.out.println("effectchangeresource: removed " + exchange.getCost());
				player.addResources(exchange.getBonus());
				//System.out.println("effectchangeresource: added " + exchange.getBonus());
			}
		}
		else {
			/*ResourceExchange exchange = player.getMatch().getControllerPlayer().chooseResourceExchange(exchanges);
			exchangeChosen = exchange;
			player.removeResources(exchange.getCost());
			player.addResources(exchange.getBonus());*/
		}

	}
	
	public void discard(Event event) throws RuntimeException {
		Player player = event.getPlayer();

		if(exchangeChosen == null) {
			for(ResourceExchange exchange : exchanges) {
				player.removeResources(exchange.getCost());
				player.addResources(exchange.getBonus());
			}
		}
		else {
			player.removeResources(exchangeChosen.getCost());
			player.addResources(exchangeChosen.getBonus());
		}
	}
}
