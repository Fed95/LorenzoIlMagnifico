package it.polimi.ingsw.gc_12.event;

import it.polimi.ingsw.gc_12.FamilyMember;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.client.ClientHandler;
import it.polimi.ingsw.gc_12.effect.EffectProvider;
import it.polimi.ingsw.gc_12.occupiables.Tower;

import java.util.ArrayList;
import java.util.List;

public class EventFamilyMemberChosen extends Event {

	private FamilyMember familyMember;
	
	public EventFamilyMemberChosen(Player player, FamilyMember familyMember) {
		super(player);
		this.familyMember = familyMember;

		effectProviders.addAll(player.getCards());
		effectProviders.addAll(player.getExcommunications());
	}
	
	public EventFamilyMemberChosen(FamilyMember familyMember) {
		super();
		this.familyMember = familyMember;
	}

	public EventFamilyMemberChosen() {
		super();
	}

	@Override
	public void executeClientSide(ClientHandler client) {
		for(Tower tower: client.getMatch().getBoard().getTowerSet().getTowers().values()) {
			System.out.println(tower);
		}
	}

	@Override
	public List<Object> getAttributes() {
		List<Object> attributes = new ArrayList<>();
		attributes.add(familyMember);
		return attributes;
	}

	public FamilyMember getFamilyMember() {
		return familyMember;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(System.getProperty("line.separator"));
		sb.append(player.getName() + " has chosen the " + familyMember.getColor() + " Family Member (value " + familyMember.getValue() + ")");
		return sb.toString();
	}
}
