package it.polimi.ingsw.gc_12;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polimi.ingsw.gc_12.effect.EffectProvider;
import it.polimi.ingsw.gc_12.event.EventSupportChurch;
import it.polimi.ingsw.gc_12.personal_board.PersonalBoard;
import it.polimi.ingsw.gc_12.excommunication.ExcommunicationTile;
import it.polimi.ingsw.gc_12.resource.ResourceExchange;
import it.polimi.ingsw.gc_12.resource.ResourceType;
import it.polimi.ingsw.gc_12.event.Event;
import it.polimi.ingsw.gc_12.card.Card;
import it.polimi.ingsw.gc_12.effect.EffectHandler;
import it.polimi.ingsw.gc_12.resource.Resource;
import it.polimi.ingsw.gc_12.track.FaithSlot;

public class Player {
	
	private final String name;
	private Match match;
	private PersonalBoard personalBoard;
	private EffectHandler effectHandler;
	private List<ExcommunicationTile> excommunications = new ArrayList<>();
	private Map<ResourceType, Resource> resources;
	private Map<FamilyMemberColor, FamilyMember> familymembers = new HashMap<>();

	
	public Player(String name, PersonalBoard personalBoard, Map<ResourceType, Resource> resources){
		this.name = name;
		this.personalBoard = personalBoard;
		this.resources = resources;
	}

	public void init(EffectHandler effectHandler) {
		this.effectHandler = effectHandler;
		for(FamilyMemberColor color : FamilyMemberColor.values()) {
			familymembers.put(color, new FamilyMember(this, color));
		}
	}

	public void supportChurch() {
		EffectProvider faithSlot = Match.instance().getBoard().getFaithSlots().get(this.resources.get(ResourceType.FAITH_POINT).getValue());
		Event event = new EventSupportChurch(this, (FaithSlot) faithSlot);

		try {
			effectHandler.executeEffects(event);
		}catch(RuntimeException e){
			//This exception is never actually thrown for this event
		}
		this.resources.get(ResourceType.FAITH_POINT).setValue(0);

	}

	public void receiveExcommunication(ExcommunicationTile excommunicationTile){
		excommunications.add(excommunicationTile);
	}

	private void addResource(Resource resource) {
		if(resource == null)
			return;
		Resource ownedResource = this.resources.get(resource.getType());
		int newValue = ownedResource.getValue() + resource.getValue();
		ownedResource.setValue(newValue);
		this.resources.replace(resource.getType(), ownedResource);
	}
	
	public void addResources(List<Resource> resources) {
		for(Resource resource: resources) {
			this.addResource(resource);
		}
	}

	public Integer getResourceValue(ResourceType type){
		return this.resources.get(type).getValue();
	}
	public void setResourceValue(ResourceType type, int value){
		this.resources.get(type).setValue(value);
	}

	private void removeResource(List<Resource> newResources, Resource resourceToRemove) throws RuntimeException {

		System.out.println("player: trying to remove " + resourceToRemove);

		try {
			Resource ownedResource = this.resources.get(resourceToRemove.getType());

			System.out.println("player: owned resource: " + ownedResource);

			int newValue = ownedResource.getValue() - resourceToRemove.getValue();

			System.out.println("player: new resource value: " + newValue);

			if(newValue < 0)
				throw new RuntimeException("You don't have enough " + ownedResource.getType() + " resources!");

			Resource newResource = ownedResource;
			newResource.setValue(newValue);
			newResources.add(newResource);

		}catch(NullPointerException e) {
			throw new RuntimeException("You don't have any resources!");
		}
	}

	public void removeResources(List<Resource> resourcesToRemove) throws RuntimeException {

		List<Resource> newResources = new ArrayList<>();
		//fills the array with the affected resources updating their values
		for(Resource resource: resourcesToRemove)
			//Can throw an exception
			this.removeResource(newResources, resource);

		//If no exceptions were thrown the resources are updated with the new values
		for(Resource resource : newResources){
			this.resources.replace(resource.getType(), resource);
		}
	}

	public void hasResources(List<Resource> resources) throws RuntimeException{
		this.removeResources(resources);
		this.addResources(resources);
	}
	
	public Map<ResourceType, Resource> getResources() {
		return resources;
	}

	public ResourceExchange chooseResourceExchange(List<ResourceExchange> resourceExchanges) {
		//TODO: implement comunication with controller
		return null;
	}

	public void resetFaithPoints(){
		//TODO: implement method
	}

	public String getName() {
		return name;
	}

	public Map<FamilyMemberColor, FamilyMember> getFamilymembers() {
		return familymembers;
	}

	public FamilyMember getFamilyMember(FamilyMemberColor familyMemberColor) {
		return familymembers.get(familyMemberColor);
	}

	public PersonalBoard getPersonalBoard() {
		return personalBoard;
	}

	//TODO: add excommunication tiles to list
	public List<Card> getCards() {
		List<Card> cards = new ArrayList<>();
		cards.addAll(this.getPersonalBoard().getCards());
		return cards;
	}

	public EffectHandler getEffectHandler(){
		return effectHandler;
	}

	public List<ExcommunicationTile> getExcommunications() {
		return excommunications;
	}

	public Match getMatch() {
		return match;
	}
}
