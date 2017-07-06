package it.polimi.ingsw.gc_12.event;

import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.action.ActionChooseExchange;
import it.polimi.ingsw.gc_12.action.ActionHandler;
import it.polimi.ingsw.gc_12.java_fx.MainBoard;
import it.polimi.ingsw.gc_12.json.loader.LoaderConfig;
import it.polimi.ingsw.gc_12.resource.CouncilPrivilege;
import it.polimi.ingsw.gc_12.resource.Resource;
import it.polimi.ingsw.gc_12.resource.ResourceExchange;
import javafx.application.Platform;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EventCouncilPrivilegeReceived extends Event implements EventView {

	private CouncilPrivilege councilPrivilege;
	private List<Integer> choices;

	public EventCouncilPrivilegeReceived(Player player, CouncilPrivilege councilPrivilege, List<Integer> choices) {
		super(player);
		this.councilPrivilege = councilPrivilege;
		this.choices = choices;
	}

	public EventCouncilPrivilegeReceived(Player player, CouncilPrivilege councilPrivilege) {
		this(player, councilPrivilege, new ArrayList<>());
	}

	@Override
	public void setActions(ActionHandler actionHandler, Match match) {
		actions = new ArrayList<>();
		if(actionHandler.getCouncilPrivilegeResources().size() == 0)
			actionHandler.setCouncilPrivilegeResources(new LoaderConfig().get(match).getCouncilPrivilegeResources());
		actionHandler.setCouncilPrivileges(councilPrivilege.getValue());

		for(List<Resource> resources: actionHandler.getCouncilPrivilegeResources()) {
			ResourceExchange resourceExchange = new ResourceExchange(new ArrayList<>(Collections.singletonList(new CouncilPrivilege(1))), resources);
			actions.add(new ActionChooseExchange(player, resourceExchange));
		}
	}

	public CouncilPrivilege getCouncilPrivilege() {
		return councilPrivilege;
	}

	public List<Integer> getChoices() {
		return choices;
	}

	@Override
	public String toString() {
		return "";
	}

	@Override
	public void executeViewSide(MainBoard view) {
		Platform.runLater(() -> view.getControllerMainBoard().handleCouncilPrivilege(councilPrivilege));
	}
}
