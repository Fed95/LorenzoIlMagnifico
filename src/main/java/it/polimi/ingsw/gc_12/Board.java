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

	private SpaceDie spaceDie;
	private TowerSet towerSet;
	private Market market;
	private CouncilPalace councilPalace;
	private Map<WorkType, SpaceWorkZone> spaceWorkZones = new HashMap<>();
	private TrackTurnOrder trackTurnOrder;
	private TrackMilitaryPoints trackMilitaryPoints;
	private TrackVictoryPoints victroyPointsTrack;
	private List<Integer> trackFaithPointsValues = new ArrayList<>();
	private transient TrackFaithPoints trackFaithPoints;
	private transient ExcommunicationSpace excommunicationSpace;
	private transient List<ExcommunicationTile> excommunicationTiles;

	public Board(List<Player> players) {
		this.spaceDie = new SpaceDie();
		this.councilPalace = new CouncilPalace(1);
		this.trackTurnOrder = new TrackTurnOrder(players, councilPalace);
		this.trackMilitaryPoints = new TrackMilitaryPoints();
		this.victroyPointsTrack = new TrackVictoryPoints();
		this.excommunicationSpace = new ExcommunicationSpace();
		createSpaceWork(players.size());
	}
	public void setTowerSet(TowerSet towerSet) {
		this.towerSet = towerSet;
	}
	public void setMarket(Market market) {
		this.market = market;
	}
	public void setTrackFaithPoints(List<Integer> values){
		this.trackFaithPoints = new TrackFaithPoints(values);
	}

	public void createSpaceWork(int playersNum) {
		ConfigPlayers configPlayers = new ConfigPlayers(playersNum);
		for(WorkType workType : WorkType.values()){
			SpaceWorkZone spaceWorkZone = new SpaceWorkZone();
			spaceWorkZone.addSpaceWork(new SpaceWorkSingle(workType));
			if(configPlayers.isSpaceWorkMultiple())
				spaceWorkZone.addSpaceWork(new SpaceWorkMultiple(workType));
			spaceWorkZones.put(workType, spaceWorkZone);
		}
	}

	public void refresh(int round, int period){
		//Sets the players' order for the new round and prepares the board
		trackTurnOrder.newRound();
		spaceDie.rollDice();
		for(Occupiable occupiable : getOccupiables()){
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
			spaceWorks.addAll(spaceWorkZone.getSpaceWorks());
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

    public TrackMilitaryPoints getTrackMilitaryPoints() {
        return trackMilitaryPoints;
    }

    public TrackVictoryPoints getVictroyPointsTrack() {
        return victroyPointsTrack;
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
