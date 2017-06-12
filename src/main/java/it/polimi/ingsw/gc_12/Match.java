package it.polimi.ingsw.gc_12;

import it.polimi.ingsw.gc_12.action.ActionPlace;
import it.polimi.ingsw.gc_12.card.*;
import it.polimi.ingsw.gc_12.effect.EffectHandler;
import it.polimi.ingsw.gc_12.event.Event;
import it.polimi.ingsw.gc_12.event.EventChooseFamilyMember;
import it.polimi.ingsw.gc_12.event.EventPlaceFamilyMember;
import it.polimi.ingsw.gc_12.event.EventStartMatch;
import it.polimi.ingsw.gc_12.event.EventStartTurn;
import it.polimi.ingsw.gc_12.excommunication.ExcommunicationTile;
import it.polimi.ingsw.gc_12.json.loader.LoaderCard;
import it.polimi.ingsw.gc_12.json.loader.LoaderCardsSpace;
import it.polimi.ingsw.gc_12.json.loader.LoaderMarket;
import it.polimi.ingsw.gc_12.json.loader.LoaderTowerSet;
import it.polimi.ingsw.gc_12.mvc.ControllerPlayer;
import it.polimi.ingsw.gc_12.occupiables.*;
import it.polimi.ingsw.gc_12.resource.Resource;
import it.polimi.ingsw.gc_12.resource.Servant;
import it.polimi.ingsw.gc_12.server.controller.Change;
import it.polimi.ingsw.gc_12.server.controller.StateChange;
import it.polimi.ingsw.gc_12.server.model.State;
import it.polimi.ingsw.gc_12.server.observer.Observable;

import java.io.IOException;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class Match extends Observable<Change> implements MatchRemote, Serializable{
	private transient List<Player> players = new ArrayList<>();
	private transient final List<BonusTile> bonusTiles = new ArrayList<>();
	private transient List<Card> cards = new ArrayList<>(); //TODO IMPORT FROM JSON
	private transient List<ExcommunicationTile> excommunicationTiles = new ArrayList<>();
	private transient final GameMode gameMode;
	private transient CardDeckSet cardDeckSet;
	private Board board;
	private int roundNum;
	private transient EffectHandler effectHandler;
	private transient ControllerPlayer controllerPlayer;
	public transient final static GameMode DEFAULT_GAME_MODE = GameMode.NORMAL;
	public transient final static int DEFAULT_ROUND_NUM = 6;
	public transient final static int DEFAULT_PERIODS_LEN = 2;
	public transient final static int DEFAULT_TOTAL_PERIODS_NUM = DEFAULT_ROUND_NUM/DEFAULT_PERIODS_LEN;
	private State gameState;
	private boolean isFMPlaced;

	public Match(GameMode gameMode) {
		this.gameMode = gameMode;
		this.roundNum = 1;
		this.cards = new LoaderCard().get(this);
		//this.cardDeckSet = new CardDeckSet(cards, DEFAULT_ROUND_NUM/DEFAULT_PERIODS_LEN);
		this.effectHandler = new EffectHandler();
		this.gameState = State.PENDING;
	}

	public Match() {
		this(DEFAULT_GAME_MODE);
	}

	public void init(List<Player> players) {
		this.players = players;
		createBoard();
		//cardDeckSet.shuffle(); TODO: WAITING FOR JSON

		for (Player player : players) {
			player.init(effectHandler);
			player.getPersonalBoard().setCardsSpaces(new LoaderCardsSpace().get(this));
		}
	}

	private void createBoard() {
		System.out.println("Match: Creating the board");
		board = new Board(players);
		board.setTowerSet(new LoaderTowerSet().get(this));
		board.setMarket(new LoaderMarket().get(this));
		System.out.println(board.getMarket().getSpaceMarkets());

		//board.getTowerSet().setCards(cardDeckSet); TODO WAITING FOR JSON
	}

	public void start() throws IOException, CloneNotSupportedException {
		this.gameState = State.RUNNING;
		System.out.println("notify EventStartMatch");
		this.notifyObserver(new EventStartMatch(this));
		newTurn();
	}

	//Increments turn counter in TrackTurnOrder
	public void newTurn() throws IOException {
		System.out.println("Match: Starting new turn");
		for(Zone zone : board.getZones())
			System.out.println(zone);
		board.getTrackTurnOrder().newTurn();
		System.out.println("Match: notifying EventStartTurn to RMIView");
		this.notifyObserver(new EventStartTurn(board.getTrackTurnOrder().getCurrentPlayer()));
	}

	@Override
	public MatchInstance getInstance() throws CloneNotSupportedException {
		return new MatchInstance(this);
	}
	
	public void placeFamilyMember(Occupiable occupiable, FamilyMember familyMember) throws IOException {
		occupiable.placeFamilyMember(familyMember);
        familyMember.setBusy(true);
		EventPlaceFamilyMember event = new EventPlaceFamilyMember(board.getTrackTurnOrder().getCurrentPlayer(), occupiable, familyMember);
		this.notifyObserver(event);
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}


	public void setControllerPlayer(ControllerPlayer controllerPlayer) {
		this.controllerPlayer = controllerPlayer;
	}


	public List<Player> getPlayers() {
		return players;
	}

	public List<BonusTile> getBonusTyles() {
		return bonusTiles;
	}

	public List<Card> getCards(CardType cardType) {
		return cards;
	}

	public GameMode getGameMode() {
		return gameMode;
	}

	public Board getBoard() {
		return board;
	}

	public int getRoundNum() {
		return roundNum;
	}

	public int getPeriodNum() {
		return roundNum / 2 + roundNum % 2;
	}

	public ControllerPlayer getControllerPlayer() {
		return controllerPlayer;
	}

	@Override
	public String toString() {
		return board.toString();
	}

	//@Override
	public Player getCurrentPlayer() throws RemoteException {
		return getBoard().getTrackTurnOrder().getCurrentPlayer();
	}

	//@Override
	public boolean isFMPlaced() throws RemoteException {
		return isFMPlaced;
	}

	//@Override
	public List<Zone> getZones() throws RemoteException {
		return getBoard().getZones();
	}

	//@Override
	public Tower getTower(CardType cardType) throws RemoteException {
		return getBoard().getTowerSet().getTower(cardType);
	}

	//@Override
	public SpaceWorkZone getSpaceWorkZone(WorkType workType) throws RemoteException {
		return getBoard().getSpaceWorkZones().get(workType);
	}
}
