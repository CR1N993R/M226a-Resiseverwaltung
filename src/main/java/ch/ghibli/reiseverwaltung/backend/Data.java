package ch.ghibli.reiseverwaltung.backend;

import ch.ghibli.reiseverwaltung.backend.utils.Json;
import org.json.simple.JSONObject;

import java.io.File;
import java.util.ArrayList;

public abstract class Data {
    private static final ArrayList<Customer> customers = new ArrayList<>();
    private static final ArrayList<Country> countries = new ArrayList<>();

    public static void addCustomer(Customer customer){
        customers.add(customer);
    }

    public static void save(){
        for (Customer customer : customers) {
            customer.save();
        }
        for (Country country : countries) {
            country.save();
        }
    }

    public static void createFromJson(){
        File file = new File("data/countries");
        for (File listFile : file.listFiles()) {
            JSONObject o = Json.loadFromJSON(listFile.getPath());
        }
    }

    public static Country getCountryById(String id){
        for (Country country : countries) {
            if (country.getId().equals(id)){
                return country;
            }
        }
        return null;
    }

    public static Locality getLocalityById(String id){
        for (Country country : countries) {
            for (Locality locality : country.localities) {
                if (locality.getId().equals(id)){
                    return locality;
                }
            }
        }
        return null;
    }
}
