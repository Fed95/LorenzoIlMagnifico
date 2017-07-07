package it.polimi.ingsw.gc12.model.player;

import it.polimi.ingsw.gc12.model.card.*;
import it.polimi.ingsw.gc12.model.board.dice.Die;
import it.polimi.ingsw.gc12.model.board.dice.DieColor;
import it.polimi.ingsw.gc12.model.board.dice.SpaceDie;
import it.polimi.ingsw.gc12.model.board.excommunication.ExcommunicationTile;
import it.polimi.ingsw.gc12.model.player.familymember.FamilyMember;
import it.polimi.ingsw.gc12.model.player.familymember.FamilyMemberColor;
import it.polimi.ingsw.gc12.model.player.personalboard.PersonalBoard;
import it.polimi.ingsw.gc12.model.player.resource.Resource;
import it.polimi.ingsw.gc12.model.player.resource.ResourceBuilder;
import it.polimi.ingsw.gc12.model.player.resource.ResourceType;

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
	private boolean disconnected;
	private boolean excluded;
	
	public Player(String name, PlayerColor playerColor){
		this.name = name;
		this.playerColor = playerColor;
		this.personalBoard = new PersonalBoard();
		Map<ResourceType, Resource> resources = new HashMap<>();
		for(ResourceType resourceType: ResourceType.values()) {
			// TODO: set to 0 before the deadline
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

	public void init(SpaceDie spaceDie) {
		for(FamilyMemberColor color : FamilyMemberColor.values()) {
			FamilyMember familyMember = new FamilyMember(this, color);
			familymembers.put(color, familyMember);
			// Check if there is a die with the same color of the family member's one
			// (exclude neutral family member)
			try{
				DieColor dieColor = DieColor.valueOf(color.name());
				Die die = spaceDie.getDie(dieColor);
				die.addObserver(familyMember);
			}
			catch (IllegalArgumentException ignored) {}

		}
	}

	public void setInitialResources(List<Resource> resources) {
		for(Resource resource: resources) {
			setResourceValue(resource.getType(), resource.getValue());
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
		return resources.stream().allMatch(resource -> resource == null || (resource.getValue() < this.resources.get(resource.getType()).getValue()));
	}

	public boolean satisfiesCardRequirements(Card c, List<Resource> discountedRequirements){
		if(c instanceof CardDevelopment){
			CardDevelopment card = (CardDevelopment) c;
			if(card instanceof CardVenture && ((CardVenture) card).hasChoice() && hasResources(((CardVenture)card).getMilitaryExchange().getCost()))
				return true;
		}
		return hasResources(discountedRequirements);
	}


	private boolean hasEnoughCards(CardType type, int quantity){
		return personalBoard.getCards(type).size() >= quantity;
	}

	private boolean canPlayLeaderCard(LeaderCard card){
		if(!hasResources(card.getRequirements()))
			return false;
		if(card.isAnyCard() && !hasEnoughCards(card.getNumOfRequiredCards()))
			return false;
		else
			for(CardType type : card.getCardRequirements().keySet()){
				if(!hasEnoughCards(type, card.getCardRequirements().get(type)))
					return false;
		}
		return true;
	}

	private boolean hasEnoughCards(int numOfRequiredCards) {
		for(CardType type : CardType.values())
			if(personalBoard.getCards(type).size() < numOfRequiredCards)
				return false;
		return true;
	}

	public List<LeaderCard> getPlayableLeaderCards(){
		List<LeaderCard> cards = new ArrayList<>();
		for(LeaderCard card : personalBoard.getLeaderCardsSpace().getCards())
			if(!card.isPlayed() && canPlayLeaderCard(card))
				cards.add(card);
		return cards;
	}

	public List<LeaderCard> getAvailableLeaderCards(){
		List<LeaderCard> cards = new ArrayList<>();
		for(LeaderCard card : personalBoard.getLeaderCardsSpace().getCards())
			if(card.isPlayed() && !card.isPermanent() && !card.isActivated())
				cards.add(card);
		return cards;
	}

	public List<LeaderCard> getNotPlayedLeaderCards() {
		List<LeaderCard> cards = new ArrayList<>();
		for(LeaderCard card : personalBoard.getLeaderCardsSpace().getCards())
			if(!card.isPlayed())
				cards.add(card);
		return cards;
	}
	
	public Map<ResourceType, Resource> getResources() {
		return resources;
	}

	public void resetFamilyMembers() {
		for(FamilyMember familyMember: familymembers.values()) {
			familyMember.setBusy(false);
			familyMember.setFriendly(false);
		}
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

	public synchronized boolean isDisconnected() {
		return disconnected;
	}

	public synchronized void setDisconnected(boolean disconnected) {
		this.disconnected = disconnected;
	}

	public synchronized boolean isExcluded() {
		return excluded;
	}

	public synchronized void setExcluded(boolean excluded) {
		this.excluded = excluded;
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

	public String printLeaderCards() {
		StringBuilder sb = new StringBuilder();
		for(LeaderCard card : personalBoard.getLeaderCardsSpace().getCards())
			sb.append(" - " + card).append(System.getProperty("line.separator"));
		return sb.toString();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Player)) return false;

		Player player = (Player) o;

		return playerColor == player.playerColor || name.toLowerCase().equals(player.getName().toLowerCase());
	}

	@Override
	public int hashCode() {
		int number =  name.toLowerCase().hashCode();
		return number;
	}
}
