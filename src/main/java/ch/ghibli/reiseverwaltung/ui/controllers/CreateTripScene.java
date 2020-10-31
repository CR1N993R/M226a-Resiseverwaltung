package ch.ghibli.reiseverwaltung.ui.controllers;

import ch.ghibli.reiseverwaltung.backend.*;
import ch.ghibli.reiseverwaltung.backend.utils.ObjectNotFoundException;
import ch.ghibli.reiseverwaltung.ui.UiManager;
import javafx.scene.control.*;

/**
 * This class is the controller for the Create trip scene
 * The purpose of this class is managing Trips
 *
 * @author Cedric Ringger
 * @author Miki Ujihara
 * @version 1.0
 */
public class CreateTripScene extends ControllerBase{
    public MenuButton countries;
    public MenuButton cities;
    public DatePicker date;
    private Customer customer;
    private Trip trip;

    /**
     * Initializes the scene and loads the data
     *
     * @param customer Is used to return to the previous scene and for certain data
     * @param trip Is used to load the data needed
     */
    @Override
    public void init(Customer customer, Trip trip) {
        this.trip = trip;
        this.customer = customer;
        loadCountries();
        if (trip != null){
            date.setValue(trip.start);
        }
    }

    /**
     * Loads all the countries into the dropdown
     */
    public void loadCountries(){
        for (Country country : Data.getCountries()) {
            CheckBox checkBox = new CheckBox();
            checkBox.setId(country.getId());
            checkBox.setText(country.name);
            checkBox.setSelected(trip!=null&&trip.countries.contains(country));
            CustomMenuItem customMenuItem = new CustomMenuItem(checkBox);
            customMenuItem.setHideOnClick(false);
            countries.getItems().add(customMenuItem);
            loadCities(country);
        }
    }

    /**
     * Loads all the cities into the dropdown
     * @param country provides the country in which the cities are
     */
    public void loadCities(Country country){
        for (City city : country.cities) {
            CheckBox checkBox = new CheckBox(city.name);
            checkBox.setId(city.getId());
            checkBox.setSelected(trip!=null&&trip.cities.contains(city));
            CustomMenuItem customMenuItem = new CustomMenuItem(checkBox);
            customMenuItem.setHideOnClick(false);
            cities.getItems().add(customMenuItem);
        }
    }

    /**
     * Saves the Trip
     */
    public void save() {
        if (date.getValue() != null) {
            if (trip == null){
                trip = new Trip();
                customer.trips.add(trip);
            }
            trip.cities.clear();
            trip.countries.clear();
            trip.start = date.getValue();
            saveCities();
            saveCountries();
            trip.save(customer.getId());
            back();
        }
    }

    /**
     * Reads the selected cities from the dropdowns and adds them to the trip
     */
    private void saveCities(){
        for (MenuItem item : cities.getItems()) {
            CheckBox checkBox = (CheckBox)((CustomMenuItem) item).getContent();
            if (checkBox.isSelected()){
                try {
                    trip.cities.add(Data.getCityById(checkBox.getId()));
                } catch (ObjectNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Reads the selected countries from the dropdowns and adds them to the trip
     */
    private void saveCountries(){
        for (MenuItem item : countries.getItems()) {
            CheckBox checkBox = (CheckBox)((CustomMenuItem) item).getContent();
            if (checkBox.isSelected()){
                try {
                    trip.countries.add(Data.getCountryById(checkBox.getId()));
                } catch (ObjectNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * returns to the showCitiesScene
     */
    public void back() {
        UiManager.showTripsScene(customer);
    }
}
