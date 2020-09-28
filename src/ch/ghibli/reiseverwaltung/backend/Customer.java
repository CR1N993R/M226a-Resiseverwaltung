package ch.ghibli.reiseverwaltung.backend;

import ch.ghibli.reiseverwaltung.backend.utils.Indexable;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.ArrayList;

public class Customer extends Indexable {
    private String name;
    private String lastname;
    public ArrayList<Trip> trips;

    public Customer(String name, String lastname){
        this.name = name;
        this.lastname = lastname;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public void save(){

    }

    public static Customer createFromJson(JSONObject object){
        return new Customer((String) object.get("name"), (String) object.get("lastname"));
    }
}
