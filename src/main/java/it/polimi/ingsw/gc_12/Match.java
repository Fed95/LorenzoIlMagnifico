package it.polimi.ingsw.gc_12;
import java.util.List;
import java.util.Map;

import it.polimi.ingsw.gc_12.card.Card;
import it.polimi.ingsw.gc_12.card.CardType;

import java.util.ArrayList;
import java.util.HashMap;

public class Match {
	private final List<Player> players = new ArrayList<>(); //This goes to the class Match	
	private final List<BonusTile> bonusTiles = new ArrayList<>();
	private Map<CardType, List<Card>> cards = new HashMap<>();
	private int roundNum;
	private final static GameMode DEFAULT_GAME_MODE = GameMode.NORMAL;
	private final GameMode gameMode;
	private Board board;
	private static Match instance;
	
	public static Match instance() {
		if(instance == null) instance = new Match();
		return instance;
	}
	
	private Match(GameMode gameMode){		
		this.gameMode = gameMode;
		this.roundNum = 1;
	}
	
	private Match() {
		this(DEFAULT_GAME_MODE);
	}

	public void start(){
		board = new Board();
	}
	
	private void setInitialResources(){
		
	}
	
	public List<Player> getPlayers(){
		return players;
	}
	
	public List<BonusTile> getBonusTyles(){
		return bonusTiles;
	}
	
	public List<Card> getCards(CardType cardType){
		return cards.get(cardType);
	}
	
	public GameMode getGameMode(){
		return gameMode;
	}
	
	public Board getBoard(){
		return board;
	}
	
	public int getRoundNum(){
		return roundNum;
	}
	
	public int getPeriodNum(){
		return roundNum / 2 + roundNum % 2;
	}

	@Override
	public String toString() {
		return board.toString();
	}
}
