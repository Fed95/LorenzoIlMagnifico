package it.polimi.ingsw.gc_12.excommunication;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ExcommunicationSpace {
	private int excommunicationTileNum;
	private List<ExcommunicationTile> excommunicationTilesGeneral;
	private List<ExcommunicationTile> finalExcommunicationTiles;

	
	public ExcommunicationSpace(int excommunicationTileNum){
		this.excommunicationTileNum = excommunicationTileNum;
		chooseTiles();
	}
	private void chooseTiles() {
		excommunicationTilesGeneral=new ArrayList<>();//import from json here
		List<ExcommunicationTile> firstRoundTile = excommunicationTilesGeneral.stream().filter(Tile->Tile.getPeriod()==1).collect(Collectors.toList());
		List<ExcommunicationTile> secondRoundTile = excommunicationTilesGeneral.stream().filter(Tile->Tile.getPeriod()==2).collect(Collectors.toList());
		List<ExcommunicationTile> thirdRoundTile =excommunicationTilesGeneral.stream().filter(Tile->Tile.getPeriod()==3).collect(Collectors.toList());
		Collections.shuffle(firstRoundTile);
		Collections.shuffle(secondRoundTile);
		Collections.shuffle(thirdRoundTile);
		finalExcommunicationTiles.add(firstRoundTile.get(0));
		finalExcommunicationTiles.add(secondRoundTile.get(0));
		finalExcommunicationTiles.add(thirdRoundTile.get(0));

	}
}
