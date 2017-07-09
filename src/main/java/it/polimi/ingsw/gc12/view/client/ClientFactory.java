package it.polimi.ingsw.gc12.view.client;

/**
 * The ClientFactory is used by the GUI to get the ClientHandler and ClientSender.
 * These two classes are unique for each client, so the GUI can access them without passing the references to it.
 * This choice has been forced by the fact that the javaFX application is quite "isolated",
 * hence it's difficult to pass a reference after it has been already initialized.
 */

public class ClientFactory {

	private static ClientSender clientSender;
	private static ClientHandler clientHandler;

	public static void setClientHandler(ClientHandler clientHandler) {
		ClientFactory.clientHandler = clientHandler;
	}

	public static ClientHandler getClientHandler() {
		return clientHandler;
	}

	public static void setClientSender(ClientSender clientSender) {
		ClientFactory.clientSender = clientSender;
	}

	public static ClientSender getClientSender() {
		return clientSender;
	}
}
