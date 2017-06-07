package it.polimi.ingsw.gc_12;

import it.polimi.ingsw.gc_12.card.*;
import it.polimi.ingsw.gc_12.effect.EffectHandler;
import it.polimi.ingsw.gc_12.excommunication.ExcommunicationTile;
import it.polimi.ingsw.gc_12.json.loader.LoaderMarket;
import it.polimi.ingsw.gc_12.json.loader.LoaderTowerSet;
import it.polimi.ingsw.gc_12.mvc.ControllerPlayer;
import it.polimi.ingsw.gc_12.server.controller.Change;
import it.polimi.ingsw.gc_12.server.controller.StateChange;
import it.polimi.ingsw.gc_12.server.model.State;
import it.polimi.ingsw.gc_12.server.observer.Observable;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;


public class Match extends Observable<Change> implements MatchRemote, Serializable{
	private transient List<Player> players = new ArrayList<>();
	private transient final List<BonusTile> bonusTiles = new ArrayList<>();
	private transient List<CardDevelopment> cards = new ArrayList<>();
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
		this.cardDeckSet = new CardDeckSet(cards, DEFAULT_ROUND_NUM/DEFAULT_PERIODS_LEN);
		this.effectHandler = new EffectHandler();
		this.gameState = State.PENDING;
	}

	public Match() {
		this(DEFAULT_GAME_MODE);
	}

	public void init(List<Player> players) {
		this.players = players;
		createBoard();
		cardDeckSet.shuffle();

		for (Player player : players) {
			player.init(effectHandler);
		}
	}

	private void createBoard() {
		board = new Board(players);
		board.setTowerSet(new LoaderTowerSet().get(this));
		board.setMarket(new LoaderMarket().get(this));
		board.getTowerSet().setCards(cardDeckSet);
	}

	public void start() {
		this.gameState = State.RUNNING;
		this.notifyObserver(new StateChange(this.gameState));
	}

	//Increments turn counter in TrackTurnOrder
	public void newTurn() {
		board.getTrackTurnOrder().newTurn();
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

	public List<CardDevelopment> getCards(CardType cardType) {
		return cards;
	}

	public GameMode getGameMode() {
		return gameMode;
	}

	public Board getBoard() {
		return board;
	}

	public int getRoundNUm() {
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

	@Override
	public Player getCurrentPlayer() throws RemoteException {
		return getBoard().getTrackTurnOrder().getCurrentPlayer();
	}

	@Override
	public boolean isFMPlaced() throws RemoteException {
		return isFMPlaced;
	}
}
