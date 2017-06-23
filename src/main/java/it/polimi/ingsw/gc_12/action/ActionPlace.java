package it.polimi.ingsw.gc_12.action;

import it.polimi.ingsw.gc_12.FamilyMember;
import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.effect.Effect;
import it.polimi.ingsw.gc_12.event.Event;
import it.polimi.ingsw.gc_12.event.EventPlaceFamilyMember;
import it.polimi.ingsw.gc_12.event.EventPlacementEnded;
import it.polimi.ingsw.gc_12.event.EventServantsRequested;
import it.polimi.ingsw.gc_12.exceptions.ActionNotAllowedException;
import it.polimi.ingsw.gc_12.exceptions.ActionDeniedException;
import it.polimi.ingsw.gc_12.exceptions.RequiredValueNotSatisfiedException;
import it.polimi.ingsw.gc_12.occupiables.Occupiable;
import it.polimi.ingsw.gc_12.resource.ResourceType;
import it.polimi.ingsw.gc_12.resource.Servant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class ActionPlace extends Action {

	protected FamilyMember familyMember;
	protected Servant servant;
	protected Occupiable occupiable;
	protected boolean complete;

	public ActionPlace(Player player, FamilyMember familyMember, Occupiable occupiable, Servant servant, boolean complete) {
		super(player);
		this.familyMember = familyMember;
		this.occupiable = occupiable;
		this.servant = servant;
		this.complete = complete;
	}

	public ActionPlace(Player player, FamilyMember familyMember, Occupiable occupiable) {
		this(player,familyMember, occupiable, new Servant(0), false);
	}

	public FamilyMember getFamilyMember() {
		return familyMember;
	}

	@Override
	public void start(Match match) {
		if(player.getName() == null)
			throw new IllegalArgumentException("Cannot start an action without a real player.");

		setup(match);
		if(!complete) {
			EventServantsRequested eventServants = new EventServantsRequested(player, occupiable, familyMember);

			try {
				match.getEffectHandler().executeEffects(match, eventServants);
			} catch (ActionDeniedException e) {
				throw new IllegalStateException("ActionPlace: starting an action that has been denied.");
			}

			match.getActionHandler().update(eventServants);
			//Notifies the ServerRMIView
			match.notifyObserver(eventServants);
		}
		else {
			Event event = new EventPlaceFamilyMember(player, occupiable, familyMember);

			int multiplier = ((EventPlaceFamilyMember) event).getMultiplier();
			int increment = (multiplier > 1 ? servant.getValue() / multiplier : servant.getValue());

			try{
				familyMember.setValue(familyMember.getValue() + increment);
				match.getEffectHandler().executeEffects(match, event);
				canBeExecuted(match);
				execute(match);
			}
			catch (RequiredValueNotSatisfiedException | ActionDeniedException | ActionNotAllowedException e) {
				throw new IllegalStateException("ActionPlace the action cannot be performed even if it has been considered valid");
			} finally {
				familyMember.setValue(familyMember.getValue() - increment);
			}
		}

	}

	@Override
	public boolean isValid(Match match) {
		if(player.getName() == null)
			throw new IllegalArgumentException("Cannot call isValid on an action without a real player.");

		setup(match);
		Event event = new EventPlaceFamilyMember(player, occupiable, familyMember);

		List<Effect> executedEffects = new ArrayList<>();
		try{
			familyMember.setValue(familyMember.getValue() + player.getResourceValue(ResourceType.SERVANT));
			//Can throw exceptions (in which case effects are discarded directly in EffectHandler)
			executedEffects = match.getEffectHandler().executeEffects(match, event, true);

			this.canBeExecuted(match);
		}
		catch (RequiredValueNotSatisfiedException | ActionDeniedException | ActionNotAllowedException e) {
			familyMember.setValue(familyMember.getValue() - player.getResourceValue(ResourceType.SERVANT));
			//match.getEffectHandler().discardEffects(executedEffects, event);
			return false;
		}
		familyMember.setValue(familyMember.getValue()-player.getResourceValue(ResourceType.SERVANT));
		//match.getEffectHandler().discardEffects(executedEffects, event);
		return true;
	}
	
	protected FamilyMember getRealFamilyMember(Match match){
    	return match.getBoard().getTrackTurnOrder().getCurrentPlayer().getFamilyMember(familyMember.getColor());
    }

    protected Integer getServants(Match match) {
		Integer ownedServants =  match.getBoard().getTrackTurnOrder().getCurrentPlayer().getResourceValue(ResourceType.SERVANT);
		if(servant.getValue() > ownedServants)
			return ownedServants;
		return servant.getValue();
	}

	protected void execute(Match match) {
		player.removeResources(Collections.singletonList(servant));
		match.placeFamilyMember(occupiable, familyMember);
		EventPlacementEnded event = new EventPlacementEnded(player);
		match.getActionHandler().update(event);
		match.notifyObserver(event);
	}

	protected abstract void setup(Match match);
	protected abstract void canBeExecuted(Match match) throws RequiredValueNotSatisfiedException, ActionNotAllowedException;

	@Override
	public String toString() {
		return occupiable.toString();
	}

}
