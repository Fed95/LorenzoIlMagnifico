package it.polimi.ingsw.gc_12;


import it.polimi.ingsw.gc_12.card.Card;
import it.polimi.ingsw.gc_12.card.CardDevelopment;
import it.polimi.ingsw.gc_12.card.CardType;
import it.polimi.ingsw.gc_12.effect.EffectFreeAction;
import it.polimi.ingsw.gc_12.mvc.ControllerMatch;
import it.polimi.ingsw.gc_12.personal_board.PersonalBoard;
import it.polimi.ingsw.gc_12.resource.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestMatch {



	public static void main(String[] args){
		List<Player> players = new ArrayList<>();
		Map<ResourceType, Resource> resources = new HashMap<>();
		Resource stones = new Stone(3);
		Resource wood = new Wood(2);
		Resource money = new Money(1);
		Resource servants = new Servant(3);
		Resource faith = new FaithPoint(1);
		resources.put(ResourceType.STONE, stones);
		resources.put(ResourceType.WOOD, wood);
		resources.put(ResourceType.MONEY, money);
		resources.put(ResourceType.SERVANT, servants);
		resources.put(ResourceType.FAITH_POINT, faith);

		PersonalBoard personalBoard1 = new PersonalBoard();
		Player player1 = new Player("Jack", personalBoard1, resources);
		players.add(player1);

		PersonalBoard personalBoard2 = new PersonalBoard();
		Player player2 = new Player("Jill", personalBoard1, resources);
		players.add(player2);

		ControllerMatch controllerMatch = new ControllerMatch(players);
		controllerMatch.start();
	}

}
