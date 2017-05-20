package it.polimi.ingsw.gc_12.JSON;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import it.polimi.ingsw.gc_12.card.Card;

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
	public List<Card> getCards(JsonMaster jsonobjmaster){
		Gson jsonobj=new Gson();
		//Standard gson line of code for get the type of the List
		Type listType = new TypeToken<List<Card>>() {}.getType();
		ManageJsonFile manageJsonFile=new ManageJsonFile();
		String json=manageJsonFile.fromJsonFile(this);
		List<Card> cards=jsonobj.fromJson(json, listType);
		System.out.println(cards);
		return cards;
	}
	
}
