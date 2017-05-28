package it.polimi.ingsw.gc_12;

import it.polimi.ingsw.gc_12.JSON.loader.LoaderMarket;
import it.polimi.ingsw.gc_12.JSON.loader.LoaderTowerSet;
import it.polimi.ingsw.gc_12.occupiables.*;
import it.polimi.ingsw.gc_12.track.TrackTurnOrder;

import java.util.ArrayList;
import java.util.List;

public class Board {

	private SpaceDie spaceDie;
	private TowerSet towerSet;
	private Market market;
	private CouncilPalace councilPalace;
	private List<Occupiable> spaceWorks = new ArrayList<>();
	private TrackTurnOrder trackTurnOrder;

	/*
	private ExcommunicationSpace excommunicationSpace;
	private FaithPointsTrack faithPointTrack;
	*/

	public Board() {
		this.spaceDie = SpaceDie.instance();
		this.towerSet = new LoaderTowerSet().get();
		this.market = new LoaderMarket().get();
		this.councilPalace = new CouncilPalace(1, null); //TODO: import values and effects from Json
		this.trackTurnOrder = new TrackTurnOrder(Match.instance().getPlayers(), councilPalace);


		for(WorkType workType : WorkType.values()){
			//TODO: set import of requiredValues and effects from Json file
			SpaceWorkZone spaceWorkZone = new SpaceWorkZone();
			SpaceWork spaceWorkSingle = new SpaceWorkSingle(workType, spaceWorkZone);
			SpaceWork spaceWorkMultiple = new SpaceWorkMultiple(workType, spaceWorkZone);
			spaceWorks.add(spaceWorkSingle);
			spaceWorks.add(spaceWorkMultiple);
		}
	}

	public void refresh(){
		towerSet.refresh();
		trackTurnOrder.newRound();
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

	public CouncilPalace getCouncilPalace() {
		return councilPalace;
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
