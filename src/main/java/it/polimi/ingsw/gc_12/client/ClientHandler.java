package it.polimi.ingsw.gc_12.client;


import it.polimi.ingsw.gc_12.*;
import it.polimi.ingsw.gc_12.action.Action;
import it.polimi.ingsw.gc_12.event.*;
import it.polimi.ingsw.gc_12.mvc.View;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public abstract class ClientHandler extends UnicastRemoteObject {
	protected MatchInstance match;
	protected View view;
	protected List<Action> actions = new ArrayList<>();
	protected LinkedList<Event> events = new LinkedList<>();
	protected int offset;
	protected int multiplier;
	private boolean myTurn;
	protected PlayerColor color;
	private boolean excluded;
	protected int unauthorizedId;
	private boolean started;

	protected ClientHandler(View view) throws RemoteException {
		super();
		this.view = view;
		this.multiplier = 1;
	}

	public void handleEvent() {
		Event event = events.peekFirst();
		if(event == null)
			return;

		if(event.toStringClient() != null)
			System.out.println(event.toStringClient());
		actions = event.getActions();

		System.out.println("HANDLING "+event.getClass().getSimpleName());
		event.executeClientSide(this);

		if(event.getPlayer() != null && myTurn && !excluded) {
			if(event.getActions().size() == 0) {
				events.removeFirst();
				handleEvent();
			}
		}
		else {
			events.removeFirst();
			if(events.size() > 0)
				handleEvent();
		}

	}


	public List<Action> getActions() {
		return actions;
	}

	public LinkedList<Event> getEvents() {
		return events;
	}

	public View getView() {
		return view;
	}

	public void setView(View view) {
		this.view = view;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public MatchInstance getMatch() {
		return match;
	}

	public void setMatch(MatchInstance match) {
		this.match = match;
	}

	public int getMultiplier() {
		return multiplier;
	}

	public boolean isMyTurn() {
		return myTurn;
	}

	public void setMyTurn(boolean myTurn) {
		this.myTurn = myTurn;
	}

	public PlayerColor getColor() {
		return color;
	}

	public void setActions(List<Action> actions) {
		this.actions = actions;
	}

	public boolean isExcluded() {
		return excluded;
	}

	public void setExcluded(boolean excluded) {
		this.excluded = excluded;
	}

	public boolean isAuthorized() {
		return unauthorizedId == 0;
	}

	public int getUnauthorizedId() {
		return unauthorizedId;
	}

	public boolean isStarted() {
		return started;
	}

	public void setStarted(boolean started) {
		this.started = started;
	}
}