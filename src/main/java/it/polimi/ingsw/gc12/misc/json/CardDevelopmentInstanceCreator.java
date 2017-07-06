package it.polimi.ingsw.gc12.misc.json;

import com.google.gson.InstanceCreator;
import it.polimi.ingsw.gc12.model.card.CardDevelopment;
import it.polimi.ingsw.gc12.model.card.CardType;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class CardDevelopmentInstanceCreator implements InstanceCreator<CardDevelopment>, Serializable {
	public CardDevelopment createInstance(Type type) {
		return new CardDevelopment(0, CardType.TERRITORY,"generic", 0, new ArrayList<>(), new ArrayList<>()) {
		};
	}
}
