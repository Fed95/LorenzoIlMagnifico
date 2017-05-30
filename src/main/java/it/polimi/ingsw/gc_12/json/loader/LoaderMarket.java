package it.polimi.ingsw.gc_12.json.loader;

import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.gc_12.occupiables.Market;

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
