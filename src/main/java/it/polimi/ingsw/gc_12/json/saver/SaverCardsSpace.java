package it.polimi.ingsw.gc_12.json.saver;

import it.polimi.ingsw.gc_12.card.CardType;
import it.polimi.ingsw.gc_12.effect.Effect;
import it.polimi.ingsw.gc_12.effect.EffectChangeResource;
import it.polimi.ingsw.gc_12.GameMode;
import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.personal_board.CardSlot;
import it.polimi.ingsw.gc_12.personal_board.CardsSpace;
import it.polimi.ingsw.gc_12.resource.MilitaryPoint;
import it.polimi.ingsw.gc_12.resource.Resource;
import it.polimi.ingsw.gc_12.resource.ResourceExchange;
import it.polimi.ingsw.gc_12.resource.VictoryPoint;

import java.util.*;

/**
 * Created by marco on 30/05/2017.
 */
public class SaverCardsSpace extends Saver<Map<CardType, CardsSpace>> {

	public SaverCardsSpace() {
		super("cardsSpace");
	}

	@Override
	public void create() {
		Map<CardType, CardsSpace> cardsSpaces = new HashMap<>();
		CardsSpace cardsSpace;
		CardSlot cardSlot;
		List<Resource> requisites;

		// Territories
		cardsSpace = new CardsSpace(CardType.TERRITORY);
		cardsSpace.setSpacesAvailable(6);

		cardSlot = new CardSlot(null, null);
		cardsSpace.addCardSlot(cardSlot);

		cardSlot = new CardSlot(null, null);
		cardsSpace.addCardSlot(cardSlot);

		requisites = new ArrayList<>(Collections.singletonList(new MilitaryPoint(3)));
		cardSlot = new CardSlot(requisites, null);
		cardsSpace.addCardSlot(cardSlot);

		requisites = new ArrayList<>(Collections.singletonList(new MilitaryPoint(7)));
		cardSlot = new CardSlot(requisites, null);
		cardsSpace.addCardSlot(cardSlot);

		requisites = new ArrayList<>(Collections.singletonList(new MilitaryPoint(12)));
		cardSlot = new CardSlot(requisites, null);
		cardsSpace.addCardSlot(cardSlot);

		requisites = new ArrayList<>(Collections.singletonList(new MilitaryPoint(18)));
		cardSlot = new CardSlot(requisites, null);
		cardsSpace.addCardSlot(cardSlot);

		cardsSpaces.put(CardType.TERRITORY, cardsSpace);

		cardsSpace = createEmptySlots(new CardsSpace(CardType.BUILDING));
		cardsSpace.setSpacesAvailable(6);
		cardsSpaces.put(CardType.BUILDING, cardsSpace);

		cardsSpace = createEmptySlots(new CardsSpace(CardType.CHARACTER));
		cardsSpace.setSpacesAvailable(6);
		cardsSpaces.put(CardType.CHARACTER, cardsSpace);

		cardsSpace = createEmptySlots(new CardsSpace(CardType.VENTURE));
		cardsSpace.setSpacesAvailable(6);
		cardsSpaces.put(CardType.VENTURE, cardsSpace);

		super.save(cardsSpaces, null);
	}

	private CardsSpace createEmptySlots(CardsSpace cardsSpace) {
		CardSlot cardSlot;
		for (int i = 0; i < 6; i++) {
			cardSlot = new CardSlot(null, null);
			cardsSpace.addCardSlot(cardSlot);
		}
		return cardsSpace;
	}
}
