package it.polimi.ingsw.gc_12;

import it.polimi.ingsw.gc_12.dice.SpaceDie;
import it.polimi.ingsw.gc_12.excommunication.ExcommunicationSpace;
import it.polimi.ingsw.gc_12.excommunication.ExcommunicationTile;
import it.polimi.ingsw.gc_12.occupiables.*;
import it.polimi.ingsw.gc_12.track.TrackFaithPoints;
import it.polimi.ingsw.gc_12.track.TrackMilitaryPoints;
import it.polimi.ingsw.gc_12.track.TrackTurnOrder;
import it.polimi.ingsw.gc_12.track.TrackVictoryPoints;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board implements Serializable{

	private transient SpaceDie spaceDie;
	private TowerSet towerSet;
	private Market market;
	private CouncilPalace councilPalace;
	private Map<WorkType, SpaceWorkZone> spaceWorkZones = new HashMap<>();
	private TrackTurnOrder trackTurnOrder;
	private transient TrackMilitaryPoints trackMilitaryPoints;
	private transient TrackVictoryPoints victroyPointsTrack;
	private transient TrackFaithPoints trackFaithPoints;
	private transient ExcommunicationSpace excommunicationSpace;
	private transient List<ExcommunicationTile> excommunicationTiles;
	private transient List<Integer> trackFaithPointsValues = new ArrayList<>();

	public Board(List<Player> players) {
		this.spaceDie = SpaceDie.instance();
		this.councilPalace = new CouncilPalace(1, null);
		this.trackTurnOrder = new TrackTurnOrder(players, councilPalace);
		this.trackMilitaryPoints = new TrackMilitaryPoints();
		this.victroyPointsTrack = new TrackVictoryPoints();
		this.trackFaithPoints = new TrackFaithPoints(trackFaithPointsValues);
		this.excommunicationSpace = new ExcommunicationSpace();
		createSpaceWork();
	}
	public void setTowerSet(TowerSet towerSet) {
		this.towerSet = towerSet;
	}
	public void setMarket(Market market) {
		this.market = market;
	}

	public void createSpaceWork() {
		for(WorkType workType : WorkType.values()){
			SpaceWorkZone spaceWorkZone = new SpaceWorkZone();
			spaceWorkZone.addSpaceWork(new SpaceWorkSingle(workType));
			spaceWorkZone.addSpaceWork(new SpaceWorkMultiple(workType));
			spaceWorkZones.put(workType, spaceWorkZone);
		}
	}

	public void refresh(int round, int period){
		//Sets the players' order for the new round and prepares the board
		trackTurnOrder.newRound();
		spaceDie.rollDice();
		for(Occupiable occupiable : getOccupiables()){
			for (FamilyMember familyMember : occupiable.getOccupiers())
				familyMember.setBusy(false);
			occupiable.free();
		}
		towerSet.refresh(period);
	}

	public Map<WorkType, SpaceWorkZone> getSpaceWorkZones(){
		return spaceWorkZones;
	}

	public List<SpaceWork> getSpaceWorks(){
		List<SpaceWork> spaceWorks = new ArrayList<>();
		for (SpaceWorkZone spaceWorkZone : spaceWorkZones.values()){
			for(SpaceWork spaceWork : spaceWorkZone.getSpaceWorks())
				spaceWorks.add(spaceWork);
		}
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
		occupiables.add(councilPalace);
		for(WorkType workType: WorkType.values())
			occupiables.addAll(spaceWorkZones.get(workType).getSpaceWorks());
		return occupiables;
	}

	public List<Zone> getZones() {
		List<Zone> zones = new ArrayList<>();
		zones.addAll(towerSet.getTowers().values());
		zones.addAll(spaceWorkZones.values());
		zones.add(councilPalace);
		zones.add(market);
		return zones;
	}

	public TrackFaithPoints getTrackFaithPoints() {
		return trackFaithPoints;
	}

	public ExcommunicationSpace getExcommunicationSpace() {
		return excommunicationSpace;
	}

	@Override
	public String toString() {
		return "Board [towerSet=" + towerSet + "]";
	}
}
