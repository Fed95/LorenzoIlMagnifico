package it.polimi.ingsw.gc_12.track;

import it.polimi.ingsw.gc_12.FamilyMember;
import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.occupiables.CouncilPalace;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TrackTurnOrder {

	private List<Player> turnOrder = new ArrayList<>();
	private int turn;
	private CouncilPalace councilPalace;

	public TrackTurnOrder(CouncilPalace councilPalace) {
		this.turnOrder = Match.instance().getPlayers();
		this.councilPalace =  councilPalace;
		chooseRandomOrder();
	}

	public void chooseRandomOrder() {
		this.turn = 0;
		Collections.shuffle(turnOrder);
	}

	public void newRound() {
		this.turn = 0;
		List<Player> newTurnOrder = new ArrayList<>();
		for(FamilyMember familyMember : councilPalace.getOccupiers()){
			if(!newTurnOrder.contains(familyMember.getOwner())){
				newTurnOrder.add(familyMember.getOwner());
				turnOrder.remove(familyMember.getOwner());
			}
		}
		newTurnOrder.addAll(turnOrder);
		turnOrder = newTurnOrder;
	}

	public Player getCurrentPlayer() {
		return turnOrder.get(turn);
	}

	public int newTurn() {
		turn++;
		if (turn == turnOrder.size())
			turn = 0;
		return turn;
	}

	//Places the specified player on top, shifting the position of the other players
	public void placeFirst(Player player){

	}

}
