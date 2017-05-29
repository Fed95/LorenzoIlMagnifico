package it.polimi.ingsw.gc_12.exceptions;

/**
 * Created by feder on 2017-05-29.
 */
public class NotEnoughResourcesException extends Exception {
    private String message;

    public NotEnoughResourcesException(String message){
        super(message);
    }
    public NotEnoughResourcesException(){
        super();
    }
}
