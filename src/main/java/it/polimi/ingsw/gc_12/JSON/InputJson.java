package it.polimi.ingsw.gc_12.JSON;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.polimi.ingsw.gc_12.card.*;
import it.polimi.ingsw.gc_12.resource.*;

public class InputJson {
	/*classe che chiede che tipo di oggetto vuoi creare e ti chiede di conseguenza i valori. chiama poi i metodi di json creator*/
	public static void main(String[] args) {
		Gson g =new Gson();
		Resource res1=new Money(5); 
		List<Resource> requirements=new ArrayList<>();
		Card prova=new Card(1,"Lorry",new ArrayList<>(),new ArrayList<>() );
		String ex=g.toJson(prova);
		
		System.out.println("ds");
	}	
}
