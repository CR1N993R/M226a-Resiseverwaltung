package ch.ghibli.reiseverwaltung.backend;

import javafx.collections.ObservableList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertSame;

/**
 * This Class is for testing Certain methods in the Trip class
 *
 * @author Cedric Ringger
 * @author Miki Ujihara
 * @version 1.0
 */
public class TripTest {

    /**
     * This test checks if the toString function works
     */
    @Test
    public void citiesToStringTest(){
        Trip trip = new Trip();
        City city = new City();
        city.name = "Test";
        trip.cities.add(city);
        ObservableList<String> trips = trip.citiesToString();
        assertSame(trips.size(), 1);
        assertSame(trips.get(0), "Test");
    }

    /**
     * This test checks if the toString function works
     */
    @Test
    public void countriesToStringTest(){
        Trip trip = new Trip();
        Country country = new Country();
        country.name = "Test";
        trip.countries.add(country);
        ObservableList<String> countries = trip.countriesToString();
        assertSame(countries.size(), 1);
        assertSame(countries.get(0), "Test");
    }

    /**
     * This test is for testing if the object can be successfully created from JSON
     */
    @Test
    public void createFromJsonTest(){
        Country country = new Country();
        Data.getCountries().add(country);
        City city = new City();
        country.cities.add(city);

        JSONObject object = new JSONObject();
        JSONArray jsonCountryArray = new JSONArray();
        jsonCountryArray.add(country.getId());
        JSONArray jsonCityArray = new JSONArray();
        jsonCityArray.add(city.getId());
        object.put("cities", jsonCityArray);
        object.put("countries", jsonCountryArray);
        object.put("date", "12.2.2020");
        Trip trip = Trip.createFromJson(object,"Test123");
        LocalDate date = LocalDate.from(DateTimeFormatter.ofPattern("d.M.yyyy").parse("12.2.2020"));

        assertSame(trip.cities.get(0), city);
        assertSame(trip.countries.get(0), country);
        assertSame(trip.getId(), "Test123");
        assertSame(trip.start.getDayOfMonth(), date.getDayOfMonth());
        assertSame(trip.start.getMonth(), date.getMonth());
        //assertSame(trip.start.getYear(), date.getYear());
    }
}