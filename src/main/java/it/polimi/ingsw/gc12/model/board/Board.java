package it.polimi.ingsw.gc12.model.board;

import it.polimi.ingsw.gc12.misc.json.loader.LoaderConfigPlayers;
import it.polimi.ingsw.gc12.model.board.dice.SpaceDie;
import it.polimi.ingsw.gc12.model.board.excommunication.ExcommunicationSpace;
import it.polimi.ingsw.gc12.model.board.excommunication.ExcommunicationTile;
import it.polimi.ingsw.gc12.model.board.occupiable.*;
import it.polimi.ingsw.gc12.model.match.ConfigPlayers;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.model.board.occupiable.WorkType;
import it.polimi.ingsw.gc12.model.board.occupiable.Zone;
import it.polimi.ingsw.gc12.model.board.track.TrackFaithPoints;
import it.polimi.ingsw.gc12.model.board.track.TrackMilitaryPoints;
import it.polimi.ingsw.gc12.model.board.track.TrackTurnOrder;
import it.polimi.ingsw.gc12.model.board.track.TrackVictoryPoints;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class represent the board of the game as some common object to the players
 */
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
	private ExcommunicationSpace excommunicationSpace;
	private transient List<ExcommunicationTile> excommunicationTiles;

    /**
     * Constructor creates all the pieces of the board
     * @param players players of the match
     */
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

    /**
     * Create the space work loading from json the number of players for unlock the workspaces multiple
     * @param playersNum
     */
	public void createSpaceWork(int playersNum) {
		ConfigPlayers configPlayers = new LoaderConfigPlayers().get(null).get(playersNum);
		for(WorkType workType : WorkType.values()){
			SpaceWorkZone spaceWorkZone = new SpaceWorkZone();
			spaceWorkZone.addSpaceWork(new SpaceWorkSingle(workType));
			if(configPlayers.isSpaceWorkMultiple())
				spaceWorkZone.addSpaceWork(new SpaceWorkMultiple(workType));
			spaceWorkZones.put(workType, spaceWorkZone);
		}
	}

    /**
     * Sets the players' order for the ner rond and prepare the board
     * @param round
     * @param period
     */
	public void refresh(int round, int period){
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
