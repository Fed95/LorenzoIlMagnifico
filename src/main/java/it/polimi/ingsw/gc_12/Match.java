package it.polimi.ingsw.gc_12;

import it.polimi.ingsw.gc_12.action.ActionHandler;
import it.polimi.ingsw.gc_12.client.rmi.ClientRMIView;
import it.polimi.ingsw.gc_12.client.rmi.ClientViewRemote;
import it.polimi.ingsw.gc_12.card.*;
import it.polimi.ingsw.gc_12.client.socket.ClientInHandler;
import it.polimi.ingsw.gc_12.effect.Effect;
import it.polimi.ingsw.gc_12.effect.EffectHandler;
import it.polimi.ingsw.gc_12.effect.EffectProvider;
import it.polimi.ingsw.gc_12.event.*;
import it.polimi.ingsw.gc_12.exceptions.ActionDeniedException;
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
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


public class Match extends Observable<Event> implements Serializable, EffectProvider{
	private Map<PlayerColor, Player> players = new HashMap<>();
	private List<BonusTile> bonusTiles;
	private List<CardDevelopment> developmentCards = new ArrayList<>();
	private List<LeaderCard> leaderCards = new ArrayList<>();
	private transient List<ExcommunicationTile> excommunicationTiles;
	private transient List<Integer> trackFaithPointValues;
	private transient CardDeckSet cardDeckSet;
    private Board board;
	private int roundNum;
	private int period;
	private int turnCounter;
	private int reportCounter;
	private transient final int TIMEOUT_ACTION;
	private transient Timer timer = new Timer();
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
		this.TIMEOUT_ACTION = new LoaderConfig().get(this).getTimeoutAction();
		this.roundNum = 0;
		this.turnCounter = 0;
		this.reportCounter = 0;
		this.period = 0;
		handleCards();
		this.excommunicationTiles = new LoaderExcommmunications().get(this);
		this.trackFaithPointValues = new LoaderTrackFaithPointsValues().get(this);
		this.cardDeckSet = new CardDeckSet(developmentCards, DEFAULT_ROUND_NUM / DEFAULT_PERIODS_LEN);
		this.effectHandler = new EffectHandler();
		this.actionHandler = new ActionHandler(this);

		this.gameState = State.PENDING;
	}

	private void handleCards(){
		List<Card> cards = new LoaderCard().get(this);
		for(Card card : cards) {
			if (card instanceof CardDevelopment)
				developmentCards.add((CardDevelopment) card);
			else
				leaderCards.add((LeaderCard) card);
		}
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
		//Collections.shuffle(leaderCards);

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
		board.setTrackFaithPoints(trackFaithPointValues);
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
			for(int j = 0; j < 4; j++)
				player.getPersonalBoard().getLeaderCardsSpace().getCards().add(leaderCards.get(4*i + j));
		}
	}

	public void start() {
		this.gameState = State.RUNNING;

		this.notifyObserver(new EventStartMatch(this));
		newPeriod();
		newRound();
		newTurn();
		System.out.println("Match: notifying EventStartMatch");
		checkConnection();
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

		EventStartTurn event = new EventStartTurn(player, board.getTrackTurnOrder().getTurn());
		try {
			effectHandler.executeEffects(this, event);
		} catch (ActionDeniedException e) {
			e.printStackTrace();
		}
		actionHandler.update(event, this);
		this.notifyObserver(event);
		this.turnCounter++;
		setTimeoutAction();
		if(player.isDisconnected() || player.isExcluded()) {
			actionHandler.flushEvents();
			newTurn();
		}

	}

	private void checkConnection() {
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				notifyObserver();
			}
		}, 2000, 2000);
	}

	private boolean newRound(){
		if(roundNum != 0 && roundNum%1 == 0){
			endMatch();
			return true;
		}
		boolean vatican = roundNum != 0 && roundNum%2 == 0;
		if(vatican) {
			endPeriod();
			newPeriod();
		}
		roundNum++;
		turnCounter = 0;
		resetFamilyMembers();
		board.refresh(roundNum, getPeriodNum());
		this.notifyObserver(new EventStartRound(roundNum, board.getTowerSet(), board.getSpaceDie()));
		return vatican;
	}

	private void excludeCurrentPlayer() {
		Player player = board.getTrackTurnOrder().getCurrentPlayer();
		Event event = new EventExcluded(player);
		actionHandler.update(event, this);
		player.setExcluded(true);
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
		actionHandler.update(event, this);
		notifyObserver(event);
	}

	private void endMatch(){
		for (Player player : players.values()) {
			Event event = new EventEndMatch(player);
			try {
				effectHandler.executeEffects(this, event);
			} catch (ActionDeniedException e) {
				throw new IllegalStateException();
			}
		}
		List<Player> players = new ArrayList<>();
		players.addAll(this.players.values());
		notifyObserver(new EventEndMatch(getBoard().getVictroyPointsTrack().getPlayerOrdered(players)));
	}

	public void placeFamilyMember(Occupiable occupiable, FamilyMember familyMember) {

		occupiable.placeFamilyMember(familyMember);
		if(familyMember.getColor() != null)
			familyMember.setBusy(true);

		if(familyMember.getColor() != null) {
			EventPlaceFamilyMember event = new EventPlaceFamilyMember(board.getTrackTurnOrder().getCurrentPlayer(), occupiable, familyMember);
			actionHandler.update(event, this);
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
				actionHandler.update(event, this);
				notifyObserver(event);
			}
		}

		player.addResources(resources);
	}

	public void setDisconnectedPlayer(Player player) {
		if(player.equals(board.getTrackTurnOrder().getCurrentPlayer())) {
			actionHandler.flushEvents();
			newTurn();
		}

		player.setDisconnected(true);
		System.out.println("PLAYER " + player.getName() + " DISCONNECTED");
	}

	public void setReconnectedPlayer(Player playerReconnected, ClientViewRemote client) {
		for(Player player: players.values()) {
			if(player.getName().toLowerCase().equals(playerReconnected.getName().toLowerCase())) {
				System.out.println("PLAYER " + player.getName() + " RECONNECTED");
				notifyObserver(new EventPlayerReconnected(player, this, client));
				player.setDisconnected(false);
				return;
			}
		}
		throw new IllegalStateException("Set reconnection of a player that doesn't belong to this match");
	}

	public void setReconnectedPlayer(Player playerReconnected) {
		for(Player player: players.values()) {
			if(player.getName().toLowerCase().equals(playerReconnected.getName().toLowerCase())) {
				System.out.println("PLAYER " + player.getName() + " RECONNECTED");
				notifyObserver(new EventPlayerReconnected(player, this));
				player.setDisconnected(false);
				return;
			}
		}
		throw new IllegalStateException("Set reconnection of a player that doesn't belong to this match");
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
		List<Card> cards = new ArrayList<>();
		cards.addAll(developmentCards);
		cards.addAll(leaderCards);
		return cards;
	}

	public List<LeaderCard> getPlayedLeaderCards(){
		List<LeaderCard> cards = new ArrayList<>();
		for(Player player : players.values())
			for(LeaderCard card : player.getPersonalBoard().getPlayedLeaderCards())
				cards.add(card);
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

	protected void setTimeoutAction() {
		timer.cancel();
		timer.purge();
		timer = new Timer();
		System.out.println("setup timer action");
		timer.schedule(new TimerActionTask(this), TIMEOUT_ACTION);
	}

	class TimerActionTask extends TimerTask {

		private Match match;

		public TimerActionTask(Match match) {
			this.match = match;
		}

		@Override
		public void run() {
			match.excludeCurrentPlayer();
			match.newTurn();
		}
	}
}
