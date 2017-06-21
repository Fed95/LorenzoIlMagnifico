package it.polimi.ingsw.gc_12.json.loader;

import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.gc_12.Config;
import it.polimi.ingsw.gc_12.Match;

import java.lang.reflect.Type;
import java.util.Map;

public class LoaderConfig extends Loader<Map<Integer, Config>> {

	public LoaderConfig() {
		super("config");
	}

	@Override
	protected Type getType() {
		return new TypeToken<Map<Integer, Config>>() {}.getType();
	}

	@Override
	protected Map<Integer, Config> adapt(Map<Integer, Config> content, Match match) {
		return content;
	}
}
