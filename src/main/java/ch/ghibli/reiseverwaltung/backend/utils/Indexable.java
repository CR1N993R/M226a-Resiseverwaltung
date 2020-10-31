package ch.ghibli.reiseverwaltung.backend.utils;

import java.util.UUID;

/**
 * This class is for displaying all Trips and managing them
 *
 * @author Cedric Ringger
 * @author Miki Ujihara
 * @version 1.0
 */
public abstract class Indexable {
    protected String id = UUID.randomUUID().toString();

    /**
     * @return Returns the id
     */
    public String getId(){
        return id;
    }
}
