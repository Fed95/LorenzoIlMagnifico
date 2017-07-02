package it.polimi.ingsw.gc_12.client;


import it.polimi.ingsw.gc_12.*;
import it.polimi.ingsw.gc_12.action.Action;
import it.polimi.ingsw.gc_12.client.socket.ClientOutHandler;
import it.polimi.ingsw.gc_12.event.*;
import it.polimi.ingsw.gc_12.java_fx.MainBoardController;
import it.polimi.ingsw.gc_12.mvc.ClientView;
import it.polimi.ingsw.gc_12.mvc.GUIAdapter;
import it.polimi.ingsw.gc_12.mvc.ViewCLI;
import javafx.application.Platform;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public abstract class ClientHandler extends UnicastRemoteObject {
	protected MatchInstance match;
	protected List<Action> actions = new ArrayList<>();
	protected LinkedList<Event> events = new LinkedList<>();
	protected int offset;
	protected int multiplier;
	private boolean myTurn;
	protected PlayerColor color;
	private boolean excluded;
	protected int unauthorizedId;
	private boolean started;
	private MainBoardController mainBoardController;
	private ClientObservable clientObservable;
	private ClientViewType viewType;

	protected ClientHandler(ClientView view) throws RemoteException {
		super();
		this.viewType = view.getType();
		this.clientObservable = new ClientObservable();
		clientObservable.registerObserver(view);
		if(viewType == ClientViewType.GUI) {
			GUIAdapter guiAdapter = new GUIAdapter();

		}
		this.multiplier = 1;
	}

	public void handleEvent() {
		Event event = events.peekFirst();
		if(event == null)
			return;

		if(event instanceof EventView)
			notifyView((EventView) event);

		if(event.toStringClient() != null) {
            System.out.println(event.toStringClient());
            if(mainBoardController!=null) {
                Platform.runLater(() -> mainBoardController.getChat().appendText("[SERVER]: " + event.toStringClientGUI()+"\n"));
            }
        }

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

	public MatchInstance createMatchInstance() {
		if(viewType == ClientViewType.CLI)
			return MatchInstanceCLI.instance();
		else
			return MatchInstanceGUI.instance();
	}

	public List<Action> getActions() {
		return actions;
	}

	public LinkedList<Event> getEvents() {
		return events;
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

	public void setColor(PlayerColor color) {
		this.color = color;
	}

    public Boolean getMyTurn(){
	    return myTurn;
    }

    public void setMainBoardController(MainBoardController mainBoardController) {
        this.mainBoardController = mainBoardController;
    }

    public MainBoardController getMainBoardController() {
        return mainBoardController;
    }

    public void notifyView(EventView event) {
    	clientObservable.notifyObserver(event);
	}

	public void setUnauthorizedId(int unauthorizedId) {
		this.unauthorizedId = unauthorizedId;
	}

	public boolean removeAction(int index) {
    	if(index < 0 || index >= actions.size())
    		return false;
    	actions.remove(index);
    	return true;
	}
}