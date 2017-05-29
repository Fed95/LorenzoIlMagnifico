package it.polimi.ingsw.gc_12;

import it.polimi.ingsw.gc_12.card.*;
import it.polimi.ingsw.gc_12.mvc.ControllerPlayer;


import java.util.*;

public class Match {
	private List<Player> players = new ArrayList<>(); //This goes to the class Match
	private final List<BonusTile> bonusTiles = new ArrayList<>();
	private List<CardDevelopment> cards = new ArrayList<>();
	public CardDeckSet cardDeckSet;
	private final GameMode gameMode;
	public final static GameMode DEFAULT_GAME_MODE = GameMode.NORMAL;
	public final static int DEFAULT_ROUND_NUM = 6;
	public final static int DEFAULT_PERIODS_LEN = 2;
	private Board board;
	private static Match instance;
	private int roundNum;

	public static Match instance() {
		if (instance == null)
			instance = new Match();
		return instance;
	}

	private Match(GameMode gameMode) {
		this.gameMode = gameMode;
		this.roundNum = 1;
		this.cardDeckSet = new CardDeckSet(cards, DEFAULT_ROUND_NUM/DEFAULT_PERIODS_LEN);
	}
	private Match() {
		this(DEFAULT_GAME_MODE);
	}

	public void init() {
		board = new Board();
	}

	public void newTurn() {
		board.getTrackTurnOrder().newTurn();
	}

	public void newRound() {
		board.refresh();
		board.getTrackTurnOrder().newRound();
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
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

	@Override
	public String toString() {
		return board.toString();
	}
}
