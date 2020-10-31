package ch.ghibli.reiseverwaltung.backend;

import org.json.simple.JSONObject;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

/**
 * This Class is for testing Certain methods in the Country class
 *
 * @author Cedric Ringger
 * @author Miki Ujihara
 * @version 1.0
 */
public class CountryTest {
    /**
     * This test is for testing if the object can be successfully created from JSON
     */
    @Test
    public void createFromJsonTest(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "Schweiz");
        Country country = Country.createFromJson(jsonObject, "Test123");

        assertSame(country.name ,"Schweiz");
        assertSame(country.getId(),"Test123");
        assertTrue(new File("data/cities/Test123").delete());
    }
}