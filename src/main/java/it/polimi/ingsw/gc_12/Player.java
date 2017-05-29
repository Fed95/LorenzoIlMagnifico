package it.polimi.ingsw.gc_12;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polimi.ingsw.gc_12.exceptions.CannotPlaceCardException;
import it.polimi.ingsw.gc_12.exceptions.CannotPlaceFamilyMemberException;
import it.polimi.ingsw.gc_12.exceptions.NotEnoughResourcesException;
import it.polimi.ingsw.gc_12.personalBoard.PersonalBoard;
import it.polimi.ingsw.gc_12.resource.ResourceExchange;
import it.polimi.ingsw.gc_12.resource.ResourceType;
import it.polimi.ingsw.gc_12.event.Event;
import it.polimi.ingsw.gc_12.event.EventPlaceFamilyMember;
import it.polimi.ingsw.gc_12.card.Card;
import it.polimi.ingsw.gc_12.effect.Effect;
import it.polimi.ingsw.gc_12.effect.EffectHandler;
import it.polimi.ingsw.gc_12.resource.Resource;

public class Player {
	
	private final String name;
	private Match match;
	private PersonalBoard personalBoard;
	private EffectHandler effectHandler = EffectHandler.instance();
	private List<Card> cards = new ArrayList<>();
	private Map<ResourceType, Resource> resources;
	private Map<FamilyMemberColor, FamilyMember> familymembers = new HashMap<>();
	private Integer faithPoints = 0;
	private Integer militaryPoints = 0;
	private Integer victoryPoints = 0;
	
	public Player(String name, PersonalBoard personalBoard, Map<ResourceType, Resource> resources){
		this.name = name;
		this.personalBoard = personalBoard;
		this.resources = resources;
		for(FamilyMemberColor color : FamilyMemberColor.values()) {
			familymembers.put(color, new FamilyMember(this, color));
		}
	}
	
	public void rollDice(){
		match.getBoard().getSpaceDie().rollDice();
	}
	
	public void placeFamilyMember(FamilyMember familyMember, Occupiable occupiable) throws CannotPlaceFamilyMemberException, CannotPlaceCardException, NotEnoughResourcesException {
		Event event = new EventPlaceFamilyMember(this, occupiable, familyMember);
		effectHandler.executeEffects(event);
		try {
			occupiable.placeFamilyMember(familyMember);
		} catch(Exception e){
			effectHandler.discardEffects(event);
			throw e;
		}

		// TODO: implement placement
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
	
	private void removeResource(Resource resource) throws NotEnoughResourcesException {
		if(resource == null)
			throw new NotEnoughResourcesException();

		Resource ownedResource = this.resources.get(resource.getType());
		int newValue = ownedResource.getValue() - resource.getValue();

		if(newValue < 0)
			throw new NotEnoughResourcesException();

		ownedResource.setValue(newValue);
		this.resources.replace(resource.getType(), ownedResource);
	}
	
	public void removeResources(List<Resource> resources) throws NotEnoughResourcesException {
		for(Resource resource: resources)
			//Can throw an exception
			this.removeResource(resource);
	}

	public boolean hasResources(List<Resource> resources){
		try{
			this.removeResources(resources);
		} catch (NotEnoughResourcesException e) {
			return false;
		}
		this.addResources(resources);
		return true;
	}
	
	public Map<ResourceType, Resource> getResources() {
		return resources;
	}
	
	public Resource getResource(ResourceType resourceType) {
		return resources.get(resourceType);
	}
	public Integer getMilitaryPoints(){
		return militaryPoints;
	}
	public Integer getVictoryPoints(){
		return victoryPoints;
	}
	public Integer getFaithPoints(){
		return faithPoints;
	}

	public void setFaithPoints(Integer faithPoints) throws IllegalArgumentException {
		if(faithPoints == null){
			throw new IllegalArgumentException("Faith points parameter cannot be null");
		}else{
			this.faithPoints = faithPoints;
		}
	}

	public void setMilitaryPoints(Integer militaryPoints) throws IllegalArgumentException {
		if(militaryPoints == null){
			throw new IllegalArgumentException("Military points parameter cannot be null");
		}else{
			this.militaryPoints = militaryPoints;
		}
	}

	public void setVictoryPoints(Integer victoryPoints) throws IllegalArgumentException{
		if(victoryPoints == null){
			throw new IllegalArgumentException("Victory points parameter cannot be null");
		}else{
			this.victoryPoints = victoryPoints;

		}
	}
	public void resetFaithPoints(){
		faithPoints = 0;
	}

	public ResourceExchange chooseResourceExchange(List<ResourceExchange> resourceExchanges) {
		//TODO: implement comunication with controller
		return null;
	}
}
