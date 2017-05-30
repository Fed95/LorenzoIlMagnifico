package it.polimi.ingsw.gc_12.excommunication;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import it.polimi.ingsw.gc_12.Match;

public class ExcommunicationSpace {
	private int excommunicationTileNum;
	private List<ExcommunicationTile> excommunicationTilesGeneral;
	private List<ExcommunicationTile> finalExcommunicationTiles;

	
	public ExcommunicationSpace(int excommunicationTileNum, List<ExcommunicationTile> excommunicationTiles){
		this.excommunicationTileNum = excommunicationTileNum;
		this.excommunicationTilesGeneral = excommunicationTiles;
		chooseTiles();
	}
	private void chooseTiles() {
		Match match = Match.instance();
		int periodNum=match.getTotalPeriodNumber();
		List<ExcommunicationTile> roundTile;
		for(int i = 0 ; i < periodNum; i++ ){
			int period = i + 1;
			roundTile = excommunicationTilesGeneral.stream().filter(Tile->Tile.getPeriod()== period).collect(Collectors.toList());
			Collections.shuffle(roundTile);
			finalExcommunicationTiles.add(roundTile.get(0));
		}
	}
}
