package it.polimi.ingsw.gc_12.json.loader;

import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.personal_board.BonusTile;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class LoaderBonusTile extends Loader<List<BonusTile>> {

	public LoaderBonusTile(){
		super("bonusTiles");
	}

	@Override
	protected Type getType() {
		return new TypeToken<List<BonusTile>>() {}.getType();
	}

	@Override
	protected List<BonusTile> adapt(List<BonusTile> content, Match match) {
		List<BonusTile> newContent = new ArrayList<>();

		int playersNum = match.getPlayers().size();

		for (int i = 0; i < playersNum; i++) {
			newContent.add(content.get(i));
		}

		return newContent;
	}
}
