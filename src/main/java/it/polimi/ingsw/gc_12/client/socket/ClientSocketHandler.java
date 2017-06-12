package it.polimi.ingsw.gc_12.client.socket;

import it.polimi.ingsw.gc_12.MatchInstance;
import it.polimi.ingsw.gc_12.action.Action;
import it.polimi.ingsw.gc_12.client.ClientSender;
import it.polimi.ingsw.gc_12.event.EventStartMatch;
import it.polimi.ingsw.gc_12.mvc.View;
import it.polimi.ingsw.gc_12.mvc.ViewCLI;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientSocketHandler implements ClientSender, Runnable {

	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private MatchInstance match;
	private View view;

	public ClientSocketHandler(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {

		try {
			in = new ObjectInputStream(socket.getInputStream());
			out = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}


		while(true){

			// handles input messages coming from the server, just showing them to the user
			try {
				Object object=in.readObject();
				if(object instanceof EventStartMatch) {
					System.out.println("ClientRMI: EventStartMatch recognised. Creating view with local match.");
					EventStartMatch event = (EventStartMatch) object;
					match = event.getMatchInstance();

					createView(match);
				}

				System.out.println(object);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void createView(MatchInstance match) throws IOException {
		view = new ViewCLI(match, this);
	}

	@Override
	public void sendAction(Action action) throws IOException {
		System.out.println("sendAction "+action);
		out.writeObject(action);
		out.flush();

	}
}
