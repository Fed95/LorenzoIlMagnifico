package it.polimi.ingsw.gc12.model.event;

import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.model.action.ActionChooseExchange;
import it.polimi.ingsw.gc12.controller.ActionHandler;
import it.polimi.ingsw.gc12.view.client.gui.MainBoard;
import it.polimi.ingsw.gc12.misc.json.loader.LoaderConfig;
import it.polimi.ingsw.gc12.model.player.resource.CouncilPrivilege;
import it.polimi.ingsw.gc12.model.player.resource.Resource;
import it.polimi.ingsw.gc12.model.player.resource.ResourceExchange;
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
	public void setActions(Match match) {
		actions = new ArrayList<>();
		if(match.getActionHandler().getCouncilPrivilegeResources().size() == 0)
			match.getActionHandler().setCouncilPrivilegeResources(new LoaderConfig().get(match).getCouncilPrivilegeResources());
		match.getActionHandler().setCouncilPrivileges(councilPrivilege.getValue());

		for(List<Resource> resources: match.getActionHandler().getCouncilPrivilegeResources()) {
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
