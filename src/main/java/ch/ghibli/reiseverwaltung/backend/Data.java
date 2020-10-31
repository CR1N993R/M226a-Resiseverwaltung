package ch.ghibli.reiseverwaltung.backend;

import ch.ghibli.reiseverwaltung.backend.utils.Json;
import ch.ghibli.reiseverwaltung.backend.utils.ObjectNotFoundException;

import java.io.File;
import java.util.ArrayList;

/**
 * This Class contains all data and manages it
 *
 * @author Cedric Ringger
 * @author Miki Ujihara
 * @version 1.0
 */
public abstract class Data {
    private static final ArrayList<Customer> customers = new ArrayList<>();
    private static final ArrayList<Country> countries = new ArrayList<>();

    /**
     * Creates all objects from JSON
     */
    public static void createFromJson(){
        File file = new File("data/countries");
        Json.checkPath(file);
        for (File listFile : file.listFiles()) {
            countries.add(Country.createFromJson(Json.loadFromJSON(listFile.getPath()), listFile.getName().replace(".json","")));
        }
        file = new File("data/customers");
        Json.checkPath(file);
        for (File listFile : file.listFiles()) {
            customers.add(Customer.createFromJson(Json.loadFromJSON(listFile.getPath()), listFile.getName().replace(".json","")));
        }
    }

    /**
     * Searches for country by id
     * @param id Provides the id of the object to be found
     * @return returns the found object or null
     */
    public static Country getCountryById(String id) throws ObjectNotFoundException{
        for (Country country : countries) {
            if (country.getId().equals(id)){
                return country;
            }
        }
        throw new ObjectNotFoundException("Object " + id + " not found");
    }

    /**
     * Searches for City by id
     * @param id Provides the id of the object to be found
     * @return returns the found object or null
     */
    public static City getCityById(String id) throws ObjectNotFoundException{
        for (Country country : countries) {
            for (City city : country.cities) {
                if (city.getId().equals(id)){
                    return city;
                }
            }
        }
        throw new ObjectNotFoundException("Object " + id + " not found");
    }

    /**
     * Searches for Customer by id
     * @param id Provides the id of the object to be found
     * @return returns the found object or null
     */
    public static Customer getCustomerById(String id) throws ObjectNotFoundException{
        for (Customer customer : customers) {
            if (customer.getId().equals(id)){
                return customer;
            }
        }
        throw new ObjectNotFoundException("Object " + id + " not found");
    }

    /**
     * Searches for Trip by id
     * @param id Provides the id of the object to be found
     * @return returns the found object or null
     */
    public static Trip getTripById(String id) throws ObjectNotFoundException{
        for (Customer customer : customers) {
            for (Trip trip : customer.trips) {
                if (trip.getId().equals(id)){
                    return trip;
                }
            }
        }
        throw new ObjectNotFoundException("Object " + id + " not found");
    }

    public static ArrayList<Country> getCountries() {
        return countries;
    }

    public static ArrayList<Customer> getCustomers() {
        return customers;
    }

    /**
     * Remove the country from the arraylist deletes associated files and trips
     * @param country The country to be deleted
     */
    public static void removeCountry(Country country){
        if (countries.remove(country)){
            if (new File("data/countries/" + country.getId() + ".json").delete()){
                Json.deleteRecursive(new File("data/cities/" + country.getId()));
                removeTripsWithCountry(country);
            }
        }
    }

    /**
     * Remove associated Trips
     * @param country The country to be deleted
     */
    private static void removeTripsWithCountry(Country country){
        for (Customer customer : customers) {
            ArrayList<Trip> arrayList = new ArrayList<>();
            for (Trip trip : customer.trips) {
                if (trip.countries.contains(country)){
                    arrayList.add(trip);
                }
                for (City city : country.cities){
                    if (trip.cities.contains(city)){
                        arrayList.add(trip);
                    }
                }
            }
            for (Trip trip : arrayList) {
                removeTrip(customer,trip);
            }
        }
    }

    /**
     * Removes city and files associated
     * @param country For file name
     * @param city City to be deleted
     */
    public static void removeCity(Country country, City city){
        if (country.cities.remove(city)) {
            if (new File("data/cities/" + country.getId() + "/" + city.getId() + ".json").delete()) {
                for (Customer customer : customers) {
                    ArrayList<Trip> trips = new ArrayList<>();
                    for (Trip trip : customer.trips) {
                        if (trip.cities.contains(city)) {
                            trips.add(trip);
                        }
                    }
                    for (Trip trip : trips) {
                        removeTrip(customer,trip);
                    }
                }
            }
        }
    }

    /**
     * Removes Trip and files associated
     * @param customer For file name
     * @param trip to be deleted
     */
    public static void removeTrip(Customer customer, Trip trip){
        if (customer.trips.remove(trip)){
            new File("data/trips/" + customer.getId() + "/" + trip.getId() + ".json").delete();
        }
    }

    /**
     * Removes Customer and files associated
     * @param customer to be deleted
     */
    public static void removeCustomer(Customer customer){
        if (customers.remove(customer)){
            if (new File("data/customers/" + customer.getId() + ".json").delete()){
                Json.deleteRecursive(new File("data/trips/" + customer.getId()));
            }
        }
    }
}
