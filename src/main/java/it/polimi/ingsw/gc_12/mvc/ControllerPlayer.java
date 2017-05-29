package it.polimi.ingsw.gc_12.mvc;

import java.util.*;

import it.polimi.ingsw.gc_12.*;
import it.polimi.ingsw.gc_12.action.Action;
import it.polimi.ingsw.gc_12.action.ActionPlaceFamilyMember;
import it.polimi.ingsw.gc_12.exceptions.CannotPlaceCardException;
import it.polimi.ingsw.gc_12.exceptions.CannotPlaceFamilyMemberException;
import it.polimi.ingsw.gc_12.exceptions.NotEnoughResourcesException;

public class ControllerPlayer{

	private Map<Player, ViewCLI> views = new HashMap<>();
	private Match match;
	private Action action;
	private Player currentPlayer;

	public ControllerPlayer(Match match){
		this.match = match;
		createViews();
	}

	private void createViews() {
		List<Player> players = match.getPlayers();
		for(Player player : players) {
			ViewCLI view = new ViewCLI(this, match);
			views.put(player, view);
		}
	}

	public void start() {
		currentPlayer = match.getBoard().getTrackTurnOrder().getCurrentPlayer();
		views.get(currentPlayer).askAction();
	}

	public void setFamilyMember(FamilyMemberColor familyMemberColor) {
		action = new ActionPlaceFamilyMember(currentPlayer, currentPlayer.getFamilyMember(familyMemberColor));
		views.get(currentPlayer).askOccupiable();
	}

	public void setOccupiable(Occupiable occupiable) {
		if(action instanceof ActionPlaceFamilyMember) {
			((ActionPlaceFamilyMember) action).setOccupiable(occupiable);

			try {
				action.start();
			} catch (CannotPlaceFamilyMemberException e){
				System.out.println(e.getMessage());
			} catch (CannotPlaceCardException e) {
				System.out.println(e.getMessage());
			} catch (NotEnoughResourcesException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public void vaticanReport(Player player) {
		boolean support = views.get(player).supportChurch();
		if (support) {
			player.supportChurch();
		}else{
			player.receiveExcommunication();
		}
	}

}
