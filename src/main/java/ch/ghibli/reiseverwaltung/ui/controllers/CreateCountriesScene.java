package ch.ghibli.reiseverwaltung.ui.controllers;

import ch.ghibli.reiseverwaltung.backend.Country;
import ch.ghibli.reiseverwaltung.backend.Data;
import ch.ghibli.reiseverwaltung.ui.UiManager;
import javafx.scene.control.TextField;

/**
 * This Class is the controller for the CreateCountriesScene
 * It manages the creation and editing of countries
 *
 * @author Cedric Ringger
 * @author Miki Ujihara
 * @version 1.0
 */
public class CreateCountriesScene extends ControllerBase{
    public TextField name;
    public Country country;

    /**
     * This function initializes the Ui
     * @param country provides the country for editing or creation
     */
    public void init(Country country){
        this.country = country;
        if (country != null){
            name.setText(country.name);
        }
    }

    /**
     * This function saves the country
     */
    public void save() {
        if (name.getText().length() > 0){
            if (country == null){
                country = new Country();
                Data.getCountries().add(country);
            }
            country.name = name.getText();
            country.save();
            back();
        }
    }

    /**
     * Returns to the showCountriesScene
     */
    public void back() {
        UiManager.showCountriesScene();
    }
}
