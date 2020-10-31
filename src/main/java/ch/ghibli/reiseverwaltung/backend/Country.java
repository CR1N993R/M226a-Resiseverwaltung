package ch.ghibli.reiseverwaltung.backend;

import ch.ghibli.reiseverwaltung.backend.utils.Indexable;
import ch.ghibli.reiseverwaltung.backend.utils.Json;
import org.json.simple.JSONObject;

import java.io.File;
import java.util.ArrayList;

/**
 * This Class contains the country information and the cities of this country
 *
 * @author Cedric Ringger
 * @author Miki Ujihara
 * @version 1.0
 */
public class Country extends Indexable {
    public String name;
    public ArrayList<City> cities = new ArrayList<>();

    /**
     * Creates a JSONObject from this object and saves it in a Jsonfile
     */
    public void save(){
        for (City city : cities) {
            city.save(id);
        }
        JSONObject object = new JSONObject();
        object.put("name", name);
        Json.saveToJson(object, "data/countries/" + id + ".json");
    }

    /**
     * Creates cities from Json
     */
    private void createCities(){
        File file = new File("data/cities/" + id);
        Json.checkPath(file);
        for (File listFile : file.listFiles()) {
            String id = listFile.getName().replace(".json", "");
            cities.add(City.creatFromJson(Json.loadFromJSON(listFile.getPath()), id));
        }
    }

    /**
     * Creates an instance of this object and applies the data from the jsonObject
     * @param object The JSONObject for creating the instance
     * @param id The Filename of the file which is used as the unique id
     * @return Returns a new Country instance with the JSON data applied
     */
    public static Country createFromJson(JSONObject object, String id){
        Country instance = new Country();
        instance.name = (String) object.get("name");
        instance.id = id;
        instance.createCities();
        return instance;
    }
}
