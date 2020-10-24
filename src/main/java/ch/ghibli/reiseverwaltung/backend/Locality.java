package ch.ghibli.reiseverwaltung.backend;

import ch.ghibli.reiseverwaltung.backend.utils.Indexable;
import ch.ghibli.reiseverwaltung.backend.utils.Json;
import org.json.simple.JSONObject;

public class Locality extends Indexable {
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void save(String id){
        JSONObject object = new JSONObject();
        object.put("name", name);
        Json.saveToJson(object, "data/localities/" + id + "/" + this.id + ".json");
    }

    public static Locality creatFromJson(JSONObject object, String id){
        Locality locality = new Locality();
        locality.name = (String) object.get("name");
        locality.id = id;
        return locality;
    }
}
