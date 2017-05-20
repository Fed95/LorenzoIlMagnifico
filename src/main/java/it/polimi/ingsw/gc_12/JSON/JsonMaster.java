package it.polimi.ingsw.gc_12.JSON;


public abstract class JsonMaster {
	private String filename;
	public JsonMaster(String filename){
		this.filename=filename;
	}
	public String getFilename(){
		return filename;
	}
}
