package ch.ghibli.reiseverwaltung.ui.controllers;

import ch.ghibli.reiseverwaltung.backend.Country;
import ch.ghibli.reiseverwaltung.backend.Data;
import ch.ghibli.reiseverwaltung.backend.utils.ObjectNotFoundException;
import ch.ghibli.reiseverwaltung.ui.UiManager;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * This class is a controller for the showScene
 * It displays all Countries for Managing
 *
 * @author Cedric Ringger
 * @author Miki Ujihara
 * @version 1.0
 */
public class CountriesScene extends ControllerBase{
    public Label name;
    public VBox vBox;

    /**
     * Initializes the scene and loads the data needed
     */
    public void init(){
        name.setText("Länder Verwalten");
        load();
    }

    /**
     * Load all the important data into the ui
     */
    private void load(){
        for (Country country : Data.getCountries()) {
            AnchorPane pane = UiManager.getCountryPrefab(country);
            if (pane != null) {
                ((Button) pane.getChildren().get(1)).setOnAction(this::delete);
                ((Button) pane.getChildren().get(2)).setOnAction(this::edit);
                ((Button) pane.getChildren().get(3)).setOnAction(this::showCities);
                vBox.getChildren().add(pane);
            }
        }
    }

    /**
     * Handles the create button and shows the create scene
     */
    public void create(){
        UiManager.createCountryScene(null);
    }

    /**
     * Handles the back button and shows the MainScene
     */
    public void back(){
        UiManager.showMainScene();
    }

    /**
     * Handles the delete button and deletes the entry
     * @param event Provides the event source
     */
    public void delete(ActionEvent event){
        try {
            Data.removeCountry(Data.getCountryById(((Button)event.getSource()).getId()));
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        }
        vBox.getChildren().clear();
        load();
    }

    /**
     * Handles the edit button and shows the CreateScene with the selected loaded
     * @param event Provides the event source
     */
    public void edit(ActionEvent event){
        try {
            UiManager.createCountryScene(Data.getCountryById(((Button)event.getSource()).getId()));
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles the showCities button and loads the showCities scene
     * @param event Provides the event source
     */
    public void showCities(ActionEvent event){
        try {
            UiManager.showCitiesScene(Data.getCountryById(((Button)event.getSource()).getId()));
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        }
    }
}
