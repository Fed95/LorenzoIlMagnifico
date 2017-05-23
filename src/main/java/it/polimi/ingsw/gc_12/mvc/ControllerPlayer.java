package it.polimi.ingsw.gc_12.mvc;

import java.util.Observable;
import java.util.Observer;

import it.polimi.ingsw.gc_12.FamilyMember;
import it.polimi.ingsw.gc_12.Occupiable;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.action.Action;
import it.polimi.ingsw.gc_12.action.ActionPlaceFamilyMember;

public class ControllerPlayer implements Observer{
	private final Player player;
	private Action action;
	private ViewCLI view;
	
	public ControllerPlayer(Player player, ViewCLI view){
		this.player = player;
		this.view = view;
		this.view.addObserver(this);
		this.view.notifyObservers();
	}

	public void start() {
		this.view.askAction();
	}

	public Player getPlayer() {
		return player;
	}

	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof ViewCLI) {
			ViewCLI view = (ViewCLI) o;
			if(arg instanceof FamilyMember) {
				FamilyMember familyMember = (FamilyMember) arg;
				action = new ActionPlaceFamilyMember(player, familyMember);
				view.askOccupiable();
			}
			else if (arg instanceof Occupiable) {
				Occupiable occupiable = (Occupiable) arg;
				
				if(action instanceof ActionPlaceFamilyMember) {
					((ActionPlaceFamilyMember) action).setOccupiable(occupiable);
					if(!action.start()) {
						view.printError("Cannot place family member.");
						view.askAction();
					}

				}
				else {
					view.printError("Action not valid");
					view.askAction();
				}
			}
		}
	}
	
}
