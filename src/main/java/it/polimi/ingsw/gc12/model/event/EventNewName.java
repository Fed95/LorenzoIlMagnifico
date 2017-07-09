package it.polimi.ingsw.gc12.model.event;

import it.polimi.ingsw.gc12.view.client.ClientHandler;
import it.polimi.ingsw.gc12.view.client.gui.MainBoard;

public class EventNewName extends Event implements EventView{

	private int unauthorizedId;
	private String name;

	public EventNewName(int unauthorizedId, String name) {
		this.unauthorizedId = unauthorizedId;
		this.name = name;
	}

	public EventNewName(int unauthorizedId) {
		this.unauthorizedId = unauthorizedId;
	}

	@Override
	public void executeClientSide(ClientHandler client) {
		System.out.println("This name is already taken from another active player.");
		System.out.println("Choose another one.");
		client.setUnauthorizedId(unauthorizedId);
	}

	public int getUnauthorizedId() {
		return unauthorizedId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "";
	}

	@Override
	public void executeViewSide(MainBoard view) {
		view.errorNameTaken();
	}
}
