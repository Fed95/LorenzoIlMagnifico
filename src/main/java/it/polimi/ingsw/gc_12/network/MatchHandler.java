package it.polimi.ingsw.gc_12.network;

import java.util.ArrayList;
import java.util.List;

public class MatchHandler {
	private List<Room> rooms = new ArrayList<>();
	public static final int MAX_PLAYERS = 4;
	public static final int MIN_PLAYERS = 2;
	public static final int START_TIMEOUT = 20000;

	public synchronized Room addPlayer(Player player) {
		Room room;
		// If the are no rooms or they are all started, create a new room
		if(rooms.isEmpty() || rooms.get(rooms.size()-1).isStarted()) {
			Room newRoom = new Room();
			rooms.add(newRoom);
			room = newRoom;
		}
		else {
			room = rooms.get(rooms.size()-1);
		}
		room.addPlayer(player);
		System.out.println("Player " + player.getName() + " is connected to room " + room);

		if(room.getPlayers().size() == MIN_PLAYERS)
			setTimeout(room);
		else if (room.isFull()){
			room.startMatch();
			System.out.println("Match started");
		}
		return room;
	}

	public void setTimeout(Room room) {
		// After START_TIMEOUT (milliseconds), the match starts with the min number of players
		new java.util.Timer().schedule(
			new java.util.TimerTask() {
				@Override
				public void run() {
					if(!room.isStarted()) {
						room.startMatch();
						System.out.println("Match started after timeout");
					}

				}
			}, START_TIMEOUT
		);
	}

	public List<Room> getRooms() {
		return rooms;
	}
}
