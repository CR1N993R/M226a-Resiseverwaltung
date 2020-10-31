package ch.ghibli.reiseverwaltung.backend;

import ch.ghibli.reiseverwaltung.backend.utils.ObjectNotFoundException;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

/**
 * This Class is for testing Certain methods in the Data class
 *
 * @author Cedric Ringger
 * @author Miki Ujihara
 * @version 1.0
 */
public class DataTest {
    /**
     * This test tests if all objects can be obtained through their id
     * @throws ObjectNotFoundException
     */
    @Test
    public void testGetById() throws ObjectNotFoundException {
        Country country = new Country();
        country.name = "Hello";
        Data.getCountries().add(country);

        City city = new City();
        city.name = "Hello";
        country.cities.add(city);

        Customer customer = new Customer();
        customer.name = "Hello";
        customer.lastname = "test";
        Data.getCustomers().add(customer);

        Trip trip = new Trip();
        trip.cities.add(city);
        trip.countries.add(country);
        customer.trips.add(trip);

        Assert.assertSame(Data.getCountryById(country.getId()), country);
        Assert.assertSame(Data.getCityById(city.getId()), city);
        Assert.assertSame(Data.getCustomerById(customer.getId()), customer);
        Assert.assertSame(Data.getTripById(trip.getId()), trip);
    }
}