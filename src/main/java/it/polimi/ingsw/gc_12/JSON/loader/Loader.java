package it.polimi.ingsw.gc_12.JSON.loader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.polimi.ingsw.gc_12.JSON.ManageJsonFile;
import it.polimi.ingsw.gc_12.JSON.TypeAdapter;

import java.lang.reflect.Type;

public abstract class Loader<C> {
	protected String filename;


	public Loader(String filename){
		this.filename = filename;
	}

	protected abstract Type getType();

	public C get(){
		ManageJsonFile manageJsonFile=new ManageJsonFile();
		String json = manageJsonFile.fromJsonFile(filename);
		GsonBuilder gsonBuiler = TypeAdapter.create();
		Gson gson = gsonBuiler.create();
		return gson.fromJson(json, getType());
	}
}
