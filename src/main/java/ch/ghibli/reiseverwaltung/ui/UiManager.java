package ch.ghibli.reiseverwaltung.ui;

import ch.ghibli.reiseverwaltung.Main;
import ch.ghibli.reiseverwaltung.backend.City;
import ch.ghibli.reiseverwaltung.backend.Country;
import ch.ghibli.reiseverwaltung.backend.Customer;
import ch.ghibli.reiseverwaltung.backend.Trip;
import ch.ghibli.reiseverwaltung.ui.controllers.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This class is for managing the GUI
 *
 * @author Cedric Ringger
 * @author Miki Ujihara
 * @version 1.0
 */
public abstract class UiManager {
    /**
     * Contains the MainStage
     */
    public static Stage stage;

    /**
     * Provides an FXML loader
     * @param path Contains the filepath of the file to load
     * @return returns the FXMLLoader fort further use
     */
    public static FXMLLoader loadFxml(String path) {
        return new FXMLLoader(Main.class.getClassLoader().getResource(path));
    }

    /**
     * Loads and displays the scene
     * @param path Contains the filepath of the file to load
     * @param controller Contains a controller if the FXML has none
     * @return Returns the Controller of the Scene
     */
    public static ControllerBase loadScene(String path, ControllerBase controller) {
        FXMLLoader loader = loadFxml(path);
        if (controller != null){
            loader.setController(controller);
        }
        try {
            stage.setScene(new Scene(loader.load()));
            stage.show();
        } catch (IOException ignored) {}
        return loader.getController();
    }

    /**
     * The following methods load certain scenes on demand
     */
    public static void showMainScene() {
        loadScene("MainScene.fxml",null);
    }

    /**
     * @param country Provides the Country for usage in the scene
     */
    public static void showCitiesScene(Country country) {
        loadScene("ShowScene.fxml", new CitiesScene()).init(country);
    }

    public static void showCountriesScene() {
        loadScene("ShowScene.fxml", new CountriesScene()).init();
    }

    public static void showCustomersScene() {
        loadScene("ShowScene.fxml", new CustomersScene()).init();
    }

    /**
     * @param customer Provides the Customer for usage in the scene
     */
    public static void showTripsScene(Customer customer) {
        loadScene("ShowScene.fxml", new TripsScene()).init(customer);
    }

    /**
     * @param city Provides the City for usage in the scene
     * @param country Provides the Country for usage in the scene
     */
    public static void createCitiesScene(City city, Country country) {
        loadScene("CreateCitiesScene.fxml",null).init(country, city);
    }

    /**
     * @param customer Provides the Customer for usage in the scene
     */
    public static void createCustomerScene(Customer customer) {
        loadScene("CreateCustomerScene.fxml",null).init(customer);
    }

    /**
     * @param trip Provides the Trip for usage in the scene
     * @param customer Provides the Customer for usage in the scene
     */
    public static void createTripScene(Trip trip, Customer customer) {
        loadScene("CreateTripScene.fxml",null).init(customer, trip);
    }

    /**
     * @param country Provides the Country for usage in the scene
     */
    public static void createCountryScene(Country country) {
        loadScene("CreateCountriesScene.fxml",null).init(country);
    }

    /**
     * The following methods return a FX element which is used in tables
     * @param city Provides the City for displaying the name in the prefab
     * @return Returns the created Prefab
     */
    public static AnchorPane getCityPrefab(City city){
        try {
            AnchorPane anchorPane = loadFxml("prefabs/CityPrefab.fxml").load();
            ((Label)anchorPane.getChildren().get(0)).setText(city.name);
            setIds(anchorPane, city.getId());
            return anchorPane;
        } catch (IOException ignored) {}
        return null;
    }

    /**
     * @param country Provides the Country for displaying the name in the prefab
     * @return Returns the created Prefab
     */
    public static AnchorPane getCountryPrefab(Country country){
        try {
            AnchorPane anchorPane = loadFxml("prefabs/CountriePrefab.fxml").load();
            ((Label)anchorPane.getChildren().get(0)).setText(country.name);
            setIds(anchorPane,country.getId());
            return anchorPane;
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param customer Provides the Customer for displaying the fields in the prefab
     * @return Returns the created Prefab
     */
    public static AnchorPane getCustomerPrefab(Customer customer) {
        try {
            AnchorPane anchorPane = loadFxml("prefabs/CustomerPrefab.fxml").load();
            ((Label)anchorPane.getChildren().get(0)).setText(customer.name);
            ((Label)anchorPane.getChildren().get(1)).setText(customer.lastname);
            setIds(anchorPane,customer.getId());
            return anchorPane;
        }catch (IOException ignored){}
        return null;
    }

    /**
     * @param trip Provides the Trip for inserting data into the dropdowns in the prefab
     * @return Returns the created Prefab
     */
    public static AnchorPane getTripPrefab(Trip trip) {
        try {
            AnchorPane anchorPane = loadFxml("prefabs/TripPrefab.fxml").load();
            ((Label)anchorPane.getChildren().get(2)).setText(trip.getStart());
            ((ComboBox<String>) anchorPane.getChildren().get(0)).setItems(trip.countriesToString());
            ((ComboBox<String>) anchorPane.getChildren().get(1)).setItems(trip.citiesToString());
            setIds(anchorPane,trip.getId());
            return anchorPane;
        }catch (IOException ignored){}
        return null;
    }

    /**
     * Iterates over the Child elements and sets the id
     * @param anchorPane The parent node
     * @param index The id to be set
     */
    public static void setIds(AnchorPane anchorPane, String index){
        for (Node child : anchorPane.getChildren()) {
            child.setId(index+"");
        }
    }
}
