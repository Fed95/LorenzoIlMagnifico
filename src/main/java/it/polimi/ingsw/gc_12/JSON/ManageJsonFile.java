package it.polimi.ingsw.gc_12.JSON;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class ManageJsonFile {
	public void toJsonFile(String gsonstring, JsonMaster jsonObj){
		FileOutputStream writeOnFile;
		File file;
		try {
			file = new File(jsonObj.getFilename() + ".json");

			// If file already exists, add new data
			if (file.exists()) {
				ManageJsonFile manageObj = new ManageJsonFile();
				String existfile = manageObj.fromJsonFile(jsonObj); // Open existing file
				existfile = existfile.substring(1, existfile.length() - 1); // Remove first and last graph
				gsonstring = gsonstring.substring(0, 1) + existfile + "," + gsonstring.substring(1, gsonstring.length()); // Substitute old file with the new
			}
			writeOnFile = new FileOutputStream(file);

			// if file doesn't exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
			  writeOnFile.write(gsonstring.getBytes());
			  writeOnFile.flush();
			  writeOnFile.close();
		} catch (Exception e) {
			  e.printStackTrace();
		}
		System.out.println("File " + jsonObj.getFilename() + ".json created");
	}

	public String fromJsonFile(JsonMaster jsonobj){
		FileInputStream readFromFile;
		File file;
		int read;
		String jsonread = "";
		try{
			file = new File(jsonobj.getFilename()+".json");
			readFromFile = new FileInputStream(file);
			while((read = readFromFile.read()) != -1){
				jsonread = jsonread + (char) read;
			}
			System.out.println(jsonread);
			readFromFile.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonread;
	}
}
