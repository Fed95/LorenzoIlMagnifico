package it.polimi.ingsw.gc_12.event;

import it.polimi.ingsw.gc_12.FamilyMember;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.client.ClientHandler;
import it.polimi.ingsw.gc_12.occupiables.Occupiable;
import it.polimi.ingsw.gc_12.resource.ResourceType;

import java.util.ArrayList;
import java.util.Collections;

public class EventServantsRequested extends EventPlaceFamilyMember {

	private int mult;

	public EventServantsRequested(Player player, Occupiable occupiable, FamilyMember familyMember) {
		super(player, new ArrayList<>(Collections.singletonList(occupiable)), familyMember);
		mult = 1;
	}

    public EventServantsRequested() {
    }

    public void setMultiplier(int mult) {
		this.mult = mult;
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
	public String toString() {
		return "EventServantsRequested";
	}

	@Override
	public String toStringClient() {
		return "";
	}
}
