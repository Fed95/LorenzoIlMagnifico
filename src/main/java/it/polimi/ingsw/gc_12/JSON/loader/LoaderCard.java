package it.polimi.ingsw.gc_12.JSON.loader;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.gc_12.TowerSet;
import it.polimi.ingsw.gc_12.card.Card;

public class LoaderCard extends Loader<List<Card>> {

	public LoaderCard(){
		super("cards");
	}

	@Override
	protected Type getType() {
		return new TypeToken<List<Card>>() {}.getType();
	}
}
