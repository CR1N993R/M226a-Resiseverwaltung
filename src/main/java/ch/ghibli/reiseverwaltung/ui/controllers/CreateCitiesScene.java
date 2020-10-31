package ch.ghibli.reiseverwaltung.ui.controllers;

import ch.ghibli.reiseverwaltung.backend.City;
import ch.ghibli.reiseverwaltung.backend.Country;
import ch.ghibli.reiseverwaltung.ui.UiManager;
import javafx.scene.control.TextField;

/**
 * This class is the UiController for the CreateCitiesScene
 * It manages Cities
 *
 * @author Cedric Ringger
 * @author Miki Ujihara
 * @version 1.0
 */
public class CreateCitiesScene extends ControllerBase{
    public TextField name;
    public City city;
    public Country country;

    /**
     * Initializes the scene
     * @param country needed for providing data and returning to the previous scene
     * @param city needed for editing or creating
     */
    @Override
    public void init(Country country, City city) {
        this.city = city;
        this.country = country;
        if (city != null){
            name.setText(city.name);
        }
    }

    /**
     * Saves the city
     */
    public void save() {
        if (name.getText().length() > 0){
            if (city == null){
                city = new City();
                country.cities.add(city);
            }
            city.name = name.getText();
            city.save(country.getId());
            back();
        }
    }

    /**
     * Returns to the showCitiesScene
     */
    public void back() {
        UiManager.showCitiesScene(country);
    }
}
