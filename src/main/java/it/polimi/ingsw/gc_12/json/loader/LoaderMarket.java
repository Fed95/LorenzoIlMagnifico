package it.polimi.ingsw.gc_12.json.loader;

import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.gc_12.ConfigPlayers;
import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.occupiables.Market;
import it.polimi.ingsw.gc_12.occupiables.SpaceMarket;

import java.lang.reflect.Type;
import java.util.ArrayList;
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
		ConfigPlayers configPlayers = new LoaderConfigPlayers().get(match).get(match.getPlayers().size());
		int spaceMarketsNum = configPlayers.getSpaceMarketNum();
		spaceMarkets = new ArrayList<>(spaceMarkets.subList(0, spaceMarketsNum));
		content.setSpaceMarkets(spaceMarkets);
		return content;
	}
}
