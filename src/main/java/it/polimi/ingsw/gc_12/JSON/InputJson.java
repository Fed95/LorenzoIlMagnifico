package it.polimi.ingsw.gc_12.JSON;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import it.polimi.ingsw.gc_12.card.Card;

public class InputJson {
	public String fromFile(String filename){
		FileInputStream readFromFile;
		File file;
		int read;
		String jsonread="";
		try{
			file = new File(filename+".json");
			readFromFile= new FileInputStream(file);
			while((read=readFromFile.read())!=-1){
				jsonread=jsonread+(char) read;
			}
			System.out.println(jsonread);
			readFromFile.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonread;
	}
	public List<Card> getCards(String json){
		Gson jsonobj=new Gson();
		Type listType = new TypeToken<List<Card>>() {}.getType();
		List<Card> cards=jsonobj.fromJson(json, listType);
		System.out.println(cards);
		return cards;
	}
}
