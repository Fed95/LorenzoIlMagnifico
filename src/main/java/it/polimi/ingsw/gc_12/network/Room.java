package it.polimi.ingsw.gc_12.network;

import java.util.ArrayList;
import java.util.List;

public class Room {
	private List<Player> members = new ArrayList<>();
	private boolean isStarted = false;
	
	public List<Player> getPlayers() {
		return members;
	}
	
	public boolean addPlayer(Player player) {
		if(members.contains(player) || isFull()) // TODO: handle with exceptions
			return false;
		
		members.add(player);
		return true;
	}

	public boolean isFull() {
		return members.size() >= MatchHandler.MAX_PLAYERS;
	}
	
	public boolean isStarted() {
		return isStarted;
	}
	
	public boolean startMatch() {
		if(isStarted || members.size() < MatchHandler.MIN_PLAYERS)
			return false;
		
		isStarted = true;
		return true;
	}
	

}
