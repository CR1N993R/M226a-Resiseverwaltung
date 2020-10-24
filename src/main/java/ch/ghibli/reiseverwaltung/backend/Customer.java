package ch.ghibli.reiseverwaltung.backend;

import ch.ghibli.reiseverwaltung.backend.utils.Indexable;
import ch.ghibli.reiseverwaltung.backend.utils.Json;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.util.ArrayList;

public class Customer extends Indexable {
    private String name;
    private String lastname;
    public ArrayList<Trip> trips = new ArrayList<>();

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public void save(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", name);
        jsonObject.put("lastname", lastname);
        Json.saveToJson(jsonObject, "data/customer/" + id + ".json");
        saveTrips();
    }

    public void saveTrips(){
        for (Trip trip : trips) {
            trip.save(id);
        }
    }

    public Customer(String name, String lastname) {
        this.name = name;
        this.lastname = lastname;
    }

    public static Customer createFromJson(JSONObject object, String id){
        Customer instance = new Customer((String) object.get("name"),(String) object.get("lastname"));
        instance.id = id;
        instance.createTrips();
        return instance;
    }

    public void createTrips(){
        File file = new File("data/customers/" + id);
        for (File listFile : file.listFiles()) {
            Json.checkPath(listFile);
            String id = file.getName().replace(".json", "");
            trips.add(Trip.createFromJson(Json.loadFromJSON(listFile.getPath()), id));
        }
    }
}
