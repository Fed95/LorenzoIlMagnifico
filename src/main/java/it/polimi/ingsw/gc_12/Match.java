package it.polimi.ingsw.gc_12;

import it.polimi.ingsw.gc_12.card.*;
import it.polimi.ingsw.gc_12.effect.Effect;
import it.polimi.ingsw.gc_12.effect.EffectChangeFamilyMemberValue;
import it.polimi.ingsw.gc_12.effect.EffectChangeResource;
import it.polimi.ingsw.gc_12.event.EventPlaceFamilyMember;
import it.polimi.ingsw.gc_12.mvc.ControllerPlayer;
import it.polimi.ingsw.gc_12.mvc.ViewCLI;
import it.polimi.ingsw.gc_12.resource.Money;
import it.polimi.ingsw.gc_12.resource.Resource;
import it.polimi.ingsw.gc_12.resource.ResourceType;
import it.polimi.ingsw.gc_12.resource.VictoryPoint;

import java.util.*;

public class Match {
	private final List<Player> players = new ArrayList<>(); //This goes to the class Match
	private final List<BonusTile> bonusTiles = new ArrayList<>();
	private List<CardDevelopment> cards = new ArrayList<>();
	public CardDeckSet cardDeckSet;
	private final GameMode gameMode;
	public final static GameMode DEFAULT_GAME_MODE = GameMode.NORMAL;
	public final static int DEFAULT_PERIODS_NUM = 3;
	private Board board;
	private static Match instance;
	private int roundNum;
	private Map<Player, ControllerPlayer> controllers = new HashMap<>();

	public static Match instance() {
		if (instance == null)
			instance = new Match();
		return instance;
	}

	private Match(GameMode gameMode) {
		this.gameMode = gameMode;
		this.roundNum = 0;
		this.cardDeckSet = new CardDeckSet(cards, DEFAULT_PERIODS_NUM);
	}
	private Match() {
		this(DEFAULT_GAME_MODE);
	}

	// TODO: remove when loaded from JSON file
	private void initializeMatch() {
		// Setup players
		Map<ResourceType, Resource> resources1 = new HashMap<>();
		resources1.put(ResourceType.MONEY, new Money(5));
		Player player1 = new Player("tizio", resources1);
		players.add(player1);

		Map<ResourceType, Resource> resources2 = new HashMap<>();
		resources2.put(ResourceType.MONEY, new Money(6));
		Player player2 = new Player("caio", resources2);
		players.add(player2);

		board = new Board();
	}

	public void setup() {
		initializeMatch();
		setInitialResources();
	}

	public void start() {
		setupControllers();
		handleTurns();
	}

	private void setupControllers() {
		for (Player player : players) {
			System.out.println(player + player.getName());
			ControllerPlayer controller = new ControllerPlayer(player, new ViewCLI(player, board));
			controllers.put(player, controller);
		}
	}

	private void handleTurns() {
		// TODO: handle rounds in a better way
		while (roundNum < 6) {
			for (int i = 0; i < FamilyMemberColor.values().length; i++) {

				Player player = board.getTrackTurnOrder().getCurrentPlayer();
				System.out.println("Current turn of: " + player.getName());
				ControllerPlayer controller = controllers.get(player);
				controller.start();
				board.getTrackTurnOrder().newTurn();
			}
			board.getTowerSet().refresh();
			roundNum++;
		}
	}

	private void setInitialResources() {

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

	// TODO: remove when loading from JSON file
	public void initializeCards() {
		Tower tower = board.getTowerSet().getTower(CardType.BUILDING);
		Effect effect1 = new EffectChangeFamilyMemberValue(new EventPlaceFamilyMember(null, tower.getFloor(1), new FamilyMember()), 2);
		Card card1 = new CardBuilding(0, "Marco", 1, null, new ArrayList<>(Arrays.asList(effect1)));
		Effect effect2 = new EffectChangeResource(new EventPlaceFamilyMember(null, tower.getFloor(2), new FamilyMember()), null, new Money(2));
		Card card2 = new CardBuilding(1, "Federico", 1, null, new ArrayList<>(Arrays.asList(effect2)));
		Effect effect3 = new EffectChangeResource(new EventPlaceFamilyMember(null, tower.getFloor(3), new FamilyMember()), null, new VictoryPoint(-3));
		Card card3 = new CardBuilding(2, "Ruggero", 1, null, new ArrayList<>(Arrays.asList(effect3)));
	}
}
