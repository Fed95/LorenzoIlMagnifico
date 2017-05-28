package it.polimi.ingsw.gc_12.JSON;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import it.polimi.ingsw.gc_12.card.*;

public class LoaderCard extends JsonMaster {
	//Standard gson line of code to get the type of the List
	private Type listCardType = new TypeToken<List<Card>>() {}.getType();

	public LoaderCard(String filename){
		super(filename);
	}

	public void createCards(List<Card> cards){
		Gson gson = TypeAdapter.create();
		String gsonCard = gson.toJson(cards, listCardType);
		ManageJsonFile manageJsonFile=new ManageJsonFile();
		manageJsonFile.toJsonFile(gsonCard, this);
	}

	public List<Card> getCards(){
		ManageJsonFile manageJsonFile=new ManageJsonFile();
		String json = manageJsonFile.fromJsonFile(this);
		Gson gson = TypeAdapter.create();
		return gson.fromJson(json, listCardType);
	}

}
