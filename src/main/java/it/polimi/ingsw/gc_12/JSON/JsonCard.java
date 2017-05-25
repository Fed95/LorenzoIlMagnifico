package it.polimi.ingsw.gc_12.JSON;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;

import it.polimi.ingsw.gc_12.*;
import it.polimi.ingsw.gc_12.card.*;
import it.polimi.ingsw.gc_12.effect.*;
import it.polimi.ingsw.gc_12.event.Event;
import it.polimi.ingsw.gc_12.event.EventPlaceFamilyMember;
import it.polimi.ingsw.gc_12.resource.Resource;
import it.polimi.ingsw.gc_12.resource.*;

public class JsonCard extends JsonMaster {
	public JsonCard(String filename){
		super(filename);
	}
	public void createCards(List<Card> cards){
		Gson gsonobj=new Gson();
		String gsoncard=gsonobj.toJson(cards);		
		ManageJsonFile manageJsonFile=new ManageJsonFile();
		manageJsonFile.tojsonFile(gsoncard, this);
	}
	public List<Card> getCards(){
		Gson jsonobj=new Gson();
		//Standard gson line of code for get the type of the List
		Type listCardType = new TypeToken<List<Card>>() {}.getType();
		ManageJsonFile manageJsonFile=new ManageJsonFile();
		String json=manageJsonFile.fromJsonFile(this);
		//GsonBuilder gsonBldr = new GsonBuilder();
		//gsonBldr.registerTypeAdapter(listCardType,new CardPersonalDeserializer());
		final RuntimeTypeAdapterFactory<EffectProvider> typeFactory6 = RuntimeTypeAdapterFactory  
		        .of(EffectProvider.class, "listEffectProvider")
		        .registerSubtype(Card.class, "CARD")
		        .registerSubtype(Occupiable.class, "OCCUPIABLE");
		
		final RuntimeTypeAdapterFactory<Occupiable> typeFactory5 = RuntimeTypeAdapterFactory  
		        .of(Occupiable.class, "listOccupiable")
		        .registerSubtype(SpaceMarket.class, "SPACE_MARKET")
		        .registerSubtype(TowerFloor.class, "TOWER_FLOOR")
		        .registerSubtype(SpaceWork.class, "SPACE_WORK");//council palace
		
		final RuntimeTypeAdapterFactory<Card> typeFactory = RuntimeTypeAdapterFactory  
		        .of(Card.class, "type")
		        .registerSubtype(CardDevelopment.class, "DEVELOPMENT");
		        //registerSubtype(CardLeader.class, "LEADER");
		
		final RuntimeTypeAdapterFactory<CardDevelopment> typeFactory1 = RuntimeTypeAdapterFactory  
		        .of(CardDevelopment.class, "cardDevelopmentType")
		        .registerSubtype(CardBuilding.class, "BUILDING");
		
		final RuntimeTypeAdapterFactory<Resource> typeFactory2 = RuntimeTypeAdapterFactory  
		        .of(Resource.class, "type") 
		        .registerSubtype(Wood.class, "WOOD") 
		        .registerSubtype(Stone.class, "STONE");
		final RuntimeTypeAdapterFactory<Effect> typeFactory3 = RuntimeTypeAdapterFactory  
		        .of(Effect.class, "type") // Here you specify which is the parent class and what field particularizes the child class.
		        .registerSubtype(EffectChangeResource.class, "EFFECT_CHANGE_RESOURCE")
		        .registerSubtype(EffectChangeFamilyMemberValue.class, "EFFECT_CHANGE_FAMILY_MEMBER_VALUE"); // if the flag equals the class name, you can skip the second parameter. This is only necessary, when the "type" field does not equal the class name.
		final RuntimeTypeAdapterFactory<Event> typeFactory4 = RuntimeTypeAdapterFactory  
		        .of(Event.class, "eventType")
		        .registerSubtype(EventPlaceFamilyMember.class, "EVENT_PLACE_FAMILY_MEMBER");
		
	
		
		
		Gson gson = new GsonBuilder().registerTypeAdapterFactory(typeFactory6).registerTypeAdapterFactory(typeFactory5).registerTypeAdapterFactory(typeFactory4).registerTypeAdapterFactory(typeFactory3).registerTypeAdapterFactory(typeFactory2).registerTypeAdapterFactory(typeFactory1).registerTypeAdapterFactory(typeFactory).create();
		
		List<Card> cards=gson.fromJson(json, listCardType);

		//List<Card> cards=jsonobj.fromJson(json, listCardType);
		System.out.println(cards.get(0).getRequirements().get(0).getClass());
		System.out.println(cards.get(0).getEffects().get(0).getClass());

		return cards;
	}
	
}
