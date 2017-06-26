package it.polimi.ingsw.gc_12.json.loader;

import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.gc_12.ConfigPlayers;
import it.polimi.ingsw.gc_12.Match;

import java.lang.reflect.Type;
import java.util.Map;

public class LoaderConfigPlayers extends Loader<Map<Integer, ConfigPlayers>> {

	public LoaderConfigPlayers() {
		super("configPlayers");
	}

	@Override
	protected Type getType() {
		return new TypeToken<Map<Integer, ConfigPlayers>>() {}.getType();
	}

	@Override
	protected Map<Integer, ConfigPlayers> adapt(Map<Integer, ConfigPlayers> content, Match match) {
		return content;
	}
}
