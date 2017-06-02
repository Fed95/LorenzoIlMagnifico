package it.polimi.ingsw.gc_12.action;

import it.polimi.ingsw.gc_12.FamilyMember;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.exceptions.RequiredValueNotSatisfiedException;

public class ActionPlace extends Action {

	protected FamilyMember familyMember;

	public ActionPlace(Player player, FamilyMember familyMember) {
		super(player);
		this.familyMember = familyMember;
	}

	public FamilyMember getFamilyMember() {
		return familyMember;
	}

	@Override
	public void start() throws RuntimeException, RequiredValueNotSatisfiedException {}
}
