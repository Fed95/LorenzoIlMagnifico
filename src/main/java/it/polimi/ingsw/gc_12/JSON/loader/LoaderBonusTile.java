package it.polimi.ingsw.gc_12.JSON.loader;

import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.gc_12.BonusTile;

import java.lang.reflect.Type;
import java.util.List;


public class LoaderBonusTile extends Loader<List<BonusTile>> {

	public LoaderBonusTile(){
		super("bonusTiles");
	}

	@Override
	protected Type getType() {
		return new TypeToken<List<BonusTile>>() {}.getType();
	}
}
