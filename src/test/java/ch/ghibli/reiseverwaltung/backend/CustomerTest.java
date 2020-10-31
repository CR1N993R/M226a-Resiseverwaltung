package ch.ghibli.reiseverwaltung.backend;

import org.json.simple.JSONObject;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

/**
 * This Class is for testing Certain methods in the Customer class
 *
 * @author Cedric Ringger
 * @author Miki Ujihara
 * @version 1.0
 */
public class CustomerTest {
    /**
     * This test is for testing if the object can be successfully created from JSON
     */
    @Test
    public void creteFromJsonTest(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "Hans");
        jsonObject.put("lastname", "Muster");

        Customer customer = Customer.createFromJson(jsonObject, "Test123");

        assertSame(customer.name, "Hans");
        assertSame(customer.lastname, "Muster");
        assertSame(customer.getId(), "Test123");
        assertTrue(new File("data/trips/Test123").delete());
    }
}