package it.polimi.ingsw.gc_12;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polimi.ingsw.gc_12.resource.ResourceType;
import it.polimi.ingsw.gc_12.effect.Effect;
import it.polimi.ingsw.gc_12.effect.EffectHandler;
import it.polimi.ingsw.gc_12.event.Event;
import it.polimi.ingsw.gc_12.resource.Resource;

public class Player {
	private String name;
	private Match match;
	private EffectHandler effectHandler = EffectHandler.instance();
	private List<Card> cards = new ArrayList<>();
	private Map<ResourceType, Resource> resources;
	private Map<FamilyMemberColor, FamilyMember> familymembers = new HashMap<>();
	
	public Player(String name, Map<ResourceType, Resource> resources){
		this.name=name;
		this.resources = resources;
		for(FamilyMemberColor color : FamilyMemberColor.values()) {
			familymembers.put(color, new FamilyMember(color));
		}
	}
	
	public void rollDice(){
		match.getBoard().getSpaceDie().rollDice();
	}
	
	public void placeFamilyMember(FamilyMember familyMember, Occupiable occupiable){
		Event event = new Event(this, occupiable);
		effectHandler.executeEffects(event);
		if(!occupiable.canBeOccupiedBy(familyMember))
			effectHandler.discardEffects(event);
	}

	public List<Card> getCards() {
		return cards;
	}
	
	public List<Effect> getCardsEffects() {
		List<Effect> cardsEffects = new ArrayList<>();
		for(Card card : cards) {
			cardsEffects.addAll(card.getEffects());
		}
		
		return cardsEffects;
	}
	
	public void addCard(Card card) {
		cards.add(card);
	}
	
	public void addResource(Resource resource) {
		Resource ownedResource = this.resources.get(resource.getType());
		int newValue = ownedResource.getValue() + resource.getValue();
		ownedResource.setValue(newValue);
		this.resources.replace(resource.getType(), ownedResource);
	}
	
	public void removeResource(Resource resource) {
		Resource ownedResource = this.resources.get(resource.getType());
		int newValue = ownedResource.getValue() - resource.getValue();
		ownedResource.setValue(newValue);
		this.resources.replace(resource.getType(), ownedResource);
		
	}
	
	public Map<ResourceType, Resource> getResources() {
		return resources;
	}
	
	public Resource getResource(ResourceType resourceType) {
		return resources.get(resourceType);
	}
}
