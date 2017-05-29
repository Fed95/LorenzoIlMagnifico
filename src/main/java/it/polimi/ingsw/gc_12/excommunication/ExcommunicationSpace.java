package it.polimi.ingsw.gc_12.excommunication;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
		Collections.shuffle(excommunicationTilesGeneral);
		//finalExcommunicationTiles = excommunicationTilesGeneral.subList(0, 3);
	}
}
