package ch.ghibli.reiseverwaltung.backend.utils;

/**
 * This is a Custom Exception for handling if an Object wasn't found in an array
 *
 * @author Cedric Ringger
 * @author Miki Ujihara
 * @version 1.0
 */
public class ObjectNotFoundException extends Exception{
    public ObjectNotFoundException(String error){
        super(error);
    }
}
