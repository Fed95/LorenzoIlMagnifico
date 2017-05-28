package it.polimi.ingsw.gc_12.JSON.loader;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.gc_12.JSON.ManageJsonFile;
import it.polimi.ingsw.gc_12.JSON.TypeAdapter;
import it.polimi.ingsw.gc_12.card.CardExclusionStrategy;

import java.lang.reflect.Type;

public abstract class Loader<C> {
	protected String filename;
	//Standard GSON line of code to get the type of the List
	protected Type type = new TypeToken<C>() {}.getType();

	public Loader(String filename){
		this.filename = filename;
	}

	public C get(){
		ManageJsonFile manageJsonFile=new ManageJsonFile();
		String json = manageJsonFile.fromJsonFile(filename);
		GsonBuilder gsonBuiler = TypeAdapter.create();
		Gson gson = gsonBuiler.setExclusionStrategies(new CardExclusionStrategy()).create();
		return gson.fromJson(json, type);
	}
}
