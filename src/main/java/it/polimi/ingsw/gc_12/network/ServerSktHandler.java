package it.polimi.ingsw.gc_12.network;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.List;

/**
 * Created by marco on 02/06/2017.
 */
public class ServerSktHandler implements Runnable {
	private Socket socket;
	private MatchHandler matchHandler;

	public ServerSktHandler(Socket socket, MatchHandler matchHandler) {
		this.socket = socket;
		this.matchHandler = matchHandler;
	}

	@Override
	public void run() {
		try {
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

			// Get first object received, which is the Players related to the connected client
			Player player = (Player) ois.readObject();
			player.setSocket(socket);

			matchHandler.addPlayer(player);

			// Close input stream and socket
			ois.close();
			socket.close();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			/** TODO: handle **/
			e.printStackTrace();
		}
	}
}
