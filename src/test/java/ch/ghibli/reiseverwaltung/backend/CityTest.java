package ch.ghibli.reiseverwaltung.backend;

import org.json.simple.JSONObject;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * This Class is for testing Certain methods in the City class
 *
 * @author Cedric Ringger
 * @author Miki Ujihara
 * @version 1.0
 */
public class CityTest {
    /**
     *This test is for testing if the object can be created from JSON
     */
    @Test
    public void createFromJsonTest(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "Zürich");

        City city = City.creatFromJson(jsonObject, "Test123");
        assertSame(city.name, "Zürich");
        assertSame(city.getId(), "Test123");
    }
}