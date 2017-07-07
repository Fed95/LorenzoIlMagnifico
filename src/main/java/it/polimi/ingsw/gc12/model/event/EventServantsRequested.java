package it.polimi.ingsw.gc12.model.event;

import it.polimi.ingsw.gc12.controller.ActionHandler;
import it.polimi.ingsw.gc12.model.action.*;
import it.polimi.ingsw.gc12.model.player.familymember.FamilyMember;
import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.view.client.ClientHandler;
import it.polimi.ingsw.gc12.view.client.gui.MainBoard;
import it.polimi.ingsw.gc12.model.board.occupiable.Occupiable;
import it.polimi.ingsw.gc12.model.player.resource.Resource;
import it.polimi.ingsw.gc12.model.player.resource.ResourceType;
import it.polimi.ingsw.gc12.model.player.resource.Servant;
import javafx.application.Platform;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EventServantsRequested extends EventPlaceFamilyMember implements EventView{

	private int mult;
	private List<Resource> discounts = new ArrayList<>();

	public EventServantsRequested(Player player, Occupiable occupiable, FamilyMember familyMember) {
		super(player, new ArrayList<>(Collections.singletonList(occupiable)), familyMember);
		mult = 1;
	}

	public EventServantsRequested(Player player, Occupiable occupiable, FamilyMember familyMember, List<Resource> discounts) {
		this(player, occupiable, familyMember);
		if(discounts != null)
			this.discounts = discounts;
	}

    public EventServantsRequested() {
    }

    public void setMultiplier(int mult) {
		this.mult = mult;
	}

	public void setDiscounts(List<Resource> discounts) {
		this.discounts = discounts;
	}

	public int getMultiplier() {
		return mult;
	}

	@Override
	public void executeClientSide(ClientHandler client) {
		if(client.isMyTurn()) {
			client.setActions(actions);
			printServantsChoice(client);
		}
	}

	private void printServantsChoice(ClientHandler client) {
		int offset = (getOccupiable().getRequiredValue() - getFamilyMember().getValue()) * mult;
		offset = (offset < 0 ? 0 : offset);
		client.setOffset(offset);
		int maxValue = player.getResourceValue(ResourceType.SERVANT);

		System.out.println("You have " + player.getResourceValue(ResourceType.SERVANT) + " Servants");

		if(mult > 1){
			setMultiplier(mult);
			double value = 1.0 / (double) mult;
			System.out.println("(An effect multiplies the value of your servants by : " + value + ")");
		}
		System.out.println("How many would you like to use?	min: " + offset + ", max: " + maxValue + " - (Press " + (maxValue + 1) + " to go back)");
	}

	@Override
	public void setActions(Match match) {
		actions = new ArrayList<>();
		int i = getOccupiable().getRequiredValue() - familyMember.getValue();
		i = (i < 0 ? 0 : i);
		match.getActionHandler().setOffset(i);
		//actionHandler.setMultiplier(mult);
		for (; i <= player.getResourceValue(ResourceType.SERVANT); i++) {
			ActionPlace action = ActionFactory.createActionPlace(player, familyMember, getOccupiable(), new Servant(i), true, null);
			if(discounts.size() > 0 && action instanceof ActionPlaceOnTower)
				action.setDiscounts(discounts);
			if(action.isValid(match))
				actions.add(action);
			if(mult > 1)
				action.setMultiplier(mult);
		}
		actions.add(new DiscardAction(player));
	}

	@Override
	public String toString() {
		return "EventServantsRequested";
	}

	@Override
	public String toStringClient() {
		return "";
	}

	@Override
	public void executeViewSide(MainBoard view) {
		Platform.runLater(() -> view.getControllerMainBoard().requestServants());
	}
}
