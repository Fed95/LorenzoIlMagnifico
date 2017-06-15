package it.polimi.ingsw.gc_12;

import it.polimi.ingsw.gc_12.action.Action;
import it.polimi.ingsw.gc_12.action.ActionFactory;
import it.polimi.ingsw.gc_12.action.ActionPlace;
import it.polimi.ingsw.gc_12.occupiables.Occupiable;
import it.polimi.ingsw.gc_12.resource.Servant;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by marco on 14/06/2017.
 */
public class ActionHandler {

	public static List<ActionPlace> getAvailableActions(Match match, Player player, FamilyMember familyMember, Servant servant) throws IOException {
		List<ActionPlace> actions = new ArrayList<>();
		for(Occupiable occupiable: match.getBoard().getOccupiables()) {
			ActionPlace action = ActionFactory.createActionPlace(player, familyMember, occupiable, servant);
			if(action.isValid(match)) {
				actions.add(action);
			}
		}
		return actions;
	}
}
