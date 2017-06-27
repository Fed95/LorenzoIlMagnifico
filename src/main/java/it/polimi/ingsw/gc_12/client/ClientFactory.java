package it.polimi.ingsw.gc_12.client;

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
