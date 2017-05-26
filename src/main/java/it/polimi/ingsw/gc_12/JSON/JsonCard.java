package it.polimi.ingsw.gc_12.JSON;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;

import it.polimi.ingsw.gc_12.card.Card;
import it.polimi.ingsw.gc_12.*;
import it.polimi.ingsw.gc_12.card.CardBuilding;
import it.polimi.ingsw.gc_12.effect.*;
import it.polimi.ingsw.gc_12.event.Event;
import it.polimi.ingsw.gc_12.event.EventPlaceFamilyMember;
import it.polimi.ingsw.gc_12.resource.Resource;
import it.polimi.ingsw.gc_12.resource.*;

public class JsonCard extends JsonMaster {
	//Standard gson line of code to get the type of the List
	private Type listCardType = new TypeToken<List<Card>>() {}.getType();

	public JsonCard(String filename){
		super(filename);
	}

	private Gson buildGson() {
		final RuntimeTypeAdapterFactory<EffectProvider> factoryEffectProvider = RuntimeTypeAdapterFactory
				.of(EffectProvider.class, "effectProvider")
				.registerSubtype(Card.class, Card.class.getName())
				.registerSubtype(Occupiable.class, Occupiable.class.getName());

		final RuntimeTypeAdapterFactory<Occupiable> factoryOccupiable = RuntimeTypeAdapterFactory
				.of(Occupiable.class, "occupiable")
				.registerSubtype(SpaceMarket.class, SpaceMarket.class.getName())
				.registerSubtype(TowerFloor.class, TowerFloor.class.getName())
				.registerSubtype(SpaceWork.class, SpaceWork.class.getName());//council palace

		final RuntimeTypeAdapterFactory<Card> factoryCard = RuntimeTypeAdapterFactory
				.of(Card.class, "type")
				.registerSubtype(CardBuilding.class, CardBuilding.class.getName());
				//registerSubtype(CardLeader.class, "LEADER");

		final RuntimeTypeAdapterFactory<Resource> factoryResource = RuntimeTypeAdapterFactory
				.of(Resource.class, "type")
				.registerSubtype(Wood.class, Wood.class.getName())
				.registerSubtype(Stone.class, Stone.class.getName());

		final RuntimeTypeAdapterFactory<Effect> factoryEffect = RuntimeTypeAdapterFactory
				.of(Effect.class, "type") // Here you specify which is the parent class and what field particularizes the child class.
				.registerSubtype(EffectChangeResource.class, EffectChangeResource.class.getName())
				.registerSubtype(EffectChangeFamilyMemberValue.class, EffectChangeFamilyMemberValue.class.getName()); // if the flag equals the class name, you can skip the second parameter. This is only necessary, when the "type" field does not equal the class name.

		final RuntimeTypeAdapterFactory<Event> factoryEvent = RuntimeTypeAdapterFactory
				.of(Event.class, "eventType")
				.registerSubtype(EventPlaceFamilyMember.class, EventPlaceFamilyMember.class.getName());

		return new GsonBuilder()
				.registerTypeAdapterFactory(factoryEffectProvider)
				.registerTypeAdapterFactory(factoryOccupiable)
				.registerTypeAdapterFactory(factoryCard)
				.registerTypeAdapterFactory(factoryResource)
				.registerTypeAdapterFactory(factoryEffect)
				.registerTypeAdapterFactory(factoryEvent)
				.create();
	}

	public void createCards(List<Card> cards){
		Gson gson = buildGson();
		String gsonCard = gson.toJson(cards, listCardType);
		ManageJsonFile manageJsonFile=new ManageJsonFile();
		manageJsonFile.tojsonFile(gsonCard, this);
	}
	public List<Card> getCards(){
		ManageJsonFile manageJsonFile=new ManageJsonFile();
		String json = manageJsonFile.fromJsonFile(this);
		Gson gson = buildGson();
		return gson.fromJson(json, listCardType);
	}

}
