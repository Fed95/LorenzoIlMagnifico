package it.polimi.ingsw.gc_12;

import it.polimi.ingsw.gc_12.card.Card;
import it.polimi.ingsw.gc_12.excommunication.ExcommunicationTile;
import it.polimi.ingsw.gc_12.personal_board.PersonalBoard;
import it.polimi.ingsw.gc_12.resource.Resource;
import it.polimi.ingsw.gc_12.resource.ResourceBuilder;
import it.polimi.ingsw.gc_12.resource.ResourceType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Player implements Serializable{
	
	private final String name;
	private final PlayerColor playerColor;
	private PersonalBoard personalBoard;
	private List<ExcommunicationTile> excommunications = new ArrayList<>();
	private Map<ResourceType, Resource> resources;
	private Map<FamilyMemberColor, FamilyMember> familymembers = new HashMap<>();
	private PlayerState state;
	
	public Player(String name, PlayerColor playerColor){
		this.name = name;
		this.playerColor = playerColor;
		this.personalBoard = new PersonalBoard();
		Map<ResourceType, Resource> resources = new HashMap<>();
		for(ResourceType resourceType: ResourceType.values()) {
			resources.put(resourceType, ResourceBuilder.create(resourceType, 100));

		}
		this.resources = resources;
		personalBoard.getResourceContainer().syncronize(this.resources);
		this.state = PlayerState.ACTION;
	}

	public Player(PlayerColor playerColor) {
		this.playerColor = playerColor;
		this.name = null;
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
		if(resource != null) {
			Resource ownedResource = this.resources.get(resource.getType());
			int newValue = ownedResource.getValue() + resource.getValue();
			ownedResource.setValue(newValue);
			this.resources.put(resource.getType(), ownedResource);
		}
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

	private void removeResource(Resource resourceToRemove) {
		if(resourceToRemove != null) {
			Resource ownedResource = this.resources.get(resourceToRemove.getType());
			int newValue = ownedResource.getValue() - resourceToRemove.getValue();
			newValue = newValue < 0 ? 0 : newValue;
			ownedResource.setValue(newValue);
		}
	}

	public void removeResources(List<Resource> resourcesToRemove) {
		//fills the array with the affected resources updating their values
		for(Resource resource: resourcesToRemove)
			this.removeResource(resource);
		personalBoard.getResourceContainer().syncronize(this.resources);
	}

	public boolean hasResources(List<Resource> resources) {
		return resources.stream().allMatch(resource -> resource.getValue() < this.resources.get(resource.getType()).getValue());
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

	public PlayerState getState() {
		return state;
	}

	public void setState(PlayerState state) {
		this.state = state;
	}

	public PlayerColor getColor() {
		return playerColor;
	}

	public String printResources(){
		StringBuilder sb = new StringBuilder();
		for(Resource resource : resources.values())
			sb.append(" - " + resource).append(System.getProperty("line.separator"));
		return sb.toString();
	}

	public String printFamilyMembers(){
		StringBuilder sb = new StringBuilder();
		for(FamilyMember fm : familymembers.values())
			sb.append(" - " + fm.getColor() + " [" + fm.getValue() + "] busy: " + fm.isBusy()).append(System.getProperty("line.separator"));
		return sb.toString();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Player)) return false;

		Player player = (Player) o;

		return playerColor == player.playerColor;
	}

	@Override
	public int hashCode() {
		return playerColor.hashCode();
	}
}
