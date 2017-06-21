package it.polimi.ingsw.gc_12.json.loader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.json.TypeAdapter;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;

public abstract class Loader<C> {
	protected String filename;

	public Loader(String filename){
		this.filename = filename;
	}

	protected abstract Type getType();

	protected abstract C adapt(C content, Match match);

	public C get(Match match) {
		JsonReader reader = null;
		try {
			reader = new JsonReader(new FileReader(filename+".json"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		GsonBuilder gsonBuilder = TypeAdapter.create();
		Gson gson = gsonBuilder.create();
		return adapt(gson.fromJson(reader, getType()), match);
	}
}
