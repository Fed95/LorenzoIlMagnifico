package it.polimi.ingsw.gc_12.JSON;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.gc_12.TowerSet;
import it.polimi.ingsw.gc_12.card.Card;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by marco on 27/05/2017.
 */
public class LoaderTowerSet extends JsonMaster {

	private Type towerSetType = new TypeToken<TowerSet>() {}.getType();

	public LoaderTowerSet(String filename){
		super(filename);
	}

	public void create(){
		Gson gson = TypeAdapter.create();
		TowerSet towerSet = new TowerSet();
		String gsonCard = gson.toJson(towerSet, towerSetType);
		ManageJsonFile manageJsonFile=new ManageJsonFile();
		manageJsonFile.toJsonFile(gsonCard, this);
	}

	public TowerSet get(){
		ManageJsonFile manageJsonFile=new ManageJsonFile();
		String json = manageJsonFile.fromJsonFile(this);
		Gson gson = TypeAdapter.create();
		return gson.fromJson(json, towerSetType);
	}
}
