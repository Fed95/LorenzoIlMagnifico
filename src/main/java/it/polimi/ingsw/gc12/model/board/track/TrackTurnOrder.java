package it.polimi.ingsw.gc12.model.board.track;

import it.polimi.ingsw.gc12.model.player.familymember.FamilyMember;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.model.board.occupiable.CouncilPalace;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class implements the turn order track, so has the turn of the player and a conucil palace
 * that is responsible of changing turn
 */
public class TrackTurnOrder implements Serializable{

	private List<Player> turnOrder = new ArrayList<>();
	private int turn;
	private CouncilPalace councilPalace;

    /**
     * Constructor choose a random player order at first
     * @param players players of the match
     * @param councilPalace council palace reference
     */
	public TrackTurnOrder(List<Player> players, CouncilPalace councilPalace) {
		this.turnOrder = players;
		this.councilPalace =  councilPalace;
		chooseRandomOrder();
	}

    /**
     * Choose random player order
     */
	public void chooseRandomOrder() {
		this.turn = -1;
		Collections.shuffle(turnOrder);
	}

    /**
     * Method for new round, refresh the list of the ordered players
     */
	public void newRound() {
		this.turn = -1;
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
		if(turn == -1)
			return turnOrder.get(turn+1);
		return turnOrder.get(turn);
	}

	public List<Player> getOrderedPlayers() {
		return turnOrder;
	}

    /**
     * Return the player of the turn
     * @return player of the turn
     */
	public synchronized Player newTurn() {
		turn++;
		if (turn == turnOrder.size())
			turn = 0;

		return turnOrder.get(turn);
	}

	//Places the specified player on top, shifting the position of the other players
	public void placeFirst(Player player){

	}

	public void setTurn(int turn) {
		this.turn = turn;
	}

	public int getTurn() {
		return turn;
	}
}
