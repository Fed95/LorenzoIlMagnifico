package it.polimi.ingsw.gc_12.json.loader;

import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.gc_12.Config;
import it.polimi.ingsw.gc_12.ConfigPlayers;
import it.polimi.ingsw.gc_12.Match;

import java.lang.reflect.Type;
import java.util.Map;


public class LoaderConfig extends Loader<Config> {

	public LoaderConfig() {
		super("config");
	}

	@Override
	protected Type getType() {
		return new TypeToken<Config>() {}.getType();
	}

	@Override
	protected Config adapt(Config content, Match match) {
		return content;
	}
}
