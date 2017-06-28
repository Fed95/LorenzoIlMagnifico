package it.polimi.ingsw.gc_12.client;

import java.io.Serializable;

public class NewName implements Serializable{

	private int unauthorizedId;
	private String name;

	public NewName(int unauthorizedId, String name) {
		this.unauthorizedId = unauthorizedId;
		this.name = name;
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
}
