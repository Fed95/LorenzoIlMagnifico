package it.polimi.ingsw.gc12.misc.json;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class ManageJsonFile {

	public void toJsonFile(String gsonString, String filename){
		FileOutputStream writeOnFile;
		File file;
		try {
			file = new File(filename + ".JSON");

			// If file already exists, add new data
			if (file.exists()) {
				ManageJsonFile manageObj = new ManageJsonFile();
				String existingFile = manageObj.fromJsonFile(filename); // Open existing file
				existingFile = existingFile.substring(1, existingFile.length() - 1); // Remove first and last graph
				gsonString = gsonString.substring(0, 1) + existingFile + "," + gsonString.substring(1, gsonString.length()); // Substitute old file with the new
			}
			writeOnFile = new FileOutputStream(file);

			// if file doesn't exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
			  writeOnFile.write(gsonString.getBytes());
			  writeOnFile.flush();
			  writeOnFile.close();
		} catch (Exception e) {
			  e.printStackTrace();
		}
		System.out.println("File " + filename + ".JSON created");
	}

	public String fromJsonFile(String filename){
		FileInputStream readFromFile;
		File file;
		int read;
		String jsonRead = "";
		try{
			file = new File(filename + ".JSON");
			readFromFile = new FileInputStream(file);
			while((read = readFromFile.read()) != -1){
				jsonRead = jsonRead + (char) read;
			}
			readFromFile.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonRead;
	}
}
