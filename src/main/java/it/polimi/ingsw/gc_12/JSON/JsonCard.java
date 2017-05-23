package it.polimi.ingsw.gc_12.JSON;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;

import it.polimi.ingsw.gc_12.card.Card;
import it.polimi.ingsw.gc_12.effect.*;
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
		
		final RuntimeTypeAdapterFactory<Resource> typeFactory = RuntimeTypeAdapterFactory  
		        .of(Resource.class, "type") // Here you specify which is the parent class and what field particularizes the child class.
		        .registerSubtype(Wood.class, "WOOD") // if the flag equals the class name, you can skip the second parameter. This is only necessary, when the "type" field does not equal the class name.
		        .registerSubtype(Stone.class, "STONE");
		final RuntimeTypeAdapterFactory<Effect> typeFactory2 = RuntimeTypeAdapterFactory  
		        .of(Effect.class, "type") // Here you specify which is the parent class and what field particularizes the child class.
		        .registerSubtype(EffectChangeResource.class, "EFFECT_CHANGE_RESOURCE")
		        .registerSubtype(EffectChangeFamilyMemberValue.class, "EFFECT_CHANGE_FAMILY_MEMBER_VALUE"); // if the flag equals the class name, you can skip the second parameter. This is only necessary, when the "type" field does not equal the class name.
		    
		Gson gson = new GsonBuilder().registerTypeAdapterFactory(typeFactory).registerTypeAdapterFactory(typeFactory2).create();
		
		List<Card> cards=gson.fromJson(json, listCardType);

		//List<Card> cards=jsonobj.fromJson(json, listCardType);
		System.out.println(cards.get(0).getRequirements().get(0).getClass());
		System.out.println(cards.get(0).getEffects().get(0).getClass());

		return cards;
	}
	
}
