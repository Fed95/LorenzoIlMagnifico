package it.polimi.ingsw.gc12.view.client;

import it.polimi.ingsw.gc12.model.board.Board;
import it.polimi.ingsw.gc12.model.board.dice.Die;
import it.polimi.ingsw.gc12.model.board.dice.SpaceDie;
import it.polimi.ingsw.gc12.model.board.excommunication.ExcommunicationTile;
import it.polimi.ingsw.gc12.model.board.occupiable.Occupiable;
import it.polimi.ingsw.gc12.model.board.occupiable.Tower;
import it.polimi.ingsw.gc12.model.board.occupiable.TowerFloor;
import it.polimi.ingsw.gc12.model.board.occupiable.TowerSet;
import it.polimi.ingsw.gc12.model.board.track.TrackTurnOrder;
import it.polimi.ingsw.gc12.model.card.CardDevelopment;
import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.model.player.PlayerColor;
import it.polimi.ingsw.gc12.model.player.familymember.FamilyMember;

import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.stream.Collectors;

public abstract class MatchInstance extends Observable {
	protected Board board;
	protected int roundNum;
	protected Map<PlayerColor, Player> players;
	public transient final static int DEFAULT_ROUND_NUM = 6;
	public transient final static int DEFAULT_PERIODS_LEN = 2;
	public transient final static int DEFAULT_TOTAL_PERIODS_NUM = DEFAULT_ROUND_NUM/DEFAULT_PERIODS_LEN;
	protected boolean initialized;

	public void init(Match match) {
		this.board = match.getBoard();
		this.roundNum = 1;
		this.players = match.getPlayers();
	}

	public void setDice(SpaceDie spaceDie) {
		for(Die die: spaceDie.getDice().values()) {
			Die boardDie = board.getSpaceDie().getDie(die.getColor());
			boardDie.setValue(die.getValue());
		}
	}

	public void setTurn(int turn) {
		board.getTrackTurnOrder().setTurn(turn);
	}

    public Board getBoard() {
		return board;
	}

    public Map<PlayerColor, Player> getPlayers() {
        return players;
    }

    public void newTurn() {
		board.getTrackTurnOrder().newTurn();
	}

	private Occupiable getOccupiable(Occupiable occupiable) {
		//System.out.println("MatchInstance: retrieving the occupiable");
		List<Occupiable> occupiables =  board.getOccupiables().stream().filter(myOccupiable -> myOccupiable.equals(occupiable)).collect(Collectors.toList());
		//System.out.println("MatchInstance: occupiable found: " + occupiables);
		return occupiables.get(0);
	}

	private List<FamilyMember> getFamilyMembers(FamilyMember familyMember) {
		return board.getTrackTurnOrder().getCurrentPlayer().getFamilyMembers().values().stream()
				.filter(myFamilyMember -> myFamilyMember.equals(familyMember)).collect(Collectors.toList());
	}

	public int getRoundNum() {
		return roundNum;
	}

	public boolean isInitialized() {
		return initialized;
	}

	public abstract void setCards(TowerSet towers);
	protected abstract void setFamilyMemberObservers();
	public abstract void pickCard(CardDevelopment card, PlayerColor playerColor);
	public abstract void placeFamilyMember(FamilyMember familyMember, Occupiable occupiable, PlayerColor playerColor);
	public abstract void resetFamilyMembers();
	public abstract void resetBoard();
	public abstract void updateResources(List<Player> players);
	public abstract void playerExcommunication(Player player, ExcommunicationTile tile);
	public abstract void activateLeaderCard(PlayerColor playerColor, int id);
	public abstract void discardLeaderCard(PlayerColor playerColor, int id);
	public abstract void setTurnOrder(TrackTurnOrder turnOrder);
}
