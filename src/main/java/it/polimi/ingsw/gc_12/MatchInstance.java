package it.polimi.ingsw.gc_12;

import it.polimi.ingsw.gc_12.card.CardDevelopment;
import it.polimi.ingsw.gc_12.card.CardType;
import it.polimi.ingsw.gc_12.dice.Die;
import it.polimi.ingsw.gc_12.dice.SpaceDie;
import it.polimi.ingsw.gc_12.java_fx.CardFloorRepresentation;
import it.polimi.ingsw.gc_12.java_fx.FamilyMemberRepresentation;
import it.polimi.ingsw.gc_12.occupiables.Occupiable;
import it.polimi.ingsw.gc_12.occupiables.TowerFloor;
import it.polimi.ingsw.gc_12.occupiables.TowerSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

import java.io.Serializable;
import java.util.HashMap;
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

	public void placeFamilyMember(Occupiable occupiable, FamilyMember familyMember) {
		getOccupiable(occupiable).placeFamilyMember(familyMember);
		if(getFamilyMembers(familyMember).size() > 0) {
			getFamilyMembers(familyMember).get(0).setBusy(true);
		}

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


}
