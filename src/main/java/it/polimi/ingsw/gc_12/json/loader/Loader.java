package it.polimi.ingsw.gc_12.json.loader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.polimi.ingsw.gc_12.GameMode;
import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.json.ManageJsonFile;
import it.polimi.ingsw.gc_12.json.TypeAdapter;

import java.lang.reflect.Type;

public abstract class Loader<C> {
	protected String filename;


	public Loader(String filename){
		this.filename = filename;
	}

	protected abstract Type getType();

	protected abstract C adapt(C content, Match match);

	public C get(Match match){
		ManageJsonFile manageJsonFile = new ManageJsonFile();
		String json = manageJsonFile.fromJsonFile(filename);
		GsonBuilder gsonBuiler = TypeAdapter.create();
		Gson gson = gsonBuiler.create();
		return adapt(gson.fromJson(json, getType()), match);
	}
}
