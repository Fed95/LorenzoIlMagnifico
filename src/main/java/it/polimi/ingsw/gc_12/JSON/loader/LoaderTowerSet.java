package it.polimi.ingsw.gc_12.JSON.loader;

import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.gc_12.occupiables.TowerSet;

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
}
