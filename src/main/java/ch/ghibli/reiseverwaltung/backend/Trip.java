package ch.ghibli.reiseverwaltung.backend;

import ch.ghibli.reiseverwaltung.backend.utils.Indexable;
import ch.ghibli.reiseverwaltung.backend.utils.Json;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;

public class Trip extends Indexable {
    public ArrayList<Locality> localities;
    public ArrayList<Country> countries;

    public void save(String id){
        JSONObject object = new JSONObject();
        object.put("localities", getIds(localities));
        object.put("countries", getIds(countries));
        Json.saveToJson(object, "data/trips/" + id + "/" + this.id + ".json");
    }

    public static ArrayList<String> getIds(ArrayList target){
        ArrayList<String> ids = new ArrayList<>();
        target.forEach(indexable -> {
            ids.add(((Indexable)indexable).getId());
        });
        return ids;
    }

    public static Trip createFromJson(JSONObject object, String id){
        Trip instance = new Trip();
        instance.id = id;
        for (Object o : (JSONArray) object.get("localities")) {
            instance.localities.add(Data.getLocalityById((String) o));
        }
        for (Object o : (JSONArray) object.get("countries")){
            instance.countries.add(Data.getCountryById((String) o));
        }
        return instance;
    }
}
