package ch.ghibli.reiseverwaltung.backend;

import ch.ghibli.reiseverwaltung.backend.utils.Indexable;
import ch.ghibli.reiseverwaltung.backend.utils.Json;
import org.json.simple.JSONObject;

import java.io.File;
import java.util.ArrayList;

public class Country extends Indexable {
    private String name;
    public ArrayList<Locality> localities;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void save(){
        for (Locality locality : localities) {
            locality.save(id);
        }
        JSONObject object = new JSONObject();
        object.put("name", name);
        Json.saveToJson(object, "data/countries/" + id + ".json");
    }

    public void createLocalities(){
        File file = new File("data/localities/" + id);
        Json.checkPath(file);
        for (File listFile : file.listFiles()) {
            String id = file.getName().replace(".json", "");
            localities.add(Locality.creatFromJson(Json.loadFromJSON(listFile.getPath()), id));
        }
    }

    public static Country createFromJson(JSONObject object, String id){
        Country instance = new Country();
        instance.name = (String) object.get("name");
        instance.id = id;
        instance.createLocalities();
        return instance;
    }
}
