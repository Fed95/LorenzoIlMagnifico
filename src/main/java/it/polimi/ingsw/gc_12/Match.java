package it.polimi.ingsw.gc_12;

import it.polimi.ingsw.gc_12.action.ActionHandler;
import it.polimi.ingsw.gc_12.card.Card;
import it.polimi.ingsw.gc_12.card.CardDeckSet;
import it.polimi.ingsw.gc_12.card.CardType;
import it.polimi.ingsw.gc_12.effect.Effect;
import it.polimi.ingsw.gc_12.effect.EffectHandler;
import it.polimi.ingsw.gc_12.effect.EffectProvider;
import it.polimi.ingsw.gc_12.event.*;
import it.polimi.ingsw.gc_12.excommunication.ExcommunicationTile;
import it.polimi.ingsw.gc_12.json.loader.*;
import it.polimi.ingsw.gc_12.occupiables.Occupiable;
import it.polimi.ingsw.gc_12.occupiables.TowerFloor;
import it.polimi.ingsw.gc_12.personal_board.BonusTile;
import it.polimi.ingsw.gc_12.resource.CouncilPrivilege;
import it.polimi.ingsw.gc_12.resource.Resource;
import it.polimi.ingsw.gc_12.server.model.State;
import it.polimi.ingsw.gc_12.server.observer.Observable;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;


public class Match extends Observable<Event> implements Serializable, EffectProvider{
	private Map<PlayerColor, Player> players = new HashMap<>();
	private List<BonusTile> bonusTiles;
	private transient List<Card> cards = new ArrayList<>();
	private transient List<ExcommunicationTile> excommunicationTiles;
	private transient CardDeckSet cardDeckSet;
    private Board board;
	private int roundNum;
	private int period;
	private int turnCounter;
	private int reportCounter;
	private final int DEFAULT_FAMILY_MEMBERS = 4;
	private transient EffectHandler effectHandler;
	private transient ActionHandler actionHandler;
	public transient final static GameMode DEFAULT_GAME_MODE = GameMode.NORMAL;
	public transient final static int DEFAULT_ROUND_NUM = 6;
	public transient final static int DEFAULT_PERIODS_LEN = 2;
	public transient final static int DEFAULT_TOTAL_PERIODS_NUM = DEFAULT_ROUND_NUM / DEFAULT_PERIODS_LEN;
	private State gameState;
	private int numReady;

	public Match() {
		this.roundNum = 0;
		this.turnCounter = 0;
		this.reportCounter = 0;
		this.period = 0;
		this.cards = new LoaderCard().get(this);
		this.excommunicationTiles = new LoaderExcommmunications().get(this);
		this.cardDeckSet = new CardDeckSet(cards, DEFAULT_ROUND_NUM / DEFAULT_PERIODS_LEN);
		this.effectHandler = new EffectHandler();
		this.actionHandler = new ActionHandler(this);

		this.gameState = State.PENDING;
	}

	public void init(Map<PlayerColor, Player> players) {
		this.players = players;

		this.bonusTiles = new LoaderBonusTile().get(this);
		Collections.shuffle(bonusTiles);

		List<Player> playersList = new ArrayList<>(players.values());
		for (int i = 0; i < playersList.size(); i++) {
			playersList.get(i).getPersonalBoard().setBonusTile(bonusTiles.get(i));
		}
		//TODO: remove comment before deadline (disabled shuffle for testing)
		//cardDeckSet.shuffle();

		createBoard();
		initPlayers();
	}

	private void createBoard() {
		System.out.println("Match: Creating the board");
		board = new Board(new ArrayList<>(players.values()));
		board.setTowerSet(new LoaderTowerSet().get(this));
		board.getTowerSet().setCards(cardDeckSet);
		board.getExcommunicationSpace().setTilesDeck(excommunicationTiles);
		board.setMarket(new LoaderMarket().get(this));
		System.out.println(board.getMarket().getSpaceMarkets());
	}

	private void initPlayers() {
		Config config = new LoaderConfig().get(this);
		List<Player> playerList = new ArrayList<>(players.values());
		for (int i = 0; i < playerList.size(); i++) {
			Player player = playerList.get(i);
			// TODO: remove the comment before the deadline
			// It has been commented to have a lot of resources for testing
			// player.setInitialResources(config.getInitialResources().get(i));
			player.init(board.getSpaceDie());
			player.getPersonalBoard().setCardsSpaces(new LoaderCardsSpace().get(this));
		}
	}

