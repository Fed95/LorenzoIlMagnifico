package it.polimi.ingsw.gc_12.JSON.loader;

import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.gc_12.occupiables.Market;
import it.polimi.ingsw.gc_12.occupiables.TowerSet;

import java.lang.reflect.Type;

public class LoaderMarket extends Loader<Market> {

	public LoaderMarket (){
		super("market");
	}

	@Override
	protected Type getType() {
		return new TypeToken<Market>() {}.getType();
	}
}
