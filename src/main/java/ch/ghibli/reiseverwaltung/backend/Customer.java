package ch.ghibli.reiseverwaltung.backend;

import ch.ghibli.reiseverwaltung.backend.utils.Indexable;
import ch.ghibli.reiseverwaltung.backend.utils.Json;
import org.json.simple.JSONObject;

import java.io.File;
import java.util.ArrayList;

/**
 * This Class contains the customer information
 *
 * @author Cedric Ringger
 * @author Miki Ujihara
 * @version 1.0
 */
public class Customer extends Indexable {
    public String name;
    public String lastname;
    public ArrayList<Trip> trips = new ArrayList<>();

    /**
     * This Method converts the object to json and saves it
     */
    public void save(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", name);
        jsonObject.put("lastname", lastname);
        Json.saveToJson(jsonObject, "data/customers/" + id + ".json");
        saveTrips();
    }

    /**
     * This Method saves all trips
     */
    private void saveTrips(){
        for (Trip trip : trips) {
            trip.save(id);
        }
    }

    /**
     * This Method creates an instance from this class with the data from JSON
     * @param object The JSONObject for creating the instance
     * @param id The Filename of the file which is used as the unique id
     * @return Returns an instance of Customer
     */
    public static Customer createFromJson(JSONObject object, String id){
        Customer instance = new Customer();
        instance.name = (String) object.get("name");
        instance.lastname = (String) object.get("lastname");
        instance.id = id;
        instance.createTrips();
        return instance;
    }

    /**
     * Creates the trips from JSON
     */
    private void createTrips(){
        File file = new File("data/trips/" + id);
        Json.checkPath(file);
        for (File listFile : file.listFiles()) {
            Json.checkPath(listFile);
            String id = listFile.getName().replace(".json", "");
            trips.add(Trip.createFromJson(Json.loadFromJSON(listFile.getPath()), id));
        }
    }
}
