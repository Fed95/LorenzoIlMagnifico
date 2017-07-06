package it.polimi.ingsw.gc12.model.board.excommunication;

import it.polimi.ingsw.gc12.model.match.Match;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

public class ExcommunicationSpace implements Serializable{

	private List<ExcommunicationTile> tilesDeck = new ArrayList<>();
	private Map<Integer, ExcommunicationTile> tiles = new HashMap<>();

	public ExcommunicationSpace(){
	}

	public void setTilesDeck(List<ExcommunicationTile> tilesDeck) {
		this.tilesDeck = tilesDeck;
		chooseTiles();
	}

	private void chooseTiles() {

		int periodNum = Match.DEFAULT_TOTAL_PERIODS_NUM;
		List<ExcommunicationTile> roundTiles;

		for (int i = 0; i < periodNum; i++) {
			int period = i + 1;
			roundTiles = tilesDeck.stream().filter(tile -> tile.getPeriod() == period).collect(Collectors.toList());
			Collections.shuffle(roundTiles);
			tiles.put(period, roundTiles.get(0));
		}
	}

	public Map<Integer, ExcommunicationTile> getTiles(){
		return tiles;
	}
}
