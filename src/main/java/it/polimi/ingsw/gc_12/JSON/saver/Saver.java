package it.polimi.ingsw.gc_12.JSON.saver;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.gc_12.JSON.ManageJsonFile;
import it.polimi.ingsw.gc_12.JSON.TypeAdapter;
import it.polimi.ingsw.gc_12.card.CardExclusionStrategy;

import java.lang.reflect.Type;

public abstract class Saver<C> {
	protected String filename;
	//Standard GSON line of code to get the type of the List
	protected Type type = new TypeToken<C>() {}.getType();

	public Saver(String filename){
		this.filename = filename;
	}

	public void save(C content){
		GsonBuilder gsonBuiler = TypeAdapter.create();
		Gson gson = gsonBuiler.setExclusionStrategies(new CardExclusionStrategy()).create();
		String gsonContent = gson.toJson(content, type);
		ManageJsonFile manageJsonFile=new ManageJsonFile();
		manageJsonFile.toJsonFile(gsonContent, filename);
	}
}
