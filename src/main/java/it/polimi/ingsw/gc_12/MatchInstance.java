package it.polimi.ingsw.gc_12;

import it.polimi.ingsw.gc_12.java_fx.FamilyMemberRepresentation;
import it.polimi.ingsw.gc_12.occupiables.Occupiable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.stream.Collectors;

public class MatchInstance extends Observable implements Serializable, Cloneable {
	private Board board;
	private int roundNum;
	public transient final static int DEFAULT_ROUND_NUM = 6;
	public transient final static int DEFAULT_PERIODS_LEN = 2;
	public transient final static int DEFAULT_TOTAL_PERIODS_NUM = DEFAULT_ROUND_NUM/DEFAULT_PERIODS_LEN;
	private static MatchInstance instance;
	private ObservableList<FamilyMemberRepresentation> familyMemberBlueRepresentationObservableList = FXCollections.observableArrayList();
	private ObservableList<FamilyMemberRepresentation> familyMemberGreenRepresentationObservableList = FXCollections.observableArrayList();
	private ObservableList<FamilyMemberRepresentation> familyMemberRedRepresentationObservableList = FXCollections.observableArrayList();
	private ObservableList<FamilyMemberRepresentation> familyMemberYellowRepresentationObservableList = FXCollections.observableArrayList();
	private Map<PlayerColor, ObservableList<FamilyMemberRepresentation>> mapFamilyMember = new HashMap<>();
    private Map<PlayerColor, Player> players;
	private MatchInstance() {}
	private FamilyMemberRepresentation familyMemberRepresentation;
	public static MatchInstance instance() {
		if(instance == null) instance = new MatchInstance();
		return instance;

	}

	public void init(Match match) {
		this.board = match.getBoard();
		this.roundNum = 1;
		this.players = match.getPlayers();

		createFamilyMemberRepresentation(match);

		setChanged();
		notifyObservers();
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
	private void createFamilyMemberRepresentation(Match match){
		Map<PlayerColor, Player> players = match.getPlayers();
		for(Player player : players.values()){
		    if(player.getColor().equals(PlayerColor.BLUE)){
                mapFamilyMember.put(PlayerColor.BLUE, familyMemberBlueRepresentationObservableList);
            }
            if(player.getColor().equals(PlayerColor.GREEN)){
                mapFamilyMember.put(PlayerColor.GREEN, familyMemberGreenRepresentationObservableList);
            }
            if(player.getColor().equals(PlayerColor.RED)){
                mapFamilyMember.put(PlayerColor.RED, familyMemberRedRepresentationObservableList);
            }
            if(player.getColor().equals(PlayerColor.YELLOW)){
                mapFamilyMember.put(PlayerColor.YELLOW, familyMemberYellowRepresentationObservableList);
            }
        }

        for(Player player : players.values()) {
			List<FamilyMember> familyMembers = player.getAvailableFamilyMembers();
			for (FamilyMember familyMember : familyMembers) {
				familyMemberRepresentation = new FamilyMemberRepresentation(familyMember.getValue(), familyMember.getColor().toString(), familyMember.getOwner().getColor().toString(), familyMember.isBusy());
				mapFamilyMember.get( familyMember.getOwner().getColor()).add(familyMemberRepresentation);
            }
		}
		//familyMemberRepresentation = new FamilyMemberRepresentation(5,"black","blue");

	}
	public ObservableList<FamilyMemberRepresentation> getFamilyMemberBlueRepresentationObservableList() {
		return familyMemberBlueRepresentationObservableList;
	}

    public Map<PlayerColor, ObservableList<FamilyMemberRepresentation>> getMapPlayerColorObservableLiseFMRepr() {
        return mapFamilyMember;
    }
}
