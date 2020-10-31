package ch.ghibli.reiseverwaltung.backend;

import ch.ghibli.reiseverwaltung.backend.utils.Indexable;
import ch.ghibli.reiseverwaltung.backend.utils.Json;
import ch.ghibli.reiseverwaltung.backend.utils.ObjectNotFoundException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * This is the trip class which contains all countries, cities and the date of the trip
 *
 * @author Cedric Ringger
 * @author Miki Ujihara
 * @version 1.0
 */
public class Trip extends Indexable {
    public ArrayList<City> cities = new ArrayList<>();
    public ArrayList<Country> countries = new ArrayList<>();
    public LocalDate start;

    /**
     * This function creates a json object and saves it
     * @param customerId is provided for the path were the file will be saved
     */
    public void save(String customerId){
        JSONObject object = new JSONObject();
        object.put("cities", getIds(cities));
        object.put("countries", getIds(countries));
        object.put("date", start.format(DateTimeFormatter.ofPattern("d.M.yyyy")));
        Json.saveToJson(object, "data/trips/" + customerId + "/" + this.id + ".json");
    }

    /**
     * gets all ids of object in a arraylist and returns them
     * @param target provides the arraylist to be converted
     * @return returns an arraylist with the ids
     */
    private static ArrayList<String> getIds(ArrayList target){
        ArrayList<String> ids = new ArrayList<>();
        target.forEach(indexable -> {
            ids.add(((Indexable)indexable).getId());
        });
        return ids;
    }

    /**
     * Takes a json object and returns a new Trip object
     * @param object Provides the data for creating the object
     * @param id Provides the file name which is the object id at the same time to prevent redundancies
     * @return returns a new Trip object with set data
     */
    public static Trip createFromJson(JSONObject object, String id){
        Trip instance = new Trip();
        instance.id = id;
        for (Object o : (JSONArray) object.get("cities")) {
            try {
                instance.cities.add(Data.getCityById((String) o));
            }catch (ObjectNotFoundException e){
                e.printStackTrace();
            }
        }
        for (Object o : (JSONArray) object.get("countries")){
            try {
                instance.countries.add(Data.getCountryById((String) o));
            } catch (ObjectNotFoundException e) {
                e.printStackTrace();
            }
        }
        instance.start = LocalDate.from(DateTimeFormatter.ofPattern("d.M.yyyy").parse((String) object.get("date")));
        return instance;
    }

    /**
     * Returns the formatted start date
     * @return returns the start date
     */
    public String getStart() {
        return start.format(DateTimeFormatter.ofPattern("d.M.yyyy"));
    }

    /**
     * Gets the name of the city as an observable arraylist
     * @return returns the observableList
     */
    public ObservableList<String> citiesToString(){
        ObservableList<String> list = FXCollections.observableArrayList();
        for (City city : cities) {
            list.add(city.name);
        }
        return list;
    }

    /**
     * Gets the name of the countries as an observable arraylist
     * @return returns the observableList
     */
    public ObservableList<String> countriesToString(){
        ObservableList<String> list = FXCollections.observableArrayList();
        for (Country country : countries) {
            list.add(country.name);
        }
        return list;
    }
}
