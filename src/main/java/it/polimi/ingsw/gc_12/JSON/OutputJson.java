package it.polimi.ingsw.gc_12.JSON;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.google.gson.Gson;
import it.polimi.ingsw.gc_12.card.*;
import it.polimi.ingsw.gc_12.effect.Effect;
import it.polimi.ingsw.gc_12.resource.*;

public class OutputJson {
	/*classe che chiede che tipo di oggetto vuoi creare e ti chiede di conseguenza i valori. chiama poi i metodi di json creator*/
	/*public void create(){
		//ask what do you want to creeate and use right method
		int choice;
		Scanner scan = new Scanner(System.in);
        while(true){
			System.out.println("What do you want to create?");
			System.out.println("1 Cards \n 2 Excommunication Tiles \n 3 Resource \n 4 Exit");
			choice=scan.nextInt();
			scan.nextLine();
			switch(choice){
				case 1 :
					//chiama funzione create card
					System.out.println("Insert the name of the Card");
					String name=scan.nextLine();
					AskResource();
					break;
					
			}
			if(choice==4){
				break;
			}
        }
        scan.close();
	}*/
	public void createCards(List<Card> cards){
		//TODO change to array and create the card in the main
		//Card card=new Card(id, name, requirements, effects);
		Gson gsonobj=new Gson();
		String gsoncard=gsonobj.toJson(cards);
		toFile(gsoncard,"card");
	}
	private void toFile(String gsonstring,String filename){
		FileOutputStream writeOnFile;
		File file;
		try {
			file = new File(filename+".json");
			writeOnFile = new FileOutputStream(file);

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
			  writeOnFile.write(gsonstring.getBytes());
			  writeOnFile.flush();
			  writeOnFile.close();
		} catch (Exception e) {
			  e.printStackTrace();
			}
		System.out.println("File added");
	}
	/*public static void main(String[] args) {
		int choice;
		int counter;//prendere ultimo id carta creato
		Gson g =new Gson();
		List<Resource> requirements=new ArrayList<>();
		Card prova=new Card(1,"Lorry",new ArrayList<>(),new ArrayList<>() );
		String ex=g.toJson(prova);
		
		System.out.println(ex);
		Card prova1=g.fromJson(ex,Card.class);
		System.out.println(prova1.getName());
        Scanner scan = new Scanner(System.in);
        while(true){
			System.out.println("What do you want to create?");
			System.out.println("1 Cards \n 2 Excommunication Tiles \n 3 Resource \n 4 Exit");
			choice=scan.nextInt();
			scan.nextLine();
			switch(choice){
				case 1 :
					//chiama funzione create card
					System.out.println("Insert the name of the Card");
					String name=scan.nextLine();
					AskResource();
					break;
					
			}
			if(choice==4){
				break;
			}
        }
        scan.close();
	}	*/
	private List<Resource> AskResource(){
		Resource resource=null;
		int value=0;
        Scanner scan1 = new Scanner(System.in);
		List<Resource> requirements=new ArrayList<>();
		while(true){
			System.out.println("Insert requirements");
			System.out.println(" 1 FaithPoint \n 2 MilitaryPoint \n 3 VictoryPoints \n 4 Servants \n 5 Money \n 6 Stone \n 7 Wood \n 8 Exit create resource tool");
			int resourcechoice=scan1.nextInt();
			System.out.println(resourcechoice);
			scan1.nextLine();
			switch(resourcechoice){
				case 1:
					System.out.println("Insert value of Faith Points");
					value=scan1.nextInt();
					scan1.nextLine();
					resource=new FaithPoint(value);
					break;

				case 2:
					System.out.println("Insert value of Military Points");
					value=scan1.nextInt();
					scan1.nextLine();
					resource=new MilitaryPoint(value);
					break;

				case 3:
					System.out.println("Insert value of Victory Points");
					value=scan1.nextInt();
					scan1.nextLine();
					resource=new VictoryPoint(value);
					break;

				case 4:
					System.out.println("Insert value of Servants");
					value=scan1.nextInt();
					scan1.nextLine();
					resource=new Servant(value);
					break;

				case 5:
					System.out.println("Insert value of Money");
					value=scan1.nextInt();
					scan1.nextLine();
					resource=new Money(value);
					break;
				case 6:
					System.out.println("Insert value of Stone");
					value=scan1.nextInt();
					scan1.nextLine();
					resource=new Stone(value);
					break;
				case 7:
					System.out.println("Insert value of Wood");
					value=scan1.nextInt();
					scan1.nextLine();
					resource=new Wood(value);
					break;
				case 8:				
					break;
			}
			if(resourcechoice==8){
				break;
			}
			requirements.add(resource);
			
		}
		return requirements;
	}
}
