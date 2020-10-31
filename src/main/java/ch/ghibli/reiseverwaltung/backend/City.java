package ch.ghibli.reiseverwaltung.backend;

import ch.ghibli.reiseverwaltung.backend.utils.Indexable;
import ch.ghibli.reiseverwaltung.backend.utils.Json;
import org.json.simple.JSONObject;

/**
 * This Class contains the city name
 *
 * @author Cedric Ringger
 * @author Miki Ujihara
 * @version 1.0
 */
public class City extends Indexable {
    public String name;

    /**
     * This function convert this instance to JSON and saves it
     * @param id
     */
    public void save(String id){
        JSONObject object = new JSONObject();
        object.put("name", name);
        Json.saveToJson(object, "data/cities/" + id + "/" + this.id + ".json");
    }

    /**
     * Creates this object from JSON
     * @param object The JSONObject for creating the instance
     * @param id The Filename of the file which is used as the unique id
     * @return Returns a new Instance of city
     */
    public static City creatFromJson(JSONObject object, String id){
        City city = new City();
        city.name = (String) object.get("name");
        city.id = id;
        return city;
    }
}
