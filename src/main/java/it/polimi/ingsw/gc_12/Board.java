package it.polimi.ingsw.gc_12;

import it.polimi.ingsw.gc_12.JSON.LoaderTowerSet;
import it.polimi.ingsw.gc_12.track.TrackTurnOrder;

import java.util.ArrayList;
import java.util.List;

public class Board {

	private SpaceDie spaceDie;
	private TowerSet towerSet;
	private Market market;
	private List<Occupiable> spaceWorks = new ArrayList<>();
	private TrackTurnOrder trackTurnOrder;
	
	/*
	private CouncilPalace councilPalace;
	private ExcommunicationSpace excommunicationSpace;
	private FaithPointsTrack faithPointTrack;
	*/
	
	public Board() {
		this.spaceDie = SpaceDie.instance();
		this.towerSet = new LoaderTowerSet("towerSet").get();
		this.market = new Market();
		this.trackTurnOrder = new TrackTurnOrder(Match.instance().getPlayers());
		
		
		for(WorkType workType : WorkType.values()){
			//TODO: set import of requiredValues and effects from Json file
			SpaceWorkZone spaceWorkZone = new SpaceWorkZone();
			SpaceWork spaceWorkSingle = new SpaceWorkSingle(workType, spaceWorkZone);
			SpaceWork spaceWorkMultiple = new SpaceWorkMultiple(workType, spaceWorkZone);
			spaceWorks.add(spaceWorkSingle);
			spaceWorks.add(spaceWorkMultiple);
		}
		
		
	}
	
	public List<Occupiable> getSpaceWorks(){
		return spaceWorks;
	}
	
	public SpaceDie getSpaceDie() {
		return spaceDie;
	}
	
	public TowerSet getTowerSet() {
		return towerSet;
	}
	
	public Market getMarket() {
		return market;
	}

	public TrackTurnOrder getTrackTurnOrder() {
		return trackTurnOrder;
	}

	public List<Occupiable> getOccupiables() {
		List<Occupiable> occupiables = new ArrayList<>();
		occupiables.addAll(towerSet.getOccupiables());
		occupiables.addAll(spaceWorks);
		return occupiables;
	}
	
	/*
	public CouncilPalace getCouncilPalace() {
		return councilPalace;
	}
	public ExcommunicationSpace getExcommunicationSpace() {
		return excommunicationSpace;
	}
	public FaithPointsTrack getFaithPointTrack() {
		return faithPointTrack;
	}*/

	@Override
	public String toString() {
		return "Board [towerSet=" + towerSet + "]";
	}
}
