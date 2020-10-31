package ch.ghibli.reiseverwaltung.ui.controllers;

import ch.ghibli.reiseverwaltung.ui.UiManager;

/**
 * The MainScene is the first Scene and allows the user to open further scenes
 *
 * @author Cedric Ringger
 * @author Miki Ujihara
 * @version 1.0
 */
public class MainScene extends ControllerBase{

    /**
     * Handles the showCountries button and displays the showCountriesScene
     */
    public void showCountries() {
        UiManager.showCountriesScene();
    }

    /**
     * Handles the showCustomer button and displays the showCustomerScene
     */
    public void showCustomers() {
        UiManager.showCustomersScene();
    }
}
