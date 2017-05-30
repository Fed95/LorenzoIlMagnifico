package it.polimi.ingsw.gc_12.json.loader;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.gc_12.GameMode;
import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.card.Card;

public class LoaderCard extends Loader<List<Card>> {

	public LoaderCard(){
		super("cards");
	}

	@Override
	protected Type getType() {
		return new TypeToken<List<Card>>() {}.getType();
	}

	@Override
	protected List<Card> adapt(List<Card> content, Match match) {
		return content;
	}
}
