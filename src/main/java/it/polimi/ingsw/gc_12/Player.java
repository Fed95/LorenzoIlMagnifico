package it.polimi.ingsw.gc_12;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

public class Player implements Serializable{
	
	private final String name;
	private transient Match match;
	private PersonalBoard personalBoard;
	private transient List<ExcommunicationTile> excommunications = new ArrayList<>(); //TODO: UNDERSTAND WHY IT IS NOT INITIALISED
	private Map<ResourceType, Resource> resources;
	private Map<FamilyMemberColor, FamilyMember> familymembers = new HashMap<>();
	private PlayerState state;
	
	public Player(String name, Map<ResourceType, Resource> resources){
		this.name = name;
		this.personalBoard = new PersonalBoard();
		this.resources = resources;
		personalBoard.getResourceContainer().syncronize(this.resources);
		this.state = PlayerState.ACTION;
	}

	public void setPersonalBoard(PersonalBoard personalBoard) {
		this.personalBoard = personalBoard;
	}

	public void init() {
		for(FamilyMemberColor color : FamilyMemberColor.values()) {
			familymembers.put(color, new FamilyMember(this, color));
		}
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
		personalBoard.getResourceContainer().syncronize(this.resources);
	}

	public Integer getResourceValue(ResourceType type){
		return this.resources.get(type).getValue();
	}

	public void setResourceValue(ResourceType type, int value){
		this.resources.get(type).setValue(value);
		personalBoard.getResourceContainer().syncronize(resources);
	}

	private void removeResource(Resource resourceToRemove) throws RuntimeException {
		Resource ownedResource = this.resources.get(resourceToRemove.getType());
		ownedResource.setValue(ownedResource.getValue() - resourceToRemove.getValue());
	}

	public void removeResources(List<Resource> resourcesToRemove) throws RuntimeException {
		//fills the array with the affected resources updating their values
		for(Resource resource: resourcesToRemove)
			this.removeResource(resource);
		for(Resource resource : resources.values())
			if(resource.getValue() < 0)
				throw new RuntimeException();
		personalBoard.getResourceContainer().syncronize(this.resources);
	}

	public boolean hasResources(List<Resource> resources) throws RuntimeException{
		try {
			this.removeResources(resources);
		}catch (RuntimeException e){
			this.addResources(resources);
			return false;
		}
		this.addResources(resources);
		return true;
	}
	
	public Map<ResourceType, Resource> getResources() {
		return resources;
	}

	public String getName() {
		return name;
	}

	public Map<FamilyMemberColor, FamilyMember> getFamilyMembers() {
		return familymembers;
	}
	
	public List<FamilyMember> getAvailableFamilyMembers() {
		return familymembers.values().stream().filter(familyMember -> !familyMember.isBusy()).collect(Collectors.toList());
	}

	public FamilyMember getFamilyMember(FamilyMemberColor familyMemberColor) {
		return familymembers.get(familyMemberColor);
	}

	public PersonalBoard getPersonalBoard() {
		return personalBoard;
	}

	public List<Card> getCards() {
		List<Card> cards = new ArrayList<>();
		cards.addAll(this.getPersonalBoard().getCards());
		return cards;
	}

	public List<ExcommunicationTile> getExcommunications() {
		return excommunications;
	}

	public Match getMatch() {
		return match;
	}

	public PlayerState getState() {
		return state;
	}

	public void setState(PlayerState state) {
		this.state = state;
	}

	public String printResources(){
		StringBuilder sb = new StringBuilder();
		for(Resource resource : resources.values())
			sb.append(" - " + resource);
		return sb.toString();
	}

	public String printFamilyMembers(){
		StringBuilder sb = new StringBuilder();
		for(FamilyMember fm : familymembers.values())
			sb.append(" - " + fm.getColor() + " [" + fm.getValue() + "] busy: " + fm.isBusy()).append(System.getProperty("line.separator"));
		return sb.toString();
	}
}
