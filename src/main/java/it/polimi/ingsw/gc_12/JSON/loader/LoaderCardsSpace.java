package it.polimi.ingsw.gc_12.JSON.loader;

import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.gc_12.card.CardType;
import it.polimi.ingsw.gc_12.personalBoard.CardsSpace;

import java.lang.reflect.Type;
import java.util.Map;

public class LoaderCardsSpace extends Loader<Map<CardType, CardsSpace>> {

	public LoaderCardsSpace(){
		super("cardsSpace");
	}

	@Override
	protected Type getType() {
		return new TypeToken<Map<CardType, CardsSpace>>() {}.getType();
	}
}