package it.polimi.ingsw.gc_12.mvc;

import java.util.*;

import it.polimi.ingsw.gc_12.*;
import it.polimi.ingsw.gc_12.action.*;
import it.polimi.ingsw.gc_12.occupiables.*;

public class ControllerPlayer{

	private Map<Player, View> views = new HashMap<>();
	private Match match;
	private Action action;
	private Player currentPlayer;
	private boolean isFMPlaced;

	public ControllerPlayer(Match match){
		this.match = match;
		createViews();
	}

	private void createViews() {
		List<Player> players = match.getPlayers();
		for(Player player : players) {
			View view = new CLIAdapter(player, match, this);
			views.put(player, view);
		}
	}

	public void start() {
		isFMPlaced = false;
		currentPlayer = match.getBoard().getTrackTurnOrder().getCurrentPlayer();
		View view = views.get(currentPlayer);
		view.startTurn();
		view.askAction(isFMPlaced);
	}

	public void setFamilyMember(FamilyMemberColor familyMemberColor) throws RuntimeException {
		FamilyMember familyMember = currentPlayer.getFamilyMember(familyMemberColor);
		if(familyMember.isBusy())
			throw new RuntimeException("This FamilyMember is already busy!");
		action = new ActionPlace(currentPlayer, familyMember);
		views.get(currentPlayer).askOccupiable();
	}

	public void setOccupiable(Occupiable occupiable) {

		if(action instanceof ActionPlace) {
			FamilyMember familyMember = ((ActionPlace) action).getFamilyMember();
			action = ActionFactory.getActionPlace(occupiable, familyMember, match);
			try{
				action.start();
				System.out.println(familyMember + " placed on " + occupiable);
				isFMPlaced = true;
				views.get(currentPlayer).askAction(isFMPlaced);
			}catch (RuntimeException e){
				System.out.println(e.getMessage());
				views.get(currentPlayer).askOccupiable();
			}
		}
		else {
			//TODO: throw exception
		}
	}

	public void vaticanReport(Player player) {
		boolean support = views.get(player).supportChurch();
		if (support) {
			player.supportChurch();
		}else{
			receiveExcommunication(player);
		}
	}

	public void receiveExcommunication(Player player){
		player.receiveExcommunication();
		views.get(player).excommunicationMessage();
	}

}
