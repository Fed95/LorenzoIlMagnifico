package it.polimi.ingsw.gc_12.JSON;

import com.google.gson.Gson;
import it.polimi.ingsw.gc_12.TowerSet;

/**
 * Created by marco on 27/05/2017.
 */
public class LoaderMarket extends JsonMaster {

	public LoaderMarket (String filename){
		super(filename);
	}

	/*public void create(){
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
	}*/
}
