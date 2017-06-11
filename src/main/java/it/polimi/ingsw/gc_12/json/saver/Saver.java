package it.polimi.ingsw.gc_12.json.saver;


import com.google.gson.ExclusionStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.json.ManageJsonFile;
import it.polimi.ingsw.gc_12.json.TypeAdapter;

import java.lang.reflect.Type;

public abstract class Saver<C> {
	protected String filename;
	protected Match match;
	//Standard GSON line of code to get the type of the List
	protected Type type = new TypeToken<C>() {}.getType();

	public Saver(String filename){
		this.filename = filename;

	}

	public void save(C content, ExclusionStrategy exclusionStrategy){
		GsonBuilder gsonBuiler = TypeAdapter.create();
		if(exclusionStrategy != null)
			gsonBuiler.setExclusionStrategies(exclusionStrategy);
		Gson gson = gsonBuiler.create();
		String gsonContent = gson.toJson(content, type);
		ManageJsonFile manageJsonFile=new ManageJsonFile();
		manageJsonFile.toJsonFile(gsonContent, filename);
	}

	public abstract void create();

}