	public void start() {
		this.gameState = State.RUNNING;

		this.notifyObserver(new EventStartMatch(this));
		newPeriod();
		newRound();
		newTurn();
		System.out.println("Match: notifying EventStartMatch");
	}

	//Increments turn counter in TrackTurnOrder
	public void newTurn() {
		if(turnCounter == (players.size() * FamilyMemberColor.values().length)) {
			boolean stop = newRound();
			if(stop)
				return;
		}

		System.out.println("Match: Starting new turn");
		Player player = board.getTrackTurnOrder().newTurn();
		EventStartTurn event = new EventStartTurn(player);
		actionHandler.update(event);
		this.notifyObserver(event);
		this.turnCounter++;
	}

	private boolean newRound(){
		if(roundNum != 0 && roundNum%2 == 0) {
			endPeriod();
			newPeriod();
		}
		roundNum++;
		turnCounter = 0;
		resetFamilyMembers();
		board.refresh(roundNum, getPeriodNum());
		this.notifyObserver(new EventStartRound(roundNum, board.getTowerSet()));
		return !(roundNum != 0 && roundNum % 2 == 0);
	}

	public void excludeCurrentPlayer() {
		Player player = board.getTrackTurnOrder().getCurrentPlayer();
		Event event = new EventExcluded(player);
		actionHandler.update(event);
		notifyObserver(event);
	}

	private void resetFamilyMembers() {
		for(Player player: players.values()) {
			player.resetFamilyMembers();
		}
	}

	private void endPeriod() {
		vaticanReport(board.getTrackTurnOrder().getOrderedPlayers());
	}

	private void newPeriod() {
		this.notifyObserver(new EventStartPeriod());
	}

	public void vaticanReport(List<Player> playerList){
		Event event = new EventVaticanReport(playerList.get(0), excommunicationTiles.get(getPeriodNum()), playerList);
		actionHandler.update(event);
		notifyObserver(event);
	}

	public synchronized void countReport(){
		reportCounter ++;
		if(reportCounter == players.size()){
			reportCounter = 0;
			newTurn();
		}
	}
	
	public void placeFamilyMember(Occupiable occupiable, FamilyMember familyMember) {

		occupiable.placeFamilyMember(familyMember);
		if(familyMember.getColor() != null)
			familyMember.setBusy(true);

		if(familyMember.getColor() != null) {
			EventPlaceFamilyMember event = new EventPlaceFamilyMember(board.getTrackTurnOrder().getCurrentPlayer(), occupiable, familyMember);
			actionHandler.update(event);
			this.notifyObserver(event);
		}

		if(occupiable instanceof TowerFloor){
			EventPickCard eventPickCard = new EventPickCard(board.getTrackTurnOrder().getCurrentPlayer(), ((TowerFloor) occupiable).getCard());
			this.notifyObserver(eventPickCard);
		}
	}

	public void addResources(Player player, List<Resource> resources) {
		for(Resource resource: resources) {
			if(resource instanceof CouncilPrivilege) {
				Event event = new EventCouncilPrivilegeReceived(player, (CouncilPrivilege) resource);
				actionHandler.update(event);
				notifyObserver(event);
			}
		}

		player.addResources(resources);
	}

	public void setPlayers(Map<PlayerColor, Player> players) {
		this.players = players;
	}


	public Map<PlayerColor, Player> getPlayers() {
		return players;
	}

	public Player getPlayer(String name) {
		List<Player> playersFiltered = players.values().stream().filter(player -> name.equals(player.getName())).collect(Collectors.toList());
		if(playersFiltered.size() > 0)
			return playersFiltered.get(0);
		else
			return null;
	}

	public List<BonusTile> getBonusTyles() {
		return bonusTiles;
	}

	public List<Card> getCards(CardType cardType) {
		return cards;
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

	@Override
	public String toString() {
		return board.toString();
	}

	public EffectHandler getEffectHandler() {
		return effectHandler;
	}

	public ActionHandler getActionHandler() {
		return actionHandler;
	}

	public void addReady() {
		numReady++;
		if(numReady == 2) {
			start();
		}
	}

	@Override
	public List<Effect> getEffects() {
		return null;
	}
}
