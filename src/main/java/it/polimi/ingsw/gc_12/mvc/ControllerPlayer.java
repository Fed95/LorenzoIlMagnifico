package it.polimi.ingsw.gc_12.mvc;

import java.util.*;

import it.polimi.ingsw.gc_12.*;
import it.polimi.ingsw.gc_12.action.*;
import it.polimi.ingsw.gc_12.card.Card;
import it.polimi.ingsw.gc_12.card.CardDevelopment;
import it.polimi.ingsw.gc_12.card.CardType;
import it.polimi.ingsw.gc_12.exceptions.RequiredValueNotSatisfiedException;
import it.polimi.ingsw.gc_12.excommunication.ExcommunicationTile;
import it.polimi.ingsw.gc_12.occupiables.*;
import it.polimi.ingsw.gc_12.resource.Resource;
import it.polimi.ingsw.gc_12.resource.ResourceType;
import it.polimi.ingsw.gc_12.resource.Servant;

public class ControllerPlayer{

	private Map<Player, View> adapters = new HashMap<>();
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
			View adapter = new CLIAdapter(player, match, this);
			adapters.put(player, adapter);
		}
	}

	public void start() {
		isFMPlaced = false;
		currentPlayer = match.getBoard().getTrackTurnOrder().getCurrentPlayer();
		View adapter = adapters.get(currentPlayer);
		adapter.startTurn();
		adapter.askAction();
	}

	public void setFamilyMember(FamilyMemberColor familyMemberColor) throws RuntimeException {
		FamilyMember familyMember = currentPlayer.getFamilyMember(familyMemberColor);
		if(familyMember.isBusy())
			throw new RuntimeException("This FamilyMember is already busy!");
		action = new ActionPlace(currentPlayer, familyMember);
		adapters.get(currentPlayer).askOccupiable();
	}

	public void setOccupiable(Occupiable occupiable) {

		if(action instanceof ActionPlace) {
			View view = adapters.get(currentPlayer);
			FamilyMember familyMember = ((ActionPlace) action).getFamilyMember();
			action = ActionFactory.getActionPlace(occupiable, familyMember, match);
			try{
				action.start();
				System.out.println(familyMember + " placed on " + occupiable);
				isFMPlaced = true;
				view.askAction();
			}catch (RuntimeException e){
				System.out.println(e.getMessage());
				view.askOccupiable();
			}catch (RequiredValueNotSatisfiedException e){
				//TODO: implement bonus / malus check
				int requiredServants = occupiable.getRequiredValue() - familyMember.getValue();
				if(currentPlayer.getResourceValue(ResourceType.SERVANT) < requiredServants) {
					System.out.println("You don't have enough Servants for this placement");
					view.askOccupiable();
				}else{
					int usedServants = view.askServants(requiredServants);
					familyMember.setValue(familyMember.getValue() + usedServants);
					List<Resource> servants = new ArrayList<>();
					servants.add(new Servant(usedServants));
					currentPlayer.removeResources(servants);
					this.setOccupiable(occupiable);
				}
			}
		}
		else {
			//TODO: throw exception
			throw new RuntimeException();
		}
	}

	public void vaticanReport(Player player) {
		boolean support = adapters.get(player).supportChurch();
		if (support) {
			player.supportChurch();
		}else{
			receiveExcommunication(player);
		}
	}

	public void receiveExcommunication(Player player){
		ExcommunicationTile excommunicationTile = match.getBoard().getExcommunicationSpace().getTiles().get(match.getPeriodNum());
		player.receiveExcommunication(excommunicationTile);
		adapters.get(player).excommunicationMessage();
	}

	public void viewStatistics() {
		adapters.get(currentPlayer).viewStatistics();
	}

	public void freeAction(CardType type, int value){
		adapters.get(currentPlayer).askFreeAction(type, value);
	}

	public boolean isFMPlaced() {
		return isFMPlaced;
	}

	public void pickCard(Card card) {
		if(currentPlayer.getPersonalBoard().canPlaceCard(currentPlayer, (CardDevelopment) card))
			currentPlayer.getPersonalBoard().placeCard((CardDevelopment) card);
	}
}
