package it.polimi.ingsw.gc_12.json;

import com.google.gson.InstanceCreator;
import it.polimi.ingsw.gc_12.card.CardDevelopment;
import it.polimi.ingsw.gc_12.card.CardTerritory;
import it.polimi.ingsw.gc_12.card.CardType;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class CardDevelopmentInstanceCreator implements InstanceCreator<CardDevelopment> {
	public CardDevelopment createInstance(Type type) {
		return new CardDevelopment(0, CardType.TERRITORY,"generic", 0, new ArrayList<>(), new ArrayList<>()) {
		};
	}
}
