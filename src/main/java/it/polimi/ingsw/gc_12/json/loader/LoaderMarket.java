package it.polimi.ingsw.gc_12.json.loader;

import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.gc_12.Config;
import it.polimi.ingsw.gc_12.GameMode;
import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.occupiables.Market;
import it.polimi.ingsw.gc_12.occupiables.SpaceMarket;

import java.lang.reflect.Type;
import java.util.List;

public class LoaderMarket extends Loader<Market> {

	public LoaderMarket (){
		super("market");
	}

	@Override
	protected Type getType() {
		return new TypeToken<Market>() {}.getType();
	}

	@Override
	protected Market adapt(Market content, Match match) {
		List<SpaceMarket> spaceMarkets = content.getSpaceMarkets();
		Config config = new LoaderConfig().get(match).get(match.getPlayers());
		int spaceMarketsNum = config.getSpaceMarketNum();
		spaceMarkets = spaceMarkets.subList(0, spaceMarketsNum-1);
		content.setSpaceMarkets(spaceMarkets);
		return content;
	}
}
