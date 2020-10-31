package ch.ghibli.reiseverwaltung.ui.controllers;

import ch.ghibli.reiseverwaltung.backend.City;
import ch.ghibli.reiseverwaltung.backend.Country;
import ch.ghibli.reiseverwaltung.backend.Customer;
import ch.ghibli.reiseverwaltung.backend.Trip;

/**
 * This class provides all init functions for the inherited classes
 * This allows to call certain functions without casting
 *
 * @author Cedric Ringger
 * @author Miki Ujihara
 * @version 1.0
 */
public abstract class ControllerBase {
    public void init() {}
    public void init(Country country, City city) {}
    public void init(Country country) {}
    public void init(Customer customer) {}
    public void init(Customer customer, Trip trip) {}
}
