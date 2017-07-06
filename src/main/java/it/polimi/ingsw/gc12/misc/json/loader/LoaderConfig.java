package it.polimi.ingsw.gc12.misc.json.loader;

import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.gc12.model.match.Config;
import it.polimi.ingsw.gc12.model.match.Match;

import java.lang.reflect.Type;


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
