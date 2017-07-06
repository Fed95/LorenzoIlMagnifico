package it.polimi.ingsw.gc12.misc.json.loader;

import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.board.occupiable.TowerSet;

import java.lang.reflect.Type;

// TODO: remove it on production
public class LoaderTowerSet extends Loader<TowerSet> {

	public LoaderTowerSet(){
		super("towers");
	}

	@Override
	protected Type getType() {
		return new TypeToken<TowerSet>() {}.getType();
	}

	@Override
	protected TowerSet adapt(TowerSet content, Match match) {
		return content;
	}
}
