package it.polimi.ingsw.gc_12.mvc;

import java.util.Observable;
import java.util.Observer;

import it.polimi.ingsw.gc_12.FamilyMember;
import it.polimi.ingsw.gc_12.Occupiable;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.action.Action;
import it.polimi.ingsw.gc_12.action.ActionPlaceFamilyMember;
import it.polimi.ingsw.gc_12.exceptions.FamilyMemberAlreadyPresentException;
import it.polimi.ingsw.gc_12.exceptions.InvalidParametersException;
import it.polimi.ingsw.gc_12.exceptions.OccupiableAlreadyTakenException;
import it.polimi.ingsw.gc_12.exceptions.RequiredValueNotSatisfiedException;

public class ControllerPlayer implements Observer{
	private final Player player;
	private Action action;
	private ViewCLI view;
	private FamilyMember familyMember;
	
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
				familyMember = (FamilyMember) arg;
				action = new ActionPlaceFamilyMember(player, familyMember);
				view.askOccupiable();
			}
			else if (arg instanceof Occupiable) {
				Occupiable occupiable = (Occupiable) arg;
				
				if(action instanceof ActionPlaceFamilyMember) {
					((ActionPlaceFamilyMember) action).setOccupiable(occupiable);
					try {
						action.start();
					} catch (RequiredValueNotSatisfiedException e) {
						//e.printStackTrace();
						System.out.println("The required value for this placement is not satisfied.");
						view.askAction();
					} catch (FamilyMemberAlreadyPresentException e) {
						//e.printStackTrace();
						System.out.println("You already have a Family Member working here.");
						view.askAction();
					} catch (InvalidParametersException e) {
						//e.printStackTrace();
						System.out.println("Something went wrong...");
						view.askAction();
					} catch (OccupiableAlreadyTakenException e) {
						//e.printStackTrace();
						System.out.println("This space is already taken.");
						view.askAction();
					}
					System.out.println(familyMember + " placed in " + occupiable);
				}
				else {
					view.printError("Action not valid");
					view.askAction();
				}
			}
		}
	}
	
}
