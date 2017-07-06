package it.polimi.ingsw.gc12.misc.json.loader;

import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.gc12.model.match.ConfigPlayers;
import it.polimi.ingsw.gc12.model.match.Match;

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
