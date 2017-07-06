package it.polimi.ingsw.gc12.misc.json.loader;

import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.card.CardType;
import it.polimi.ingsw.gc12.model.player.personalboard.CardsSpace;

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

	@Override
	protected Map<CardType, CardsSpace> adapt(Map<CardType, CardsSpace> content, Match match) {
		return content;
	}
}