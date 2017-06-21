package it.polimi.ingsw.gc_12;

import it.polimi.ingsw.gc_12.occupiables.Occupiable;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class MatchInstance implements Serializable, Cloneable {
	private Board board;
	private int roundNum;
	public transient final static int DEFAULT_ROUND_NUM = 6;
	public transient final static int DEFAULT_PERIODS_LEN = 2;
	public transient final static int DEFAULT_TOTAL_PERIODS_NUM = DEFAULT_ROUND_NUM/DEFAULT_PERIODS_LEN;
	private static MatchInstance instance;

	private MatchInstance() {}

	public static MatchInstance instance() {
		if(instance == null) instance = new MatchInstance();
		return instance;

	}

	public void init(Match match) {
		this.board = match.getBoard();
		this.roundNum = 1;
	}

	public Board getBoard() {
		return board;
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
}
