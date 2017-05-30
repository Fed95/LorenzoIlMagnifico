package it.polimi.ingsw.gc_12;

import it.polimi.ingsw.gc_12.json.loader.LoaderMarket;
import it.polimi.ingsw.gc_12.json.loader.LoaderTowerSet;
import it.polimi.ingsw.gc_12.excommunication.ExcommunicationSpace;
import it.polimi.ingsw.gc_12.excommunication.ExcommunicationTile;
import it.polimi.ingsw.gc_12.occupiables.*;
import it.polimi.ingsw.gc_12.track.TrackFaithPoints;
import it.polimi.ingsw.gc_12.track.TrackMilitaryPoints;
import it.polimi.ingsw.gc_12.track.TrackTurnOrder;
import it.polimi.ingsw.gc_12.track.TrackVictoryPoints;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {

	private SpaceDie spaceDie;
	private TowerSet towerSet;
	private Market market;
	private CouncilPalace councilPalace;
	private Map<WorkType, SpaceWorkZone> spaceWorks = new HashMap<>();
	private TrackTurnOrder trackTurnOrder;
	private TrackMilitaryPoints trackMilitaryPoints;
	private TrackVictoryPoints victroyPointsTrack;
	private TrackFaithPoints trackFaithPoints;
	private List<EffectProvider> faithSlots = new ArrayList<>();
	private ExcommunicationSpace excommunicationSpace;
	private List<ExcommunicationTile> excommunicationTiles = new ArrayList<>();// import from json or match

	public Board() {
		this.spaceDie = SpaceDie.instance();
		this.towerSet = new LoaderTowerSet().get(Match.instance());
		this.market = new LoaderMarket().get(Match.instance());
		this.councilPalace = new CouncilPalace(1, null); //TODO: import values and effects from Json
		this.trackTurnOrder = new TrackTurnOrder(councilPalace);
		this.trackMilitaryPoints = new TrackMilitaryPoints();
		this.victroyPointsTrack = new TrackVictoryPoints();
		this.trackFaithPoints = new TrackFaithPoints();
		//this.excommunicationSpace=new ExcommunicationSpace(DEFAULT_NUMBER_OF_EXCOMMUNICATION_TILE);//TODO:import from json file config if needed
		createSpaceWork();
	}

	public void createSpaceWork() {
		for(WorkType workType : WorkType.values()){
			SpaceWorkZone spaceWorkZone = new SpaceWorkZone();
			spaceWorkZone.addSpaceWork(new SpaceWorkSingle(workType));
			spaceWorkZone.addSpaceWork(new SpaceWorkMultiple(workType));
			spaceWorks.put(workType, spaceWorkZone);
		}
	}

	public void refresh(){
		//towerSet.refresh();
		trackTurnOrder.newRound();
	}

	public Map<WorkType, SpaceWorkZone> getSpaceWorks(){
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
		occupiables.addAll(market.getSpaceMarkets());
		for(WorkType workType: WorkType.values())
			occupiables.addAll(spaceWorks.get(workType).getSpaceWorks());
		return occupiables;
	}

	public TrackFaithPoints getTrackFaithPoints() {
		return trackFaithPoints;
	}

	public List<EffectProvider> getFaithSlots() {
		return faithSlots;
	}

	public ExcommunicationSpace getExcommunicationSpace() {
		return excommunicationSpace;
	}

	/*
	public ExcommunicationSpace getExcommunicationSpace() {
		return excommunicationSpace;
	}

	}*/

	@Override
	public String toString() {
		return "Board [towerSet=" + towerSet + "]";
	}
}
